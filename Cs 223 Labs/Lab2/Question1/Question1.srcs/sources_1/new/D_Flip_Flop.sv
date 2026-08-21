module D_Flip_Flop(
    input logic clock,
    input logic reset,
    input logic in,
    output logic out
);

    always_ff @(posedge clock) begin
        if (reset) begin
            out <= 1'b0; 
        end else begin
            out <= in;    
        end
    end

endmodule