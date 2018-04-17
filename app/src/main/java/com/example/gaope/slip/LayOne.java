package com.example.gaope.slip;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by gaope on 2018/4/15.
 */

public class LayOne extends LinearLayout {
    private static final String TAG = "Lay_One";
    private int mLastX;
    private int mLastY;
    private int downX;

    // 手指按下时View的相对坐标。
    private int mDownViewX;
    private int mDownViewY;
    private int moveX;

    public LayOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LayOne(Context context){
        super(context);
    }


    /**
     * 采用scrollTo()来进行滑动
     * scrollX:偏移量
     * mLastX-downX:两次触摸点之间的距离
     * scrollX+mLastX-downX:得到了scrollTo()方法中要传入的mScroller
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        downX = (int) event.getX();
        Log.d(TAG,"down:"+downX);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = downX;
                Log.d(TAG,"mLastX1:"+mLastX);
                return true;
            case MotionEvent.ACTION_MOVE:
                int scrollX = getScrollX();
                Log.d(TAG,"scrloo::"+scrollX);
                int newScrollX = scrollX+mLastX-downX;
                Log.d(TAG,"new:"+newScrollX);
                scrollTo(newScrollX, 0);
                mLastX = downX;
                Log.d(TAG,"mLastX:"+mLastX);
                return true;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     *采用scrollBy()来进行滑动
     * int dy = y - mLastY:dx为scrollBy()方法所要传入的参数
     */

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//
//        int x = (int) event.getRawX();
//        int y = (int) event.getRawY();
//        Log.d(TAG,"getRawX:"+x);
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                mDownViewX = getScrollX();
//                mDownViewY = getScrollY();
//
//                Log.d(TAG,""+mDownViewX);
//                Log.d(TAG,""+mDownViewX);
//                Log.d(TAG,"down啊 ");
//
//                mLastX = x;
//                mLastY = y;
//
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                int dy = y - mLastY;
//                int dx = x - mLastX;
//          //      Log.d(TAG,"xxx:"+getX());
//                mLastX = x;
//                mLastY = y;
//                scrollBy(-dx,0);
//                return true;
//            case MotionEvent.ACTION_UP:
//                return true;
//        }
//        return super.onTouchEvent(event);
//    }
    //getRawX()或者getX()都可以 要注意scrollBy()和scrollerTo()参数需要的是什么 需要什么传什么
    //scrollTo()需要mScrollX和mScrollY
    //scrollBy()需要dx和dy
}
