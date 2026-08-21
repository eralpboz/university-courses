module multi_digit_display(
    input logic clk,          
    input logic [15:0] sw,     
    output logic [6:0] seg,    
    output logic [3:0] an      
);


    logic [19:0] refresh_counter=0;
    
    always_ff @(posedge clk) begin
        refresh_counter <= refresh_counter + 1;
    end


    logic [1:0] digit_select;
    //assign digit_select = refresh_counter[19:18]; //basys
    assign digit_select = refresh_counter[1:0]; //simulasyon


    logic [3:0] current_hex; 
    

    always_comb begin
        case(digit_select)
            2'b00: begin
                an = 4'b0111;           
                current_hex = sw[15:12]; 
            end
            2'b01: begin
                an = 4'b1011;           
                current_hex = sw[11:8]; 
            end
            2'b10: begin
                an = 4'b1101;          
                current_hex = sw[7:4];  
            end
            2'b11: begin
                an = 4'b1110;           
                current_hex = sw[3:0];  
            end
            default: begin
                an = 4'b1111; 
                current_hex = 4'b0000;
            end
        endcase
    end

   
    hex_to_7seg decoder (
        .hex_val(current_hex),
        .segments(seg)
    );

endmodule