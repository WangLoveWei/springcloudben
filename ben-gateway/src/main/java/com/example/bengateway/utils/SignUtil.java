package com.example.bengateway.utils;

import cn.hutool.core.date.DateUtil;
import com.example.bencommon.utils.SpringContextHolder;
import com.example.bencommon.utils.ToolUtil;
import com.example.bengateway.config.properties.SignProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 签名工具类
 *
 * @author yaoliguo
 * @date 2018-05-08 09:50
 */
public class SignUtil {

    private static final long DEAULT_SIGN_EXPIRED_SECONDS = 60;

    private static Logger logger = LoggerFactory.getLogger(ToolUtil.class);


    /**
     * 获取配置文件中的签名失效时间
     *
     * @author yaoliguo
     * @date 2018-05-08 14:02
     */
    private static long getExpiredTime() {
        SignProperties signProperties = null;
        try {
            signProperties = SpringContextHolder.getBean(SignProperties.class);
        } catch (RuntimeException e) {
            return DEAULT_SIGN_EXPIRED_SECONDS;
        }
        if (signProperties == null || signProperties.getTime() == 0L) {
            return DEAULT_SIGN_EXPIRED_SECONDS;
        } else {
            return signProperties.getTime();
        }
    }

    /**
     * 生成签名
     *
     * @author yaoliguo
     * @date 2018-05-08 14:02
     */
    public static String generateSign(String appId, String secret, String timestamp, String data) {
        if (ToolUtil.isOneEmpty(appId, secret, timestamp, data)) {
            throw new IllegalArgumentException("签名参数为空!appId=" + appId + ",secret=" + secret + ",timestamp=" + timestamp + ",data=" + data);
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append(appId);
            buf.append(secret);
            buf.append(timestamp);
            buf.append(data);
            return ToolUtil.md5Hex(buf.toString());
        }
    }

    /**
     * 校验签名是否有效
     *
     * @author yaoliguo
     * @date 2018-05-08 14:02
     */
    public static boolean validateSign(String appId, String secret, String timestamp, String requestBody, String sign) {
        return validateSign(appId, secret, timestamp, requestBody, sign, getExpiredTime());
    }

    /**
     * 校验签名是否有效
     *
     * @author yaoliguo
     * @date 2018-05-08 14:02
     */
    public static boolean validateSign(String appId, String secret, String timestamp, String data, String sign, long expiredSeconds) {
        if (ToolUtil.isOneEmpty(appId, secret, timestamp, data, sign)) {
            throw new IllegalArgumentException("签名参数为空!appId=" + appId + ",secret=" + secret + ",timestamp=" + timestamp + ",data=" + data + ",sign=" + sign);
        }

        Date timestampDate = null;
        try {
            timestampDate = DateUtil.parse(timestamp, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            logger.error("校验签名是否有效异常！时间转化异常！", e);
            return false;
        }

        long now = System.currentTimeMillis();
        long signTime = timestampDate.getTime();
        if ((now - signTime) / 1000 > expiredSeconds) {
            return false;
        }
        String nowSign = generateSign(appId, secret, timestamp, data);
        if (nowSign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
    }

}
