`timescale 1ns / 1ps

// Signal Stabilizer Core
module Signal_Stabilizer_Core (
    input  logic clk_sys_mhz,
    input  logic raw_btn_in,
    output logic clean_pulse_out
);
    logic [19:0] stabilization_cnt; 
    logic current_st;
    logic previous_st;

    always_ff @(posedge clk_sys_mhz) begin
        previous_st <= current_st;
        if (raw_btn_in == current_st) begin
            stabilization_cnt <= 0;
        end else begin
            stabilization_cnt <= stabilization_cnt + 1;
            if (stabilization_cnt == 20'd1_000_000) begin
                current_st <= raw_btn_in;
                stabilization_cnt <= 0;
            end
        end
    end
    assign clean_pulse_out = (current_st == 1'b1) && (previous_st == 1'b0);
endmodule

// Button Signal Conditioner
module Button_Signal_Conditioner (
    input  logic clk_sys_mhz,
    input  logic buttonFirst, buttonSecond, buttonThird, buttonFourth,
    output logic pls_qw, pls_hw, pls_dr, pls_dw
);
    Signal_Stabilizer_Core filter_qw (.clk_sys_mhz(clk_sys_mhz), .raw_btn_in(buttonFirst),  .clean_pulse_out(pls_qw));
    Signal_Stabilizer_Core filter_hw (.clk_sys_mhz(clk_sys_mhz), .raw_btn_in(buttonSecond), .clean_pulse_out(pls_hw));
    Signal_Stabilizer_Core filter_dr (.clk_sys_mhz(clk_sys_mhz), .raw_btn_in(buttonThird),  .clean_pulse_out(pls_dr));
    Signal_Stabilizer_Core filter_dw (.clk_sys_mhz(clk_sys_mhz), .raw_btn_in(buttonFourth), .clean_pulse_out(pls_dw));
endmodule

// Clock Freq Divider
module Clk_Freq_Divider (
    input  logic clk_sys_mhz, rstt_sys_n,
    output logic blink_led_sig
);
    logic [25:0] tick_counter;
    always_ff @(posedge clk_sys_mhz or posedge rstt_sys_n) begin
        if (rstt_sys_n) begin
            tick_counter <= 0; 
            blink_led_sig <= 0;
        end else begin
            if (tick_counter == 25_000_000 - 1) begin
                tick_counter <= 0; 
                blink_led_sig <= ~blink_led_sig;
            end else begin
                tick_counter <= tick_counter + 1;
            end
        end
    end
endmodule

// State Decoder
module State_Decoder_3to8 (
    input  logic [2:0] state_in_bits,
    output logic [7:0] decoded_out_onehot
);
    always_comb begin
        decoded_out_onehot = 8'b00000000; 
        decoded_out_onehot[state_in_bits] = 1'b1;
    end
endmodule

// FSM LndryMachine
module FSM_LndryMachine (
    input  logic clk_sys_mhz, rstt_sys_n,
    input  logic pls_qw, pls_hw, pls_dr, pls_dw,
    output logic req_quick_w, req_heavy_w, req_dryer_m, req_delicate_w
);
    typedef enum logic [2:0] {
        IDLE_ST = 3'b000, REQ_Q_ST = 3'b001, REQ_H_ST = 3'b010, 
        REQ_DR_ST = 3'b011, REQ_DW_ST = 3'b100
    } sys_state_t;

    sys_state_t st_current, st_next;

    always_ff @(posedge clk_sys_mhz or posedge rstt_sys_n) begin
        if (rstt_sys_n) st_current <= IDLE_ST;
        else st_current <= st_next;
    end

    always_comb begin
        st_next = IDLE_ST; 
        case (st_current)
            IDLE_ST: begin
                if (pls_qw)       st_next = REQ_Q_ST;
                else if (pls_hw)  st_next = REQ_H_ST;
                else if (pls_dr)  st_next = REQ_DR_ST;
                else if (pls_dw)  st_next = REQ_DW_ST;
            end
            default: st_next = IDLE_ST;
        endcase
    end

    // Decoder Usage
    logic [7:0] decoder_wire;
    State_Decoder_3to8 dec_inst (
        .state_in_bits(st_current),
        .decoded_out_onehot(decoder_wire)
    );

    assign req_quick_w    = decoder_wire[1]; 
    assign req_heavy_w    = decoder_wire[2];
    assign req_dryer_m    = decoder_wire[3]; 
    assign req_delicate_w = decoder_wire[4];
endmodule

// Laundry Execution Datapath
module Laundry_Execution_Datapath #(parameter integer PROC_DUR = 4) (
    input  logic clk_sys_mhz, rstt_sys_n, req_in_sig,
    output logic is_busy_flag
);
    logic [2:0] wait_queue; 
    logic [3:0] sec_timer;
    logic [26:0] sec_scaler; 
    logic tick_1s;

    assign tick_1s = (sec_scaler == 100_000_000 - 1);

    always_ff @(posedge clk_sys_mhz or posedge rstt_sys_n) begin
        if (rstt_sys_n) begin
            is_busy_flag <= 0; wait_queue <= 0; sec_timer <= 0; sec_scaler <= 0;
        end else begin
            
            if (is_busy_flag) begin
                if (tick_1s) sec_scaler <= 0;
                else sec_scaler <= sec_scaler + 1;
            end else begin
                sec_scaler <= 0;
            end

            if (!is_busy_flag) begin
                if (req_in_sig) begin
                    is_busy_flag <= 1;
                    sec_timer <= PROC_DUR;
                end else if (wait_queue > 0) begin
                    is_busy_flag <= 1;
                    sec_timer <= PROC_DUR;
                    wait_queue <= wait_queue - 1;
                end
            end else begin
                if (req_in_sig && wait_queue < 7) begin
                    wait_queue <= wait_queue + 1;
                end
                
                if (tick_1s) begin
                    if (sec_timer > 1) begin
                        sec_timer <= sec_timer - 1;
                    end else begin
                        is_busy_flag <= 0; 
                    end
                end
            end
            
        end
    end
endmodule

// Busy State Aggregator
module Busy_State_Aggregator (
    input logic st_qw, st_hw, st_dr, st_dw,
    output logic [2:0] total_active_cnt
);
    assign total_active_cnt = st_qw + st_hw + st_dr + st_dw;
endmodule

// Main System Wrapper
module laundry_top (
    input  logic clk_sys_mhz, rstt_sys_n,
    input  logic buttonFirst, buttonSecond, buttonThird, buttonFourth,
    output logic [15:0] led,
    output logic [6:0] seg,
    output logic dp,
    output logic [3:0] an
);
    logic pls_qw, pls_hw, pls_dr, pls_dw;
    logic req_quick_w, req_heavy_w, req_dryer_m, req_delicate_w;
    logic blink_led_sig;
    logic flag_qw, flag_hw, flag_dr, flag_dw;
    logic [2:0] sum_active_mach;

    // Filter Group
    Button_Signal_Conditioner btn_filter_blk (
        .clk_sys_mhz(clk_sys_mhz), 
        .buttonFirst(buttonFirst), .buttonSecond(buttonSecond), 
        .buttonThird(buttonThird), .buttonFourth(buttonFourth),
        .pls_qw(pls_qw), .pls_hw(pls_hw), .pls_dr(pls_dr), .pls_dw(pls_dw)
    );

    // Clock Mod
    Clk_Freq_Divider clk_mod_blk (
        .clk_sys_mhz(clk_sys_mhz), .rstt_sys_n(rstt_sys_n), .blink_led_sig(blink_led_sig)
    );

    // FSM Controller
    FSM_LndryMachine fsm_ctrl_blk (
        .clk_sys_mhz(clk_sys_mhz), .rstt_sys_n(rstt_sys_n), 
        .pls_qw(pls_qw), .pls_hw(pls_hw), .pls_dr(pls_dr), .pls_dw(pls_dw),
        .req_quick_w(req_quick_w), .req_heavy_w(req_heavy_w), 
        .req_dryer_m(req_dryer_m), .req_delicate_w(req_delicate_w)
    );

    // QuickWashMachine Datapath
    Laundry_Execution_Datapath #(4) QuickWashMachine_Datapath (
        .clk_sys_mhz(clk_sys_mhz), .rstt_sys_n(rstt_sys_n), .req_in_sig(req_quick_w), .is_busy_flag(flag_qw)
    );

    // HeavyWashMachine Datapath
    Laundry_Execution_Datapath #(7) HeavyWashMachine_Datapath (
        .clk_sys_mhz(clk_sys_mhz), .rstt_sys_n(rstt_sys_n), .req_in_sig(req_heavy_w), .is_busy_flag(flag_hw)
    );

    // DryerMachine Datapath
    Laundry_Execution_Datapath #(9) DryerMachine_Datapath (
        .clk_sys_mhz(clk_sys_mhz), .rstt_sys_n(rstt_sys_n), .req_in_sig(req_dryer_m), .is_busy_flag(flag_dr)
    );

    // DelicateWashMachine Datapath
    Laundry_Execution_Datapath #(11) DelicateWashMachine_Datapath (
        .clk_sys_mhz(clk_sys_mhz), .rstt_sys_n(rstt_sys_n), .req_in_sig(req_delicate_w), .is_busy_flag(flag_dw)
    );

    // RTL Adder Equivalent
    Busy_State_Aggregator sum_calc_blk (
        .st_qw(flag_qw), .st_hw(flag_hw), .st_dr(flag_dr), .st_dw(flag_dw),
        .total_active_cnt(sum_active_mach)
    );

    // LED Outputs
    assign led[14:12] = flag_qw ? {3{blink_led_sig}} : 3'b111;
    assign led[10:8]  = flag_hw ? {3{blink_led_sig}} : 3'b111;
    assign led[6:4]   = flag_dr ? {3{blink_led_sig}} : 3'b111;
    assign led[2:0]   = flag_dw ? {3{blink_led_sig}} : 3'b111;
    assign led[15] = 0; assign led[11] = 0; assign led[7] = 0; assign led[3] = 0; 

    // Seven Segment
    assign an = 4'b1110; 
    assign dp = 1;       

    always_comb begin
        case (sum_active_mach)
            3'd0: seg = 7'b1000000; 
            3'd1: seg = 7'b1111001; 
            3'd2: seg = 7'b0100100; 
            3'd3: seg = 7'b0110000; 
            3'd4: seg = 7'b0011001; 
            default: seg = 7'b1111111; 
        endcase
    end
endmodule