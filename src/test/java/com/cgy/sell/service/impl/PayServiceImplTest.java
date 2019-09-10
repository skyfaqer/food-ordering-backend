package com.cgy.sell.service.impl;

import com.cgy.sell.dto.OrderDTO;
import com.cgy.sell.service.OrderService;
import com.cgy.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

// NO PAY ACCOUNT NOW, WECHAT PAYMENT FEATURE UNUSABLE
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    private static final String ORDER_ID_CREATE = "1565900854428821589";
    private static final String ORDER_ID_REFUND = "1565839046207205783";

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID_CREATE);
        payService.create(orderDTO);
    }

    @Test
    public void refund() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID_REFUND);
        payService.refund(orderDTO);
    }
}
