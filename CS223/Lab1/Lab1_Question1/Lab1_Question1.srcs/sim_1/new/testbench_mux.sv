module tb_mux();

    logic D0_tstbnch, D1_tstbnch, D2_tstbnch, D3_tstbnch, D4_tstbnch, D5_tstbnch, D6_tstbnch, D7_tstbnch,
          D8_tstbnch, D9_tstbnch, D10_tstbnch, D11_tstbnch, D12_tstbnch, D13_tstbnch, D14_tstbnch, D15_tstbnch,
          S3_tstbnch, S2_tstbnch, S1_tstbnch, S0_tstbnch, Y_out_tstbnch;

   
    mux_16to1 uut (
        .D0(D0_tstbnch), .D1(D1_tstbnch), .D2(D2_tstbnch), .D3(D3_tstbnch),
        .D4(D4_tstbnch), .D5(D5_tstbnch), .D6(D6_tstbnch), .D7(D7_tstbnch),
        .D8(D8_tstbnch), .D9(D9_tstbnch), .D10(D10_tstbnch), .D11(D11_tstbnch),
        .D12(D12_tstbnch), .D13(D13_tstbnch), .D14(D14_tstbnch), .D15(D15_tstbnch),
        .S3(S3_tstbnch), .S2(S2_tstbnch), .S1(S1_tstbnch), .S0(S0_tstbnch),
        .Y_out(Y_out_tstbnch)
    );

    initial begin
        D15_tstbnch=1; D14_tstbnch=1; D13_tstbnch=0; D12_tstbnch=0; 
        D11_tstbnch=1; D10_tstbnch=0; D9_tstbnch=1;  D8_tstbnch=0;  
        D7_tstbnch=0;  D6_tstbnch=1;  D5_tstbnch=0;  D4_tstbnch=1;  
        D3_tstbnch=0;  D2_tstbnch=0;  D1_tstbnch=1;  D0_tstbnch=1;  
        
     

        // 0: 0000  D0=1 
        S3_tstbnch=0; S2_tstbnch=0; S1_tstbnch=0; S0_tstbnch=0; #18; 
        
        // 1: 0001 D1=1 
        S3_tstbnch=0; S2_tstbnch=0; S1_tstbnch=0; S0_tstbnch=1; #18; 
        
        // 2: 0010 D2=0 
        S3_tstbnch=0; S2_tstbnch=0; S1_tstbnch=1; S0_tstbnch=0; #18; 
        
        // 3: 0011 D3=0 
        S3_tstbnch=0; S2_tstbnch=0; S1_tstbnch=1; S0_tstbnch=1; #18; 
        
        // 4: 0100 D4=1 
        S3_tstbnch=0; S2_tstbnch=1; S1_tstbnch=0; S0_tstbnch=0; #18; 
        
        // 5: 0101  D5=0 
        S3_tstbnch=0; S2_tstbnch=1; S1_tstbnch=0; S0_tstbnch=1; #18; 
        
        // 6: 0110  D6=1 
        S3_tstbnch=0; S2_tstbnch=1; S1_tstbnch=1; S0_tstbnch=0; #18; 
        
        // 7: 0111  D7=0 
        S3_tstbnch=0; S2_tstbnch=1; S1_tstbnch=1; S0_tstbnch=1; #18; 
        
        // 8: 1000 D8=0 
        S3_tstbnch=1; S2_tstbnch=0; S1_tstbnch=0; S0_tstbnch=0; #18; 
        
        // 9: 1001 D9=1 
        S3_tstbnch=1; S2_tstbnch=0; S1_tstbnch=0; S0_tstbnch=1; #18; 
        
        // 10: 1010 D10=0 
        S3_tstbnch=1; S2_tstbnch=0; S1_tstbnch=1; S0_tstbnch=0; #18; 
        
        // 11: 1011 D11=1
        S3_tstbnch=1; S2_tstbnch=0; S1_tstbnch=1; S0_tstbnch=1; #18; 
        
        // 12: 1100 D12=0 
        S3_tstbnch=1; S2_tstbnch=1; S1_tstbnch=0; S0_tstbnch=0; #18; 
        
        // 13: 1101  D13=0 
        S3_tstbnch=1; S2_tstbnch=1; S1_tstbnch=0; S0_tstbnch=1; #18; 
        
        // 14: 1110  D14=1
        S3_tstbnch=1; S2_tstbnch=1; S1_tstbnch=1; S0_tstbnch=0; #18; 
        
        // 15: 1111  D15=1
        S3_tstbnch=1; S2_tstbnch=1; S1_tstbnch=1; S0_tstbnch=1; #18; 
        
        $finish; 
    end

endmodule