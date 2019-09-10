package com.cgy.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "success"),
    PARAM_ERROR(1, "parameter error"),
    PRODUCT_NOT_EXIST(10, "product not exist"),
    PRODUCT_STOCK_ERROR(11, "product stock not correct"),
    ORDER_NOT_EXIST(12, "order not exist"),
    ORDER_DETAIL_NOT_EXIST(13, "order detail not exist"),
    ORDER_STATUS_ERROR(14, "order status error"),
    ORDER_UPDATE_FAIL(15, "order update fail"),
    ORDER_DETAIL_EMPTY(16, "order detail empty"),
    PAY_STATUS_ERROR(17, "pay status error"),
    CART_EMPTY(18, "cart empty"),
    ORDER_OWNER_ERROR(19, "order not owned by current user"),
    WECHAT_MP_ERROR(20, "wechat media platform authorization error"),
    ORDER_AMOUNT_INCONSISTENT(21, "order amount inconsistent in wechat payment async notification"),
    ORDER_CANCEL_SUCCESS(22, "cancelling order: success"),
    ORDER_FINISH_SUCCESS(23, "finishing order: success"),
    PRODUCT_STATUS_ERROR(24, "product status error"),
    PRODUCT_ON_SALE(25, "product is successfully on sale"),
    PRODUCT_OFF_SALE(26, "product is successfully off sale"),
    WECHAT_OPEN_PLATFORM_ERROR(27, "wechat open platform authorization error"),
    LOGIN_INFO_ERROR(28, "login fail: login info error"),
    LOGOUT_SUCCESS(29, "logged out successfully")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
