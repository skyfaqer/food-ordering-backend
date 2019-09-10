package com.cgy.sell.controller;

import com.cgy.sell.config.ProjectUrlConfig;
import com.cgy.sell.enums.ResultEnum;
import com.cgy.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

// Getting user openid using wxjava tools for buyers (in use)
// Getting user openid using wxjava tools for sellers to login (NO OPEN PLATFORM ACCOUNT NOW, WECHAT LOGIN FEATURE UNUSABLE)
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // Buyers
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        try {
            String encodedReturnUrl = URLEncoder.encode(returnUrl, "UTF-8");
            String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, encodedReturnUrl);
            log.info("[Wechat OAuth2 authorization]: getting code, encodedReturnUrl = {}, redirectUrl = {}", encodedReturnUrl, redirectUrl);
            return "redirect:" + redirectUrl;
        } catch (Exception e) {
            log.error("[URLEncoder]: unsupported encoding");
            return "ERROR: [URLEncoder]: unsupported encoding";
        }
    }

    // Buyers
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }
        catch (WxErrorException e) {
            log.error("[Wechat OAuth2 authorization]: {}", e.getError().getErrorMsg());
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openid;
    }

    // Sellers
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        try {
            String encodedReturnUrl = URLEncoder.encode(returnUrl, "UTF-8");
            String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, encodedReturnUrl);
            log.info("[Wechat QR authorization]: getting code, encodedReturnUrl = {}, redirectUrl = {}", encodedReturnUrl, redirectUrl);
            return "redirect:" + redirectUrl;
        } catch (Exception e) {
            log.error("[URLEncoder]: unsupported encoding");
            return "ERROR: [URLEncoder]: unsupported encoding";
        }
    }

    // Sellers
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        }
        catch (WxErrorException e) {
            log.error("[Wechat QR authorization]: {}", e.getError().getErrorMsg());
            throw new SellException(ResultEnum.WECHAT_OPEN_PLATFORM_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openid;
    }
}
