module mux_4to1 (
    input logic D0, D1, D2, D3,S1, S0,        
    output logic Y              
);


    assign Y = ({S1, S0} == 2'b00) ? D0 :
               ({S1, S0} == 2'b01) ? D1 :
               ({S1, S0} == 2'b10) ? D2 :
               ({S1, S0} == 2'b11) ? D3 : 1'b0;
endmodule



module mux_16to1 (
    
    input logic D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15, S3, S2, S1, S0,
    output logic Y_out
);

    logic Y_wire0, Y_wire1, Y_wire2, Y_wire3; 

    mux_4to1 mux0 (
        .D0(D0), .D1(D1), .D2(D2), .D3(D3), 
        .S1(S1), .S0(S0), 
        .Y(Y_wire0)
    );

    mux_4to1 mux1 (
        .D0(D4), .D1(D5), .D2(D6), .D3(D7), 
        .S1(S1), .S0(S0), 
        .Y(Y_wire1)
    );

    mux_4to1 mux2 (
        .D0(D8), .D1(D9), .D2(D10), .D3(D11), 
        .S1(S1), .S0(S0), 
        .Y(Y_wire2)
    );

    mux_4to1 mux3 (
        .D0(D12), .D1(D13), .D2(D14), .D3(D15), 
        .S1(S1), .S0(S0), 
        .Y(Y_wire3)
    );

    mux_4to1 final_mux (
        .D0(Y_wire0), .D1(Y_wire1), .D2(Y_wire2), .D3(Y_wire3), 
        .S1(S3), .S0(S2), 
        .Y(Y_out)
    );

endmodule