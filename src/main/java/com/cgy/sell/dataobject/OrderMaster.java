package com.cgy.sell.dataobject;

import com.cgy.sell.enums.OrderStatusEnum;
import com.cgy.sell.enums.PayStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

// Order master table
@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    // Buyer's wechat openid
    private String buyerOpenid;

    private BigDecimal orderAmount;

    // default: new order
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    // default: waiting to be paid
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;
}
