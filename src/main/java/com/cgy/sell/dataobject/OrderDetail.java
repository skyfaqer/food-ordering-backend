package com.cgy.sell.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

// Order detail
@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    // Quantity of the product bought
    private Integer productQuantity;

    private String productIcon;
}
