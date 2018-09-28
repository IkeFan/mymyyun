package com.mmy.maimaiyun.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @创建者 lucas
 * @创建时间 2017/7/20 0020 16:24
 * @描述 TODO
 */

public class StringUtil {
    //对用户姓名打码
    public static String getMakeName(String name) {
        if (!TextUtils.isEmpty(name) && name.length() >= 2) {
            name = name.replace(name.charAt(1) + "", "*");
            return name;
        }
        return null;
    }

    //对身份证进行打码
    public static String getMakeIdCar(String idCar) {
        if (!TextUtils.isEmpty(idCar) && idCar.length() == 18) {
            return idCar.replace(idCar.substring(4, 14), "**********");
        }
        return null;
    }

    //对银行卡打码
    public static String getMakeBankCar(String bankCar) {
        if (!TextUtils.isEmpty(bankCar) && bankCar.length() >= 16) {
            return bankCar.replace(bankCar.substring(4, 12), " **** **** ");
        }
        return null;
    }

    public static String getMakePhone(String phone) {
        if (!TextUtils.isEmpty(phone) && phone.length() == 11) {
            return phone.replace(phone.substring(3, 7), "****");
        }
        return null;
    }

    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
