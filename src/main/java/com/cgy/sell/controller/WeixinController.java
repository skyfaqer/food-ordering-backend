package com.cgy.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// Example for getting user openid using weixin api (old approach), not in use
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code,
                     @RequestParam("state") String state) {
        log.info("entering auth...");
        log.info("code = {}", code);
        log.info("state = {}", state);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7878cd0d2ef70372&secret=ddba84663b2a8b9276657b605ce73a2e&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        // User openid in response
        log.info("response = {}", response);
    }
}
