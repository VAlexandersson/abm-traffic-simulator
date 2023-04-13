## Introduction

The purpose of the agent-based traffic simulation is to provide an access for clients to easy simulate traffic in a provided piece of road.

The traffic that will be simulated will contain one or more vehicles.

The vehicles shall have a provided speed and will be driving in a single way road. 

The end users that the system is aimed for is for any client that want to simulate a road. 


## User requirements

**1.** The traffic simulation will generate a visualisation of the traffic (vehicles) on the road and also output the data/statistics of the said traffic. An interface will also be provided that will give the user the possibility of providing custom parameters for number of vehicles in traffic, max speed of vehicles, delay probability, length of road.



## System requirements

**1.1.** The simulation shall be using the agent-based computional model.

**1.2.** An agent shall have following rules:

	1. Acceleration: $v_{i} \Leftarrow min(v_{i},v_{max})$
	2. Deceleration to avoid accidents: $v_{i} \Leftarrow min(v_i,gap)$
	3. Randomisation: with certain probability $p$ do $v_{i}\Leftarrow max(v_{i}-1,0)$ 
	4. Movement: $x_{i}\Leftarrow x_{i}+v_{i}$ 
			
**1.3.** An agent is a vehicle.

**1.4.** Vehicle shall have following paratmeters, *Velocity*, *Acceleration*, *Deceleration*,  *Position*

**1.5.** There will be at-least three different components in the program, *simulation component*, *UI component* and *data-collection component*.

**1.6.** Simulation and UI shall run on different threads. 

**1.7.** A graphical module will output a visualisation of the traffic to the UI. 

**1.8.** The data-collection module will output an CSV file with collected data when software has runned successfully to its end. 


