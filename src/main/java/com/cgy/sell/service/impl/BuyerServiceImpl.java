package com.cgy.sell.service.impl;

import com.cgy.sell.dto.OrderDTO;
import com.cgy.sell.enums.ResultEnum;
import com.cgy.sell.exception.SellException;
import com.cgy.sell.service.BuyerService;
import com.cgy.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService service;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("[Cancel order]: order not found, orderId = {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return service.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = service.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        // Check if current order belongs to current openid
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("[Find order]: openid inconsistent, openid = {}, orderDTO = {}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
