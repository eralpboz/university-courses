`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02.03.2026 15:22:11
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


module decoder24 (
 input logic E,
 input logic A,
 input logic B,
 output logic Y0,
 output logic Y1,
 output logic Y2,
 output logic Y3
);
 assign Y0 = E & (~A) & (~B);
 assign Y1 = E & (~A) & ( B);
 assign Y2 = E & ( A) & (~B);
 assign Y3 = E & ( A) & ( B);
endmodule
module mux81 (
 input logic [7:0] I,
 input logic [2:0] S,
 output logic Y
);
 assign Y = I[S];
endmodule
module logicC (
 input logic A, B, C, D, E,
 output logic Y
);
 logic DE00, DE01, DE10, DE11;
 decoder24 dec (
 .E (1'b1),
 .A (D),
 .B (E),
 .Y0(DE00), 
 .Y1(DE01), 
 .Y2(DE10), 
 .Y3(DE11) 
 );
 logic [7:0] inputs;
 assign inputs[0] = 1'b1;
 assign inputs[1] = DE00;
 assign inputs[2] = D;
 assign inputs[3] = 1'b0;
 assign inputs[4] = 1'b1;
 assign inputs[5] = 1'b0;
 assign inputs[6] = DE01;
 assign inputs[7] = ~D;

 mux81 mux (
 .I(inputs),
 .S({A, B, C}),
 .Y(Y)
 );
endmodule

