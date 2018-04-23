package com.example.gaope.slip;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

/**
 * Created by gaope on 2018/4/22.
 */

public class Static {
    private static final String TAG = "Static";

    /**
     * 缩放图片
     * @param bitmap
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap zoomImage(Bitmap bitmap,float newWidth,float newHeight){
        //获取这个图片的宽和高
        float width = bitmap.getWidth();
        //Log.d("asas",""+width);
        float height = bitmap.getHeight();
        //Log.d("ahah",""+height);
        //创建图片操作的matrix对象
        Matrix matrix = new Matrix();
        //计算宽高缩放率
        float scaleWidth = newWidth/width;
        float scaleHeight = newHeight/height;
        //Log.d(TAG,"scaleHe:"+scaleHeight);
        //缩放图片动作
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap bitmap1 =Bitmap.createBitmap( bitmap,0, 0, (int)width, (int)height, matrix, true);
        return bitmap1;
    }


}
