package com.example.magneta94.doodle;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.graphics.Color;
import android.widget.ToggleButton;
import android.widget.RadioButton;

import static com.example.magneta94.doodle.R.id.clearB;



public class MainActivity extends AppCompatActivity {


    private DoodleView dView;




    @Override
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dView = (DoodleView) findViewById(R.id.doodleC);
        final Button clearButton = (Button)findViewById(clearB);
        final ToggleButton styleButton = (ToggleButton)findViewById(R.id.toggleButton2);
        final SeekBar colorBar = (SeekBar)findViewById(R.id.colorBar);
        final SeekBar opBar = (SeekBar)findViewById(R.id.opBar);
        final SeekBar sizeBar =(SeekBar)findViewById(R.id.sizeBar);

        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               dView.sizeChange(progress);
            }
        });



        opBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                dView.alphaChange(progress);
            }
        });



        colorBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



            int progress = 0;
            float[] hsvColor = {0, 1, 1};

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                hsvColor[0] = 360f * i/100;
                int colIn =  Color.HSVToColor(hsvColor);
                dView.colorChange(colIn);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dView.clearLine();
            }
        });




        styleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dView.styleChange(true);
                } else {
                    dView.styleChange(false);
                }
            }
        });
      }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.whitec:
                if (checked)
                    dView.backgroundChange(Color.WHITE);
                    break;

            case R.id.blackC:
                if (checked)
                    dView.backgroundChange(Color.BLACK);
                    break;


            case R.id.blueC:
                if (checked)
                    dView.backgroundChange(Color.BLUE);
                    break;


            case R.id.greenC:
                if (checked)
                    dView.backgroundChange(Color.GREEN);
                    break;

            case R.id.Red:
                if (checked)
                    dView.backgroundChange(Color.RED);
                    break;



        }
    }






}







