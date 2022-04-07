package com.mechonot.fiddle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import android.content.Intent;

import com.mechonot.fiddle.scrolling.FidScrollingActivity;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private  static final String TAG = "swipe position";
    private float x1, x2, y1, y2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        //Initialize GestureDetector
        this.gestureDetector = new GestureDetector(MainActivity.this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            //starting to swipe time gesture
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                //y1 = event.getY();
                break;
            //ending to swipe time gesture
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
               // y2 = event.getY();

                //getting values for horizontal swipe
                float valueX = x2 - x1;

                //getting values for vertical swipe
                //float valueY = y2 - y1;

                if(Math.abs(valueX) > MIN_DISTANCE){

                    //detect left to right
                    if(x2 > x1){
                        Intent intent = new Intent(this, FidScrollingActivity.class);
                        Toast.makeText(this, "Right is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "right swipe");
                        startActivity(intent);
                    }
                    //detect right to left
                    else {
//                        Intent intent = new Intent(this, FidScrollingActivity.class);
                        Toast.makeText(this, "Left is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "left swipe");
//                        startActivity(intent);
                    }



                }


                break;

        }


        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}