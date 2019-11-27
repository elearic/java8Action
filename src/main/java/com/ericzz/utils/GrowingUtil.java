package com.ericzz.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 *  GrowingIO API 认证工具类
 *  * @author zz_huns  
 *  @version Id: GrowingUtil.java, v 0.1 2019/9/16 8:18 PM zz_huns Exp $$
 *
 */
public class GrowingUtil {

    /**
     *  growingIO api 认证 auth 计算
     * @param gioPrivateSecret gio私钥
     * @param gioUid gio项目UID
     * @param id     gio项目ID
     * @param tm     当前时间戳(注意，tm不能取当前时间戳)
     * @return
     * @throws Exception
     */
    public static String authToken(String gioPrivateSecret, String gioUid, String id, Long tm) throws Exception {
        String message = "POST\n/auth/token\nproject="+gioUid+"&ai="+id+"&tm="+tm;
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(gioPrivateSecret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signature = hmac.doFinal(message.getBytes("UTF-8"));
        return Hex.encodeHexString(signature);
    }

}
