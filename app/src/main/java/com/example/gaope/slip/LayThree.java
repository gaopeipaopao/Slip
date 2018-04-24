package com.example.gaope.slip;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Scroller
 * Created by gaope on 2018/4/23.
 */

public class LayThree extends ViewGroup{

    private static final String TAG = "LayThree";
    private Scroller scroller;
    private int touchSlop;
    private float downX;
    private float indownX;

    /**
     * 左边界
     * @param context
     */
    private int leftBroad;

    /**
     * 右边界
     * @param context
     */
    private int rightBroad;

    public LayThree(Context context) {
        super(context);
    }

    public LayThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public LayThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for(int i =0 ;i < childCount; i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount = getChildCount();
            for(int i = 0; i< childCount; i++){
                View childView = getChildAt(i);
               /// Log.d(TAG,"getMeas:"+childView.getMeasuredWidth());
                childView.layout(i * childView.getMeasuredWidth(),0,(i + 1) * childView.getMeasuredWidth(),childView.getMeasuredHeight());
            }
            leftBroad = getChildAt(0).getLeft();
            rightBroad = getChildAt(getChildCount() - 1).getRight();

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        float x = ev.getRawX();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                indownX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (x - indownX);
                indownX = x;
                if(dx > touchSlop){
                    return true;
                }
                return true;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (downX - x);
//                Log.d(TAG,"left:" + leftBroad);
//                Log.d(TAG,"right:" + rightBroad);
//                Log.d(TAG,"dx:" + dx );
//                Log.d(TAG,"getScrollX:" + getScrollX() );
//                float aa = getScaleX() + getWidth() + dx;
//               // Log.d(TAG,"rrrrr:" + aa );
                if (getScrollX() + dx < leftBroad){
                    scrollTo(leftBroad,0);
                    return true;
                }else if ( getScrollX() + getWidth() + dx  > rightBroad){
                //    Log.d(TAG,"width:"+getWidth());
                    scrollTo(rightBroad - getWidth() , 0);
                    return true;
                }
                downX = x;
                scrollBy(dx,0);
                return true;
            case MotionEvent.ACTION_UP:
                //判断在第几个界面
                //加上getWidth()/2使得target的值为大于等于1
                int target = ( getScrollX() + getWidth()/2 )/getWidth();
                //dx
                int dxx = (int) (target * getWidth()) - getScrollX();
                scroller.startScroll(getScrollX() , 0 , dxx , 0);
                invalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo( scroller.getCurrX() , scroller.getCurrY() );
            invalidate();
        }
    }
}
