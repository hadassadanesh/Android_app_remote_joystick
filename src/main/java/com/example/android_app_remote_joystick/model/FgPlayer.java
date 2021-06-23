package com.example.android_app_remote_joystick.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FgPlayer {
    public String strIP;
    public String strPort;
    private ExecutorService executor;
    Socket fg;
    PrintWriter out;
    public double rudder;
    public double throttle;
    public double aileron;
    public double elevator;


    public void connectToFg(){
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    fg = new Socket(strIP, Integer.parseInt(strPort));
                    out = new PrintWriter(fg.getOutputStream(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendNewRudder(){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                double newRudder = rudder * 1/100;
                out.print("set /controls/flight/rudder " + newRudder +"\r\n");
                out.flush();
            }
        });
    }

    public void sendNewThrottle(){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                double newThrottle = throttle * 1/200;
                out.print("set /controls/engines/current-engine/throttle " + newThrottle +"\r\n");
                out.flush();
            }
        });
    }

    public void sendNewAileron(){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                double newAileron = 1/200 * aileron ;
                out.print("set /controls/flight/aileron " + newAileron +"\r\n");
                out.flush();
            }
        });
    }

    public void sendNewElevator(){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                double newElevator = elevator ;
                out.print("set /controls/flight/elevator " + newElevator +"\r\n");
                out.flush();
            }
        });
    }


}

