package com.mmy.maimaiyun.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @创建者 lucas
 * @创建时间 2017/8/8 0008 17:08
 * @描述 计算工具
 */

public class MathUtil {
    /**
     * 四舍五入精确到小数点两位
     * @param aFloat
     * @return
     */
    public static BigDecimal round2(float aFloat){
        return new BigDecimal(String.valueOf(aFloat)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal round2(String aFloat){
        return new BigDecimal(aFloat).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * sha1 加密
     * @param info
     * @return
     */
    public static String encryptToSHA(String info) {
        byte[] digesta = null;
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(info.getBytes());
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byte2hex(digesta);
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }
}
