package com.mmy.maimaiyun.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.TypedValue;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mmy.maimaiyun.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import static android.graphics.Color.BLACK;

/**
 * @创建者 lucas
 * @创建时间 2017/7/5 0005 9:23
 * @描述 TODO
 */

public class UIUtil {
    /**
     * dp-->px
     */
    public static int dp2px(Context context,int dp) {
        // int densityDpi = getResources().getDisplayMetrics().densityDpi;
        float density = context.getResources().getDisplayMetrics().density;
        // System.out.println("densityDpi:" + densityDpi);
        // System.out.println("density:" + density);
        int px = (int) (dp * density + .5f);
        return px;
    }

    /**
     * px->dp
     */
    public static int px2dp(Context context,int px) {
        // px/dp = density
        float density = context.getResources().getDisplayMetrics().density;
        int dp = (int) (px / density + .5f);
        return dp;
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context,float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal,context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     */
    public static float px2sp(Context context,float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    //生成二维码
    public static Bitmap createQRCode(String str, int widthAndHeight)  {
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
        } catch (WriterException e) {
            e.printStackTrace();
            Log.e("UIUtil", "二维码生成失败");
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }


    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath = App.getAppComponent().getCache().directory().getAbsolutePath();
        Log.d("UIUtil", savePath);
        File filePic;
        try {
            filePic = new File(savePath + generateFileName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }
}
