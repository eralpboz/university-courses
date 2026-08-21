module decoder_2to4 (
    input logic E,A1, A0,
    output logic Y0,Y1,Y2,Y3
);
    assign Y0 = E & (~A1) & (~A0);
    assign Y1 = E & (~A1) & (A0);
    assign Y2 = E & (A1)  & (~A0);
    assign Y3 = E & (A1)  & (A0);
endmodule


module decoder_3to8 (
    input logic E,A0,A1,A2,
    output logic Y0, Y1, Y2, Y3, Y4, Y5, Y6, Y7
);
    logic E_top;
    logic E_bottom;

    assign E_top = E & (~A2);
    assign E_bottom = E & A2;

    decoder_2to4 top_dec (
        .E(E_top), 
        .A1(A1), 
        .A0(A0),
        .Y0(Y0), 
        .Y1(Y1), 
        .Y2(Y2), 
        .Y3(Y3)
    );

    decoder_2to4 bottom_dec (
        .E(E_bottom), 
        .A1(A1), 
        .A0(A0),
        .Y0(Y4), 
        .Y1(Y5), 
        .Y2(Y6), 
        .Y3(Y7)
    );

endmodule