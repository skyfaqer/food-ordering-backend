package com.cgy.sell.VO;

import lombok.Data;

import java.io.Serializable;

// Data object returned for http request
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 5238629349115178292L;

    // Error code
    private Integer code;

    // Message
    private String msg;

    private T data;
}
