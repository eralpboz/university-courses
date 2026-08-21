module lab_counter(
    input logic clk,           
    input logic [15:0] sw,     
    output logic [6:0] seg,    
    output logic [3:0] an,     
    output logic [15:0] led    
);

    logic slow_clk;            
    logic [15:0] count;        
    logic low_shift_in, high_shift_in;

    // Saat Bölücü: 100->2
    clock_divider clk_div (
        .clock_in(clk),
        .reset(sw[12]),
        .clock_out(slow_clk)
    );

    // Glue Logic 
    always_comb begin
        low_shift_in = (sw[15:13] == 3'b010) ? count[8] : 1'b0;  // Sağa Kaydırma (Yarıya bölme) 
        high_shift_in = (sw[15:13] == 3'b001) ? count[7] : 1'b0; // Sola Kaydırma (İkiyle çarpma) 
    end

    //LSB REGISTER 
    multifunction_register low_reg (
        .clock(slow_clk),             // for BASYS3 slow_clk    for tb clk
        .rst(sw[12]),
        .shift_in(low_shift_in),
        .select(sw[15:13]),
        .inpt(sw[7:0]),
        .outpt(count[7:0])
    );

    //MSB REGISTER 
    multifunction_register high_reg (
        .clock(slow_clk),             // for BASYS3 slow_clk  for tb clk
        .rst(sw[12]),
        .shift_in(high_shift_in),
        .select(sw[15:13]),
        .inpt({4'b0, sw[11:8]}), // 
        .outpt(count[15:8])
    );

    //ALARM 
    // count > 0xC74 
    assign led = (count > 16'h0C74) ? {16{slow_clk}} : 16'b0; // for BASYS3 slow_clk   for tb clk


    multi_digit_display display_unit (
        .clk(clk),
        .sw(count), 
        .seg(seg),
        .an(an)
    );

endmodule