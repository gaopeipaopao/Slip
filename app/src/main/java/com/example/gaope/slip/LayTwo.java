package com.example.gaope.slip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 自定义view实现仿闹钟开关
 * Created by gaope on 2018/4/17.
 */

public class LayTwo extends View {

    private static final String TAG = "LayTwo";
    private Paint paint;
    private Scroller scroller;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private float touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    /**
     * isPress为false表示开关关闭，为灰色背景
     * isPress为false表示开关打开，为蓝色背景
     */
    private boolean isPress = false;

    /**
     * 记录matrix拉伸图片时的横坐标
     */
    private float moveX = 80;

    /**
     * 记录移动的坐标
     */
    private float downX;
    private float initX = 0;


    public LayTwo(Context context) {
        super(context);
    }

    public LayTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        scroller = new Scroller(getContext());
        bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.toggle_white);
        bitmap1 = Static.zoomImage(bitmap,80,80);
    }

    public LayTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //onDrow()方法中，canvas画布的原点就是自定义view的最左最顶端
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 40, 150, 120);
        if (isPress == true) {
            paint.setColor(Color.BLUE);
            canvas.drawRoundRect(rectF, 50, 50, paint);
            canvas.drawBitmap(bitmap1, initX,40, paint);
            isPress = false;
        } else {
            paint.setColor(Color.GRAY);
            canvas.drawRoundRect(rectF, 50, 50, paint);
            canvas.drawBitmap(bitmap1,initX,40,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getRawX();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = x;
                return true;

            /**
             * 在move的时候onDraw()重绘，drawBtmap()来进行图片的移动
             * 所能移动的距离为70dp
             */
            case MotionEvent.ACTION_MOVE:

                int dx = (int)(downX - x);
                initX = initX  - dx;
                Log.d(TAG,"inintX:"+initX);

                //dx>0向左移动，关闭按钮，背景为灰色；dx<0向右移动，打开按钮，背景为蓝色
                if(dx>0){
                    isPress = false;

                }else {
                    isPress = true;
                }

                //initX<0与initX>70判断是否达到边界
                if(initX<0){
                    initX = 0;
                }
                //Log.d(TAG,"width:" + getWidth());
                if(initX>70){
                    initX = 70;
                }

                //重绘
                invalidate();

                downX = x;
                return true;
            case MotionEvent.ACTION_UP:

        }
        return super.onTouchEvent(event);
    }


}
//判断dx移动的距离，进行mtarix拉伸
//                    if( dx > 0 && dx < 10 ){
//                        moveX = moveX + 5;
//                        bitmap1 = Static.zoomImage(bitmap,moveX,80);
//                    }else {
//                        moveX = 80;
//                        bitmap1 = Static.zoomImage(bitmap,moveX,80);
//
//
//                    }

//判断dx移动的距离，进行mtarix拉伸
//                    if( (-1 * dx) < touchSlop ) {
//                        moveX = moveX - dx;
//                        Log.d(TAG, "dxx:" + dx);
//                        Log.d(TAG, "moveX:" + moveX);
//                        bitmap1 = Static.zoomImage(bitmap, moveX, 80);
//                        invalidate();
//                        return true;
//                    }else {
//                        moveX = 80;
//                        bitmap1 = Static.zoomImage(bitmap,moveX,80);
//
//
//                       // Log.d("aaa","aaaa");
//                    }