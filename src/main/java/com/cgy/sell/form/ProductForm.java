package com.cgy.sell.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProductForm {

    @NotEmpty(message = "product id cannot be empty")
    private String productId;

    @NotEmpty(message = "product name cannot be empty")
    private String productName;

    @NotNull(message = "product price cannot be empty")
    @DecimalMin(value = "0", message = "product price cannot be less than 0")
    private BigDecimal productPrice;

    @NotNull(message = "product price cannot be empty")
    @Min(value = 0, message = "product stock cannot be less than 0")
    @Max(value = 999, message = "product stock cannot be greater than 999")
    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;
}
