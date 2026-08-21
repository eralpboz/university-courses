//2-to-4 Decoder
module decoder_2to4 (
    input logic d, e,
    output logic y0, y1, y2, y3
);
    assign y0 = (~d) & (~e);
    assign y1 = (~d) & (e);
    assign y2 = (d)  & (~e);
    assign y3 = (d)  & (e);
endmodule

//8-to-1 Multiplexer
module mux_8to1 (
    input logic in0, in1, in2, in3, in4, in5, in6, in7, s2, s1, s0,
    output logic out
);
    assign out = s2 ? (s1 ? (s0 ? in7 : in6) : (s0 ? in5 : in4)) :
                      (s1 ? (s0 ? in3 : in2) : (s0 ? in1 : in0));
endmodule

module logic_function (
    input logic A, B, C, D, E, 
    output logic F             
);
    
    logic y0, y1, y2, y3;
    logic not_D;

    assign not_D = ~D;

    decoder_2to4 my_decoder (
        .d(D), .e(E),
        .y0(y0), .y1(y1), .y2(y2), .y3(y3)
    );

  
    mux_8to1 my_mux (
        .in0(1'b1),    
        .in1(y0),     
        .in2(D),      
        .in3(1'b0),    
        .in4(1'b1),   
        .in5(1'b0),   
        .in6(y1),    
        .in7(not_D),  
        
        .s2(A), .s1(B), .s0(C), 
        .out(F)
    );

endmodule