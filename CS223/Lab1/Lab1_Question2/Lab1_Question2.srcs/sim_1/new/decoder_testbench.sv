module tb_decoder();

    logic E_tstbnch, A2_tstbnch, A1_tstbnch, A0_tstbnch, Y0_tstbnch, Y1_tstbnch, Y2_tstbnch, Y3_tstbnch, Y4_tstbnch, Y5_tstbnch, Y6_tstbnch, Y7_tstbnch;

    decoder_3to8 uut (
        .E(E_tstbnch), 
        .A2(A2_tstbnch), .A1(A1_tstbnch), .A0(A0_tstbnch),
        .Y0(Y0_tstbnch), .Y1(Y1_tstbnch), .Y2(Y2_tstbnch), .Y3(Y3_tstbnch), 
        .Y4(Y4_tstbnch), .Y5(Y5_tstbnch), .Y6(Y6_tstbnch), .Y7(Y7_tstbnch)
    );

    initial begin
        // E= 0 TESTS
        E_tstbnch = 0; 
        
        A2_tstbnch = 0; A1_tstbnch = 0; A0_tstbnch = 0; #20; // 0
        A2_tstbnch = 0; A1_tstbnch = 0; A0_tstbnch = 1; #20; // 1
        A2_tstbnch = 0; A1_tstbnch = 1; A0_tstbnch = 0; #20; // 2
        A2_tstbnch = 0; A1_tstbnch = 1; A0_tstbnch = 1; #20; // 3
        A2_tstbnch = 1; A1_tstbnch = 0; A0_tstbnch = 0; #20; // 4
        A2_tstbnch = 1; A1_tstbnch = 0; A0_tstbnch = 1; #20; // 5
        A2_tstbnch = 1; A1_tstbnch = 1; A0_tstbnch = 0; #20; // 6
        A2_tstbnch = 1; A1_tstbnch = 1; A0_tstbnch = 1; #20; // 7
        

        // E = 1 TESTS

        E_tstbnch = 1;
        
        A2_tstbnch = 0; A1_tstbnch = 0; A0_tstbnch = 0; #20; 
        A2_tstbnch = 0; A1_tstbnch = 0; A0_tstbnch = 1; #20; 
        A2_tstbnch = 0; A1_tstbnch = 1; A0_tstbnch = 0; #20; 
        A2_tstbnch = 0; A1_tstbnch = 1; A0_tstbnch = 1; #20; 
        A2_tstbnch = 1; A1_tstbnch = 0; A0_tstbnch = 0; #20; 
        A2_tstbnch = 1; A1_tstbnch = 0; A0_tstbnch = 1; #20; 
        A2_tstbnch = 1; A1_tstbnch = 1; A0_tstbnch = 0; #20; 
        A2_tstbnch = 1; A1_tstbnch = 1; A0_tstbnch = 1; #20; 
        
        $finish;
    end

endmodule