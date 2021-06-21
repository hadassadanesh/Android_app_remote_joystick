package com.example.android_app_remote_joystick.view_model;

import com.example.android_app_remote_joystick.model.FgPlayer;

import java.io.IOException;

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

    void setAileron(int val){

    }

    void setElevator(int val){

    }
}
