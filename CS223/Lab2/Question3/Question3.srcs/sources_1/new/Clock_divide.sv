module clock_divider(
    input logic clock_in,  
    input logic reset,
    output logic clock_out 
);
    logic [24:0] count; 

    always_ff @(posedge clock_in) begin
        if (reset) begin
            count <= 25'b0;
            clock_out  <= 1'b0;
        end else if (count == 25'd24_999_999) begin
            count <= 25'b0;
            clock_out  <= ~clock_out ; 
        end else begin
            count <= count + 1;
        end
    end
endmodule