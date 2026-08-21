`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02.03.2026 15:03:17
// Design Name: 
// Module Name: abc
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////

module mux161(
    input  logic [11:0] d, 
    input  logic [3:0] s, 
    output logic y
    );
    logic w0, w1, w2, w3;

    mux41 m0 (.d(d[3:0]),   .s(s[1:0]), .y(w0));
    mux41 m1 (.d(d[7:4]),   .s(s[1:0]), .y(w1));
    mux41 m2 (.d(d[11:8]),  .s(s[1:0]), .y(w2));
    mux41 m3 (.d(4'b0000), .s(s[1:0]), .y(w3));

    
    mux41 m_out (
        .d({w3, w2, w1, w0}), 
        .s(s[3:2]),          
        .y(y)                
    );

endmodule


module mux41 (
input logic [3:0] d,
input logic [1:0] s,
output logic y

);
assign y = s[1] ? ( s[0] ? d[3] : d[2]) : ( s[0] ? d[1] : d[0]) ;

endmodule

