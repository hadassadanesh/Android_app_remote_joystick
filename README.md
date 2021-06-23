# Android_app_remote_joystick

# Brief explanation of the project
At this program we developed android application with MVVM architecture. 
This application constitutes remote control for Joystick in flight simulator.
The communication protocol- once the flight simulator, at the server side, get strings with instructions from the client, it moves the airplane in the flight simulator as a reaction.


# Added Features
* **Button** Connect: After entering the IP and the PORT, connect button will connect the application to the flight gear.
* **SeekBars**: There are two seek bars, through them the user can control the throttle and the rudder in the simulator. 
* **Joystick**: By moving the joystick, who has a circle shape, the user can control the aileron and the elevator in the simulator.

# The structure of the folders and the main files of the project
For programming this app, MVVM architecture has been used.
This App has three main parts that run it, each part with its own designated responsibilities- **V**iew, **V**iew**M**odel and **M**odel. 

The **V**iew bound by data-binding to the **V**iew-**M**odel, which will give commands to the **M**odel which will communicate with the flight simulator.

# Required installation and Preparations
1. Android Studio application  
2. [FlightGear](https://www.flightgear.org/)  application version 3.6

# Running

Before connecting, run the FlightGear Launcher and go to Additional Settings and write:
--telnet=socket,in,10,127.0.0.1,6400,tcp

After opening the app, the user needs to enter IP and PORT and then, needs to press connect button.

![image](https://github.com/hadassadanesh/Android_app_remote_joystick/blob/dbc441751490a4f71b300ab0758ab0b720fc3524/app.jpeg)

# UML Chart
Press here to get the UML

# Explanation video
Press here to watch our video examples.

# Collaborators
This program was developed by Hadassa Danesh and Noam Sery Levi.
