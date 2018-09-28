package com.mmy.maimaiyun;

import com.mmy.maimaiyun.utils.MD5;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void  fff()throws Exception{
        String json = "{\"com\":\"yunda\",\"num\":\"3962120229549\"}eQWZgGOu2818936D2D2DD2B47D98B3149BB4F23B679C";
        String encode = MD5.encode(json);
        encode.toCharArray();
    }


    //格式化时间
    @Test
    public void formatDate() throws Exception {
        long time = System.currentTimeMillis();//时间搓
        long f = 60;
        long h = 60 * 60;
        long day = 24 * h;
        long zhou = day * 7;
        long month = day * 30;
        long year = month * 12;
        if (time / f == 0) {

        }
    }
}