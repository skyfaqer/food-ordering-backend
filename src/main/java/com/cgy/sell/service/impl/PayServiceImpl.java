package com.cgy.sell.service.impl;

import com.cgy.sell.dto.OrderDTO;
import com.cgy.sell.enums.ResultEnum;
import com.cgy.sell.exception.SellException;
import com.cgy.sell.service.OrderService;
import com.cgy.sell.service.PayService;
import com.cgy.sell.utils.JsonUtil;
import com.cgy.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// NO PAY ACCOUNT NOW, WECHAT PAYMENT FEATURE UNUSABLE
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "Wechat order";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[Wechat pay]: generating pay request, payRequest = {}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[Wechat pay]: getting pay response when creating payment, payResponse = {}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    @Transactional
    public PayResponse notify(String notifyData) {
        // Steps for checking results:
        // 1. check sign (done by BestPayService)
        // 2. check payment status (done by BestPayService)
        // 3. check amount
        // 4. check payer (order creator == payer, not required for this project)

        // Generate pay response from notifyData
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[Wechat pay]: async payment result notification form wechat, payReponse = {}", JsonUtil.toJson(payResponse));

        // Check, then update order's payment status
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if (orderDTO == null) {
            log.error("[Wechat pay]: async payment result, order not exist, orderId = {}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("[Wechat pay]: async payment result, order amount inconsistent, orderId = {}, wechat notification amount = {}, orderDTO amount = {}",
                    payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.ORDER_AMOUNT_INCONSISTENT);
        }
        orderService.pay(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[Wechat refund]: refundRequest = {}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[Wechat refund]: refundResponse = {}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
