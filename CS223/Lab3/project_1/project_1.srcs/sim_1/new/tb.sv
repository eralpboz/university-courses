`timescale 1ns / 1ps

module tb_FSM_LndryMachine();

   
    logic clk_sys_mhz;
    logic rstt_sys_n;
    logic pls_qw, pls_hw, pls_dr, pls_dw;
    logic req_quick_w, req_heavy_w, req_dryer_m, req_delicate_w;


    FSM_LndryMachine uut (
        .clk_sys_mhz(clk_sys_mhz), 
        .rstt_sys_n(rstt_sys_n),
        .pls_qw(pls_qw), .pls_hw(pls_hw), .pls_dr(pls_dr), .pls_dw(pls_dw),
        .req_quick_w(req_quick_w), .req_heavy_w(req_heavy_w), 
        .req_dryer_m(req_dryer_m), .req_delicate_w(req_delicate_w)
    );

   
    always #5 clk_sys_mhz = ~clk_sys_mhz;


    initial begin
        
        clk_sys_mhz = 0;
        rstt_sys_n = 1;
        pls_qw = 0; pls_hw = 0; pls_dr = 0; pls_dw = 0;

   
        #50; 
        rstt_sys_n = 0; 
        
       
        #50; 

      
        @(posedge clk_sys_mhz);
        pls_qw = 1;
        @(posedge clk_sys_mhz);
        pls_qw = 0; 
        
        
        #100; 

        
        @(posedge clk_sys_mhz);
        pls_hw = 1; 
        @(posedge clk_sys_mhz);
        pls_hw = 0; 
        
        #100; 

       
        @(posedge clk_sys_mhz);
        pls_dr = 1; pls_dw = 1; 
        @(posedge clk_sys_mhz);
        pls_dr = 0; pls_dw = 0; 
        
        #100;

       
        #200;
        $finish;
    end

endmodule