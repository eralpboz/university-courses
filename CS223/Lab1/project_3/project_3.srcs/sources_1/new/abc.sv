`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02.03.2026 15:16:38
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


module full_adder (
 input logic a, 
 input logic b, 
 input logic cin, 
 output logic s,
 output logic cout 
);
 assign s = a ^ b ^ cin;

 assign cout = (a & b) | (cin & (a ^ b));
endmodule
module add (
 input logic [3:0] A, 
 input logic [3:0] B, 
 input logic C, 
 output logic [3:0] S, 
 output logic Cout 
);
 logic c1, c2, c3;

 logic [3:0] B_xor;

 assign B_xor[0] = B[0] ^ C;
 assign B_xor[1] = B[1] ^ C;
 assign B_xor[2] = B[2] ^ C;
 assign B_xor[3] = B[3] ^ C;

 full_adder fa0 (
 .a(A[0]),
 .b(B_xor[0]),
 .cin(C),
 .s(S[0]),
 .cout(c1)
 );
 full_adder fa1 (
 .a(A[1]),
 .b(B_xor[1]),
 .cin(c1),
 .s(S[1]),
 .cout(c2)
 );
 full_adder fa2 (
 .a(A[2]),
 .b(B_xor[2]),
 .cin(c2), 
 .s(S[2]),
 .cout(c3)
 );
 full_adder fa3 (
 .a(A[3]),
 .b(B_xor[3]),
 .cin(c3), 
 .s(S[3]),
 .cout(Cout) 
 );
endmodule

