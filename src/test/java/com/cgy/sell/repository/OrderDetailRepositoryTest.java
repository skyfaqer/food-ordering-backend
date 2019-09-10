package com.cgy.sell.repository;

import com.cgy.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111222");
        orderDetail.setOrderId("abc123444");
        orderDetail.setProductIcon("http://eee.jpg");
        orderDetail.setProductId("8erre3711");
        orderDetail.setProductName("food");
        orderDetail.setProductPrice(new BigDecimal(6));
        orderDetail.setProductQuantity(1);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId("abc123444");
        Assert.assertNotEquals(0, result.size());
    }
}
