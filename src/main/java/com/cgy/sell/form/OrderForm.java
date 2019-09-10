package com.cgy.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "buyer name required")
    private String name;

    @NotEmpty(message = "buyer phone required")
    private String phone;

    @NotEmpty(message = "buyer address required")
    private String address;

    @NotEmpty(message = "buyer openid required")
    private String openid;

    @NotEmpty(message = "bought items can not be empty")
    private String items;
}
