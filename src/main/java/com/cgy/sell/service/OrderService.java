package com.cgy.sell.service;

import com.cgy.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    // Create an order
    OrderDTO create(OrderDTO orderDTO);

    // Find one order
    OrderDTO findOne(String orderId);

    // Find all orders by buyerOpenid
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    // Cancel an order
    OrderDTO cancel(OrderDTO orderDTO);

    // Finish an order
    OrderDTO finish(OrderDTO orderDTO);

    // Pay an order
    OrderDTO pay(OrderDTO orderDTO);

    // Find all orders (for seller)
    Page<OrderDTO> findList(Pageable pageable);
}
