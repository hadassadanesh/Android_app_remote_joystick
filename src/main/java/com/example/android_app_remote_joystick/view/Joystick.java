package com.example.android_app_remote_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;
    private  JoystickListener joystickCallBack;
    private final int ratio = 5;

    public Joystick(Context context) {

        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickCallBack = (JoystickListener) context;
        }
    }

    public Joystick(Context context, AttributeSet attributes, int style) {

        super(context, attributes,style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickCallBack = (JoystickListener) context;
        }

    }

    public Joystick(Context context, AttributeSet attributes) {

        super(context, attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickCallBack = (JoystickListener) context;
        }
    }


    private void drawJoystick(float x, float y){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = this.getHolder().lockCanvas();
            //this.setBackgroundColor(Color.TRANSPARENT);
            //this.setZOrderOnTop(true);
            //getHolder().setFormat(PixelFormat.TRANSPARENT);
            //canvas.drawColor(Color.WHITE);
            //canvas.drawColor(Color.argb(255,255,255,255));
            Paint colors = new Paint();
            //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
          //  colors.setARGB(255, 0,0 , 255);
            colors.setColor(Color.BLUE);
            canvas.drawCircle(centerX,centerY, baseRadius, colors);
            colors.setColor(Color.CYAN);
            canvas.drawCircle(x, y , hatRadius, colors);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void setupDimensions(){
        centerX = getWidth() /2 ;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    public boolean onTouch(View v, MotionEvent e){
        if(v.equals(this)){
            if(e.getAction() != e.ACTION_UP){
                float displacement = (float) Math.sqrt((Math.pow((e.getX() - centerX), 2))
                        + Math.pow(e.getY() - centerY, 2));
                if(displacement < baseRadius){
                    drawJoystick(e.getX(), e.getY());
                    joystickCallBack.OnJoystickMoved((e.getX() - centerX) / baseRadius,
                            (e.getY() - centerY) / baseRadius, getId());

                }
                else{
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (e.getX() - centerX) * ratio;
                    float constrainedY = centerY + (e.getY() - centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    joystickCallBack.OnJoystickMoved((constrainedX - centerX) / baseRadius,
                            (constrainedY - centerY) / baseRadius, getId());
                }
            }
            else{
                drawJoystick(centerX, centerY);
                joystickCallBack.OnJoystickMoved(0,0, getId());
            }
        }
        return true;
    }

    public interface JoystickListener {
        void OnJoystickMoved(float xPercent, float yPercent, int id);
    }
}
