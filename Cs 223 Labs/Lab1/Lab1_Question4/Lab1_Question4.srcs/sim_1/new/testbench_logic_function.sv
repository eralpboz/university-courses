module tb_logic_function();

    logic A_tstbnch, B_tstbnch, C_tstbnch, D_tstbnch, E_tstbnch, F_tstbnch;

    logic_function uut (
        .A(A_tstbnch), .B(B_tstbnch), .C(C_tstbnch), .D(D_tstbnch), .E(E_tstbnch),
        .F(F_tstbnch)
    );

    initial begin
        // TEST 0: 00000  F=1 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=0; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 1: 00001  F=1 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=0; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 2: 00010  F=1 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=0; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 3: 00011  F=1 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=0; D_tstbnch=1; E_tstbnch=1; #10;
        // TEST 4: 00100  F=1 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=1; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 5: 00101  F=0 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=1; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 6: 00110  F=0 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=1; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 7: 00111  F=0 
        A_tstbnch=0; B_tstbnch=0; C_tstbnch=1; D_tstbnch=1; E_tstbnch=1; #10;
        
        // TEST 8: 01000  F=0 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=0; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 9: 01001  F=0 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=0; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 10: 01010  F=1 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=0; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 11: 01011  F=1 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=0; D_tstbnch=1; E_tstbnch=1; #10;
        // TEST 12: 01100  F=0 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=1; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 13: 01101  F=0 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=1; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 14: 01110  F=0 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=1; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 15: 01111  F=0 
        A_tstbnch=0; B_tstbnch=1; C_tstbnch=1; D_tstbnch=1; E_tstbnch=1; #10;
        
        // TEST 16: 10000  F=1 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=0; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 17: 10001  F=1 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=0; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 18: 10010  F=1 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=0; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 19: 10011  F=1 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=0; D_tstbnch=1; E_tstbnch=1; #10;
        // TEST 20: 10100  F=0 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=1; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 21: 10101  F=0 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=1; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 22: 10110  F=0 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=1; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 23: 10111  F=0 
        A_tstbnch=1; B_tstbnch=0; C_tstbnch=1; D_tstbnch=1; E_tstbnch=1; #10;
        
        // TEST 24: 11000  F=0 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=0; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 25: 11001  F=1 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=0; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 26: 11010  F=0 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=0; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 27: 11011  F=0 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=0; D_tstbnch=1; E_tstbnch=1; #10;
        // TEST 28: 11100  F=1 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=1; D_tstbnch=0; E_tstbnch=0; #10;
        // TEST 29: 11101  F=1 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=1; D_tstbnch=0; E_tstbnch=1; #10;
        // TEST 30: 11110  F=0 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=1; D_tstbnch=1; E_tstbnch=0; #10;
        // TEST 31: 11111  F=0 
        A_tstbnch=1; B_tstbnch=1; C_tstbnch=1; D_tstbnch=1; E_tstbnch=1; #10;
        
        $finish;
    end

endmodule