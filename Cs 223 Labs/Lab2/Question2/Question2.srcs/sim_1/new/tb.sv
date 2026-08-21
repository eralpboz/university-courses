module tb_hex_to_7seg();

    logic [3:0] hex_val;

    logic [6:0] segments;


    hex_to_7seg dut (
        .hex_val(hex_val),
        .segments(segments)
    );


    initial begin
       
        for (int i = 0; i < 16; i++) begin
            hex_val = i;  
            #10;          
        end
        
        $finish; // Simülasyonu bitir
    end

endmodule