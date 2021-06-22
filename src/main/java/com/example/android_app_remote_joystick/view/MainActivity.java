package com.example.android_app_remote_joystick.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.example.android_app_remote_joystick.R;
import com.example.android_app_remote_joystick.databinding.ActivityMainBinding;
import com.example.android_app_remote_joystick.model.FgPlayer;
import com.example.android_app_remote_joystick.view_model.ViewModel;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ViewModel viewModel = new ViewModel(new FgPlayer());
    private Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strIP = binding.textOfIP.getText().toString().trim();
                String strPort = binding.textOfPort.getText().toString().trim();

                if (!strIP.equals("") && !strPort.equals("")) {
                    viewModel.setIp(strIP);
                    viewModel.setPort(strPort);

                }
//                else{
//                    Toast.makeText(getApplicationContext(), "IP or Port is missing",
//                            Toast.LENGTH_SHORT).show();
//                }
            }
        });


        binding.joystick.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                System.out.println("joystick");

//                else{
//                    Toast.makeText(getApplicationContext(), "IP or Port is missing",
//                            Toast.LENGTH_SHORT).show();
//                }
            }
        });

        binding.seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                viewModel.setRudder(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                viewModel.setThrottle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}