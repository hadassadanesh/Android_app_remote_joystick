package com.example.android_app_remote_joystick.view_model;

import com.example.android_app_remote_joystick.model.FgPlayer;

public class ViewModel {
    FgPlayer model;

    public ViewModel(FgPlayer m)
    {
        this.model = m;
    }

    public void setIp(String ip){
        model.strIP = ip;
    }

    public void setPort(String port){
        model.strPort = port;
        model.connectToFg();
    }

    public void setRudder(int rudder){
        model.rudder = rudder;
        model.connectToFg();
        model.sendNewRudder();
    }

    public void setThrottle(int throttle){
        model.throttle = throttle;
        model.connectToFg();
        model.sendNewThrottle();
    }

    public void setAileron(double aileron){
        model.aileron = aileron;
        model.connectToFg();
        model.sendNewAileron();
    }

    public void setElevator(double elevator){
        model.elevator = elevator;
        model.connectToFg();
        model.sendNewElevator();
    }
}
