package com.cgy.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

// Wechat platform account info
// NO PAY ACCOUNT NOW, WECHAT PAYMENT FEATURE UNUSABLE
// NO OPEN PLATFORM ACCOUNT NOW, WECHAT LOGIN FEATURE UNUSABLE
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;

    private String openAppId;

    private String openAppSecret;

    private String mchId;

    private String mchKey;

    private String keyPath;

    // Wechat pay async notification url
    private String notifyUrl;

    // Wechat template message id, format: (templateName: templateId)
    private Map<String, String> templateId;
}
