package com.cgy.sell.service.impl;

import com.cgy.sell.dataobject.SellerInfo;
import com.cgy.sell.repository.SellerInfoRepository;
import com.cgy.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
