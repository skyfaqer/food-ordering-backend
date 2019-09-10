package com.cgy.sell.service;

import com.cgy.sell.dto.OrderDTO;

public interface PushMessageService {

    // Push wechat template message of order status update
    void orderStatus(OrderDTO orderDTO);
}
