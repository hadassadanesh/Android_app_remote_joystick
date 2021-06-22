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
        model.sendNewRudder();
    }

    public void setThrottle(int throttle){
        model.throttle = throttle;
        model.sendNewThrottle();
    }

    void setAileron(int aileron){
        model.aileron = aileron;
    }

    void setElevator(int elevator){
        model.elevator = elevator;
    }
}
