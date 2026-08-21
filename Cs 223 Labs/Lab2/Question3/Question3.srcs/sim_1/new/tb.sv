module tb_lab_counter();
    logic clk;
    logic [15:0] sw;
    logic [6:0] seg;
    logic [3:0] an;
    logic [15:0] led;

    
    lab_counter dut (.*);

   
    always #5 clk = ~clk;

    initial begin
     
        clk = 0; 
        sw = 16'b0;
        
       
        sw[12] = 1; 
        #20; 
        sw[12] = 0; 
        #20;

        // ADIM 1: Yükleme (Parallel Load: 011) [cite: 398]
        sw[15:13] = 3'b011;
        sw[11:0] = 12'h014;  // Sayaç 0x0014 olacak [cite: 408]
        #20;

        //(Shift Left: 001)
        // 014 -> 0028 -> 0050 -> 00A0 
        sw[15:13] = 3'b001;
        #150;
        
        //(Shift Right: 010) 
        sw[15:13] = 3'b010;
        #100; //

        $finish;
    end
endmodule