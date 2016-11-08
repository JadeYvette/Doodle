package com.example.magneta94.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magneta94 on 11/2/16.
 */

public class DoodleView extends View {

    private Paint _paintDoodle = new Paint();
    private Paint _paintMirror = new Paint();
    private Path _path =  new Path();
    private Path _pathMirror = new Path();

    private Paint paintHold;

    private ArrayList<Paint> _paintTrack ;
    private ArrayList<Path>  _pathTrack ;
    private int bg = Color.WHITE;







    public DoodleView(Context context) {
        super(context);
    }

    public DoodleView(Context context,AttributeSet attrs){
        super(context,attrs);
        init(attrs,0);
    }

    public DoodleView(Context context,AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init(attrs,defStyle);
    }

    public void init (AttributeSet attrs, int defStyle){
        _paintDoodle.setColor(Color.BLUE);
        _paintDoodle.setStrokeWidth(10f);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setAlpha(255);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintMirror.set(_paintDoodle);
        _paintMirror.setColor(Color.BLACK);
        _paintMirror.setStyle(Paint.Style.FILL);
        _paintTrack = new ArrayList<Paint>();
        _pathTrack = new ArrayList<Path>();


    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        _paintMirror.setColor(bg);
        canvas.drawPaint(_paintMirror);
        for(int i = 0; i < _pathTrack.size(); i++) {
            canvas.drawPath(_pathTrack.get(i), _paintTrack.get(i));

        }

        canvas.drawPath(_path,_paintDoodle);


    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){

        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX,touchY);

                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX,touchY);

                break;
            case MotionEvent.ACTION_UP:

                _paintTrack.add(_paintDoodle);
                _pathTrack.add(_path);


                paintHold = new Paint();
                paintHold.set(_paintDoodle);

                _path = new Path();
                _paintDoodle = paintHold;


                break;

        }
        invalidate();
        return true;
    }

    public void clearLine() {
      _paintTrack.clear();
      _pathTrack.clear();
        invalidate();


    }


    public void sizeChange(int sizeIn) {

        _paintDoodle.setStrokeWidth(sizeIn);

    }

    public void alphaChange(int sizeIn) {

        _paintDoodle.setAlpha(255-sizeIn);


    }


    public void colorChange(int colorIn) {
        _paintDoodle.setColor(colorIn);

    }

    public void styleChange(boolean bool) {
        if(bool == true){
            _paintDoodle.setStyle(Paint.Style.FILL);
        }else{
            _paintDoodle.setStyle(Paint.Style.STROKE);
        }

    }

    public void backgroundChange(int colorIn) {
        bg = colorIn;
        invalidate();
    }
}
