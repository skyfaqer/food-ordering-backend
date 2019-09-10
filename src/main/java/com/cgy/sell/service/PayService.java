package com.cgy.sell.service;

import com.cgy.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

// NO PAY ACCOUNT NOW, WECHAT PAYMENT FEATURE UNUSABLE
public interface PayService {

    // Create a new payment given order info
    PayResponse create(OrderDTO orderDTO);

    // Process payment result notification from wechat
    PayResponse notify(String notifyData);

    // Process refund
    RefundResponse refund(OrderDTO orderDTO);
}
