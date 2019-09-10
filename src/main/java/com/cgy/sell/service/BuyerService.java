package com.cgy.sell.service;

import com.cgy.sell.dto.OrderDTO;

public interface BuyerService {

    // Find an order
    OrderDTO findOrderOne(String openid, String orderId);

    // Cancel an order
    OrderDTO cancelOrder(String openid, String orderId);
}
