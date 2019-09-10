package com.cgy.sell.controller;

import com.cgy.sell.dto.OrderDTO;
import com.cgy.sell.enums.ResultEnum;
import com.cgy.sell.exception.SellException;
import com.cgy.sell.service.OrderService;
import com.cgy.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Process order payment
// NO PAY ACCOUNT NOW, WECHAT PAYMENT FEATURE UNUSABLE
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        // Find order
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // Start payment (using freemarker template tool)
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        // Process nofityData in PayService
        payService.notify(notifyData);

        // Return result to wechat (using freemarker template tool)
        return new ModelAndView("pay/success");
    }
}
