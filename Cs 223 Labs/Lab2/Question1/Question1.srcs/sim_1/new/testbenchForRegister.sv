module testbenchForRegister();

    logic clock;
    logic rst;
    logic shift_in;
    logic [2:0] select;
    logic [7:0] inpt;
    logic [7:0] outpt;

    multifunction_register dut (
        .clock(clock),
        .rst(rst),
        .shift_in(shift_in),
        .select(select),
        .inpt(inpt),
        .outpt(outpt)
    );

    always #5 clock = ~clock;

    initial begin
        clock = 0;
        rst = 1; 
        shift_in = 0;
        select = 3'b000;
        inpt = 8'h00;

        #10; 
        rst = 0; 
        //(1010 0101)
        
        // 011 (Parallel Load) 
        select = 3'b011; 
        inpt = 8'hA5; 
        #10;

        // 111 (Nibble Swap) 
        // A5 (1010 0101) -> 5A (0101 1010) 
        select = 3'b111;
        #10;

        // 110 (Bit Reversal) 
        select = 3'b110;
        #10;

        // 101 (Rotate Right) 
        select = 3'b101;
        #10;

        // 010 (Shift Right) 
        select = 3'b010;
        shift_in = 1'b0;
        #10;

        // 100 (Rotate Left) 
        select = 3'b100;
        #10;

        // 001 (Shift Left) 
        select = 3'b001;
        shift_in = 1'b1;
        #10;

        // 000 (Maintain Present Value) 
        select = 3'b000;
        #10;

        $finish;
    end

endmodule