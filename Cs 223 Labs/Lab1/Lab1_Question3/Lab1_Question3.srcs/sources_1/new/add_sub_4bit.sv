//Full Adder
module fulladr (
    input logic a, b, cin,
    output logic s, cout
);
    assign s = a ^ b ^ cin;
    assign cout = (a & b) | (cin & (a ^ b));
endmodule


// 4 bit adder substracter
module adder_subtractor_4bit (
    input logic A0, A1, A2, A3,B0, B1, B2, B3, C,               
    
    output logic S0, S1, S2, S3,
    //Last carryout 
    output logic Cout            
);

    // XOR outputs
    logic B0_xor, B1_xor, B2_xor, B3_xor;
    
    assign B0_xor = B0 ^ C;
    assign B1_xor = B1 ^ C;
    assign B2_xor = B2 ^ C;
    assign B3_xor = B3 ^ C;

    // Cin Cout wires
    logic C1, C2, C3;


    full_adder FA0 (
        .a(A0), .b(B0_xor), .cin(C),
        .s(S0), .cout(C1)
    );

 
    full_adder FA1 (
        .a(A1), .b(B1_xor), .cin(C1),
        .s(S1), .cout(C2)
    );


    full_adder FA2 (
        .a(A2), .b(B2_xor), .cin(C2),
        .s(S2), .cout(C3)
    );

 
    full_adder FA3 (
        .a(A3), .b(B3_xor), .cin(C3),
        .s(S3), .cout(Cout)
    );

endmodule