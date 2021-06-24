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
        if (model.executor == null){
            model.connectToFg();

        }
    }

    public void setRudder(int rudder){
        model.rudder = rudder;
        if (model.executor == null){
            model.connectToFg();

        }
        model.sendNewRudder();
    }

    public void setThrottle(int throttle){
        model.throttle = throttle;
        if (model.executor == null){
            model.connectToFg();
        }
        model.sendNewThrottle();
    }

    public void setAileron(double aileron){
        model.aileron = aileron;
        if (model.executor == null){
            model.connectToFg();

        }
        model.sendNewAileron();
    }

    public void setElevator(double elevator){
        model.elevator = elevator;
        if (model.executor == null){
            model.connectToFg();

        }
        model.sendNewElevator();
    }
}
