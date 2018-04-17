package com.example.gaope.slip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LinearLayout lay_one;
    private float downX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lay_one = (LinearLayout)findViewById(R.id.slip_one_layout);

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = event.getX();
//                Log.d(TAG,"down1:"+downX);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = event.getX();
//                Log.d(TAG,"down:"+moveX);
//                int scrollX = lay_one.getScrollX();
//                Log.d(TAG,"scrloo::"+scrollX);
//                int newScrollX = (int) (scrollX+downX-moveX);
//                Log.d(TAG,"new:"+newScrollX);
//                lay_one.scrollTo(newScrollX, 0);
//                downX = moveX;
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}

