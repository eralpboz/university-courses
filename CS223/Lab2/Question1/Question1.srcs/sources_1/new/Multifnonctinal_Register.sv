module multifunction_register(
    input logic clock,
    input logic rst,
    input logic shift_in,
    input logic [2:0] select,
    input logic [7:0] inpt,
    output logic [7:0] outpt
);

    logic mux_out_0, mux_out_1, mux_out_2, mux_out_3, mux_out_4, mux_out_5, mux_out_6, mux_out_7;

    //BIT 0
    Multiplexer_8_to_1 m0 (.input0(outpt[0]), .input1(shift_in), .input2(outpt[1]), .input3(inpt[0]), .input4(outpt[7]), .input5(outpt[1]), .input6(outpt[7]), .input7(outpt[4]), .sel(select), .out(mux_out_0));
    D_Flip_Flop d0 (.clock(clock), .reset(rst), .in(mux_out_0), .out(outpt[0]));

    //BIT 1
    Multiplexer_8_to_1 m1 (.input0(outpt[1]), .input1(outpt[0]), .input2(outpt[2]), .input3(inpt[1]), .input4(outpt[0]), .input5(outpt[2]), .input6(outpt[6]), .input7(outpt[5]), .sel(select), .out(mux_out_1));
    D_Flip_Flop d1 (.clock(clock), .reset(rst), .in(mux_out_1), .out(outpt[1]));

    //BIT 2
    Multiplexer_8_to_1 m2 (.input0(outpt[2]), .input1(outpt[1]), .input2(outpt[3]), .input3(inpt[2]), .input4(outpt[1]), .input5(outpt[3]), .input6(outpt[5]), .input7(outpt[6]), .sel(select), .out(mux_out_2));
    D_Flip_Flop d2 (.clock(clock), .reset(rst), .in(mux_out_2), .out(outpt[2]));

    //BIT 3
    Multiplexer_8_to_1 m3 (.input0(outpt[3]), .input1(outpt[2]), .input2(outpt[4]), .input3(inpt[3]), .input4(outpt[2]), .input5(outpt[4]), .input6(outpt[4]), .input7(outpt[7]), .sel(select), .out(mux_out_3));
    D_Flip_Flop d3 (.clock(clock), .reset(rst), .in(mux_out_3), .out(outpt[3]));

    //BIT 4
    Multiplexer_8_to_1 m4 (.input0(outpt[4]), .input1(outpt[3]), .input2(outpt[5]), .input3(inpt[4]), .input4(outpt[3]), .input5(outpt[5]), .input6(outpt[3]), .input7(outpt[0]), .sel(select), .out(mux_out_4));
    D_Flip_Flop d4 (.clock(clock), .reset(rst), .in(mux_out_4), .out(outpt[4]));

    //BIT 5
    Multiplexer_8_to_1 m5 (.input0(outpt[5]), .input1(outpt[4]), .input2(outpt[6]), .input3(inpt[5]), .input4(outpt[4]), .input5(outpt[6]), .input6(outpt[2]), .input7(outpt[1]), .sel(select), .out(mux_out_5));
    D_Flip_Flop d5 (.clock(clock), .reset(rst), .in(mux_out_5), .out(outpt[5]));

    //BIT 6
    Multiplexer_8_to_1 m6 (.input0(outpt[6]), .input1(outpt[5]), .input2(outpt[7]), .input3(inpt[6]), .input4(outpt[5]), .input5(outpt[7]), .input6(outpt[1]), .input7(outpt[2]), .sel(select), .out(mux_out_6));
    D_Flip_Flop d6 (.clock(clock), .reset(rst), .in(mux_out_6), .out(outpt[6]));

    // BIT 7
    Multiplexer_8_to_1 m7 (.input0(outpt[7]), .input1(outpt[6]), .input2(shift_in), .input3(inpt[7]), .input4(outpt[6]), .input5(outpt[0]), .input6(outpt[0]), .input7(outpt[3]), .sel(select), .out(mux_out_7));
    D_Flip_Flop d7 (.clock(clock), .reset(rst), .in(mux_out_7), .out(outpt[7]));

endmodule