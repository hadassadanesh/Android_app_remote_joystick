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

public class Joystick extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {

    private float xOfCenter;
    private float yOfCenter;
    private float bigRadius;
    private float littleRadius;
    private  JoystickListener joystickListener;

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

    public interface JoystickListener {
        void OnMoveJoystick(float aileron, float elevator);
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

        littleRadius = Math.min(getWidth(), getHeight()) / 4;  //5
        bigRadius = Math.min(getWidth(), getHeight()) / 2; //3
        xOfCenter = getWidth() /2 ;
        yOfCenter = getHeight() / 2;

        drawJoystick(xOfCenter, yOfCenter);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public boolean onTouch(View v, MotionEvent e){
        if(v.equals(this)){
            if(e.getAction() == e.ACTION_UP){
                drawJoystick(xOfCenter, yOfCenter);
                joystickListener.OnMoveJoystick(0,0);
            }
            else{
                float distance = (float) Math.sqrt((Math.pow((e.getX() - xOfCenter), 2))
                        + Math.pow(e.getY() - yOfCenter, 2));
                if(distance >= bigRadius){

                    float ratio = bigRadius / distance;
                    float constrainedX = xOfCenter + (e.getX() - xOfCenter) * ratio;
                    float constrainedY = yOfCenter + (e.getY() - yOfCenter) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    joystickListener.OnMoveJoystick((constrainedX - xOfCenter) / bigRadius,
                            (constrainedY - yOfCenter) / bigRadius);

                }
                else{

                    drawJoystick(e.getX(), e.getY());
                    joystickListener.OnMoveJoystick((e.getX() - xOfCenter) / bigRadius,
                            (e.getY() - yOfCenter) / bigRadius);
                }
            }
        }
        return true;
    }
}
