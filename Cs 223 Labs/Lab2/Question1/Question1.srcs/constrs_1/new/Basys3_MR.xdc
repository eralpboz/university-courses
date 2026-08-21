set_property PACKAGE_PIN W5 [get_ports clk]							
	set_property IOSTANDARD LVCMOS33 [get_ports clk]

# inpt[7:0] (Rightmost 8 switches: SW0 to SW7)
set_property PACKAGE_PIN V17 [get_ports {inpt[0]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[0]}]
set_property PACKAGE_PIN V16 [get_ports {inpt[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[1]}]
set_property PACKAGE_PIN W16 [get_ports {inpt[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[2]}]
set_property PACKAGE_PIN W17 [get_ports {inpt[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[3]}]
set_property PACKAGE_PIN W15 [get_ports {inpt[4]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[4]}]
set_property PACKAGE_PIN V15 [get_ports {inpt[5]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[5]}]
set_property PACKAGE_PIN W14 [get_ports {inpt[6]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[6]}]
set_property PACKAGE_PIN W13 [get_ports {inpt[7]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {inpt[7]}]

# select[2:0] (SW8 to SW10)
set_property PACKAGE_PIN V2 [get_ports {select[0]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {select[0]}]
set_property PACKAGE_PIN T3 [get_ports {select[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {select[1]}]
set_property PACKAGE_PIN T2 [get_ports {select[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {select[2]}]

# shift_in (SW14)
set_property PACKAGE_PIN T1 [get_ports shift_in]					
	set_property IOSTANDARD LVCMOS33 [get_ports shift_in]

# reset (SW15)
set_property PACKAGE_PIN R2 [get_ports rst]					
	set_property IOSTANDARD LVCMOS33 [get_ports rst]

# outpt[7:0] (Rightmost 8 LEDs: LED0 to LED7)
set_property PACKAGE_PIN U16 [get_ports {outpt[0]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[0]}]
set_property PACKAGE_PIN E19 [get_ports {outpt[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[1]}]
set_property PACKAGE_PIN U19 [get_ports {outpt[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[2]}]
set_property PACKAGE_PIN V19 [get_ports {outpt[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[3]}]
set_property PACKAGE_PIN W18 [get_ports {outpt[4]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[4]}]
set_property PACKAGE_PIN U15 [get_ports {outpt[5]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[5]}]
set_property PACKAGE_PIN U14 [get_ports {outpt[6]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[6]}]
set_property PACKAGE_PIN V14 [get_ports {outpt[7]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {outpt[7]}]