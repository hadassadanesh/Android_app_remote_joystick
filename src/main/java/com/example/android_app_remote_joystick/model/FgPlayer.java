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

    public void connectToFg(){
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    fg = new Socket("172.18.57.147", 6401);
                    out = new PrintWriter(fg.getOutputStream(), true);
                    System.out.println("avivvvvvv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

