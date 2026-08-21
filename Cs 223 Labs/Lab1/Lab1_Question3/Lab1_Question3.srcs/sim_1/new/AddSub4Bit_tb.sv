module tb_adder_subtractor();

    logic A0_tstbnch, A1_tstbnch, A2_tstbnch, A3_tstbnch,
          B0_tstbnch, B1_tstbnch, B2_tstbnch, B3_tstbnch,
          C_tstbnch,
          S0_tstbnch, S1_tstbnch, S2_tstbnch, S3_tstbnch,
          Cout_tstbnch;

    adder_subtractor_4bit uut (
        .A0(A0_tstbnch), .A1(A1_tstbnch), .A2(A2_tstbnch), .A3(A3_tstbnch),
        .B0(B0_tstbnch), .B1(B1_tstbnch), .B2(B2_tstbnch), .B3(B3_tstbnch),
        .C(C_tstbnch),
        .S0(S0_tstbnch), .S1(S1_tstbnch), .S2(S2_tstbnch), .S3(S3_tstbnch),
        .Cout(Cout_tstbnch)
    );

    initial begin
        
        // 6 + 4 = 10 
        // A = 6 (0110)
        A3_tstbnch = 0; A2_tstbnch = 1; A1_tstbnch = 1; A0_tstbnch = 0;
        // B = 4 (0100)
        B3_tstbnch = 0; B2_tstbnch = 1; B1_tstbnch = 0; B0_tstbnch = 0;
        
        C_tstbnch = 0; 
        #20; 
        

        // 8 - 3 = 5 
        // A = 8 (1000)
        A3_tstbnch = 1; A2_tstbnch = 0; A1_tstbnch = 0; A0_tstbnch = 0;
        // B = 3 (0011)
        B3_tstbnch = 0; B2_tstbnch = 0; B1_tstbnch = 1; B0_tstbnch = 1;
    
        C_tstbnch = 1; 
        #20; 
        
        
        // 15 + 2 = 17
        A3_tstbnch = 1; A2_tstbnch = 1; A1_tstbnch = 1; A0_tstbnch = 1; // 15
        B3_tstbnch = 0; B2_tstbnch = 0; B1_tstbnch = 1; B0_tstbnch = 0; // 2
        C_tstbnch = 0; 
        #20;
        
        $finish;
    end

endmodule