package com.cgy.sell.handler;

import com.cgy.sell.VO.ResultVO;
import com.cgy.sell.config.ProjectUrlConfig;
import com.cgy.sell.exception.SellException;
import com.cgy.sell.exception.SellerAuthorizeException;
import com.cgy.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// Handle exceptions from seller's end
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // Handle login status verification exceptions, then redirect
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handleSellerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    // Handle other exceptions defined in this project
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
