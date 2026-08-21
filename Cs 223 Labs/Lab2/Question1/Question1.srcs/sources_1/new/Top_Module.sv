module top_module(
    input logic clk,          
    input logic rst,          
    input logic shift_in,     
    input logic [2:0] select, 
    input logic [7:0] inpt,   
    output logic [7:0] outpt  
);

    logic slow_clk; 

    clock_divider clk_div (
        .clock_in(clk),       
        .reset(1'b0),         
        .clock_out(slow_clk)  
    );

    multifunction_register mfr (
        .clock(slow_clk),     
        .rst(rst),
        .shift_in(shift_in),
        .select(select),
        .inpt(inpt),
        .outpt(outpt)
    );

endmodule