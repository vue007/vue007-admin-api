package com.vue007.admin.controller;

import com.vue007.admin.domain.jsonp.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by bamboo on 2017/6/28.
 */
@RestController
public class ExceptionController {

    @RequestMapping(value = "error", method = {RequestMethod.GET, RequestMethod.POST})
    private ResponseMessage<Void> handleError(HttpServletResponse response) {
        ResponseMessage<Void> responseMessage = new ResponseMessage<>(response.getStatus());
        try {
            HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
            responseMessage.setMessage(httpStatus.getReasonPhrase());
        } catch (IllegalArgumentException e) {
            responseMessage.setMessage("UNKNOW ERROR.");
        }
        return responseMessage;
    }

}
