package com.example.android_app_remote_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.android_app_remote_joystick.R;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private float xOfCenter;
    private float yOfCenter;
    private float bigRadius;
    private float littleRadius;
    private  JoystickListener joystickListener;
    // private final int ratio = 5;

    public Joystick(Context context) {

        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickListener = (JoystickListener) context;
        }
    }

    public Joystick(Context context, AttributeSet attributes, int style) {

        super(context, attributes,style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickListener = (JoystickListener) context;
        }
    }

    public Joystick(Context context, AttributeSet attributes) {

        super(context, attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickListener = (JoystickListener) context;
        }
    }


    private void drawJoystick(float x, float y){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            canvas.drawColor(getResources().getColor(R.color.lightBlue2));
            colors.setColor(Color.WHITE);
            canvas.drawCircle(xOfCenter, yOfCenter, bigRadius, colors);
            colors.setColor(getResources().getColor(R.color.purple_500));
            canvas.drawCircle(x, y , littleRadius, colors);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(xOfCenter, yOfCenter);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void setupDimensions(){
        xOfCenter = getWidth() /2 ;
        yOfCenter = getHeight() / 2;
        bigRadius = Math.min(getWidth(), getHeight()) / 2; //3
        littleRadius = Math.min(getWidth(), getHeight()) / 4;  //5
    }

    public boolean onTouch(View v, MotionEvent e){
        if(v.equals(this)){
            if(e.getAction() != e.ACTION_UP){
                float displacement = (float) Math.sqrt((Math.pow((e.getX() - xOfCenter), 2))
                        + Math.pow(e.getY() - yOfCenter, 2));
                if(displacement < bigRadius){
                    drawJoystick(e.getX(), e.getY());
                    joystickListener.OnJoystickMoved((e.getX() - xOfCenter) / bigRadius,
                            (e.getY() - yOfCenter) / bigRadius, getId());

                }
                else{
                    float ratio = bigRadius / displacement;
                    float constrainedX = xOfCenter + (e.getX() - xOfCenter) * ratio;
                    float constrainedY = yOfCenter + (e.getY() - yOfCenter) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    joystickListener.OnJoystickMoved((constrainedX - xOfCenter) / bigRadius,
                            (constrainedY - yOfCenter) / bigRadius, getId());
                }
            }
            else{
                drawJoystick(xOfCenter, yOfCenter);
                joystickListener.OnJoystickMoved(0,0, getId());
            }
        }
        return true;
    }

    public interface JoystickListener {
        void OnJoystickMoved(float xPercent, float yPercent, int id);
    }
}
