# Android_app_remote_joystick

# Brief explanation of the project
At this program we developed android application with MVVM architecture. 
This application constitutes remote control for Joystick in flight simulator.
The communication protocol- once the flight simulator, at the server side, get strings with instructions from the client, it moves the airplane in the flight simulator as a reaction.


# Added Features
* **Buttom** Connect: After entering the IP and the PORT, connect button will connect the application to the flight gear.
* **SeekBars**: There are two seek bars, through them the user can control the throttle and the rudder in the simulator. 
* **Joystick**: By moving the joystick, who has a circle shape, the user can control the aileron and the elevator in the simulator.

# The structure of the folders and the main files of the project
For programming this app, MVVM architecture has been used.
This App has three main parts that run it, each part with its own designated responsibilities- **M**odel, **V**iew**M**odel and **V**iew. 

The **V**iew bound by data-binding to the **V**iew-**M**odel, which will give commands to the **M**odel which will communicate with the flight simulator.

# Required installation and Preparations
1. Android Studio application  
2. [FlightGear](https://www.flightgear.org/)  application version 3.6

# Running


# UML Chart
Press here to get the UML

# Explanation video
Press here to watch our video examples.

# Collaborators
This program was developed by Hadassa Danesh and Noam Sery Levi.
