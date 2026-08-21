module tb_multi_digit();
    logic clk;
    logic [15:0] sw;
    logic [6:0] seg;
    logic [3:0] an;

    
    multi_digit_display dut (
        .clk(clk),
        .sw(sw),
        .seg(seg),
        .an(an)
    );

 
    always #5 clk = ~clk;

    initial begin
       
        clk = 0;
  
        sw = 16'hAB31; 
        
        #100; 

        #2000; 
        $finish;
    end
endmodule