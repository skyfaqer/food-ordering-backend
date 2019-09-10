package com.cgy.sell.service;

import com.cgy.sell.dataobject.SellerInfo;

public interface SellerService {

    // Find seller's account info by wechat openid
    SellerInfo findSellerInfoByOpenid(String openid);
}
