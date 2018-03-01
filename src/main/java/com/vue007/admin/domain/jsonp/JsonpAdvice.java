package com.vue007.admin.domain.jsonp;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Xie Gengcai
 * @date 2017/6/23
 */
@ControllerAdvice(basePackages = "com.vue007.admin.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    protected JsonpAdvice() {
        super("callback", "jsonp");
    }

    protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return new ServletServerHttpRequest(servletRequest);
    }

    protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        return new ServletServerHttpResponse(response);
    }

    @ExceptionHandler(Throwable.class)
    public void exceptionHandler(NativeWebRequest request, Throwable e) {
        ServletServerHttpRequest inputMessage = createInputMessage(request);
        ServletServerHttpResponse outputMessage = createOutputMessage(request);
        ResponseMessage<Void> responseMessage = new ResponseMessage<>();

        responseMessage.setCode(StatusCode.CODE_ERROR);
        responseMessage.setMessage(e.getMessage());
        MappingJacksonValue bodyContainer = new MappingJacksonValue(responseMessage);

        beforeBodyWriteInternal(bodyContainer, MediaType.APPLICATION_JSON_UTF8, null, inputMessage, outputMessage);
        StringBuilder res = new StringBuilder("").append(JSON.toJSONString(responseMessage));
        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        try {
            response.getOutputStream().write(res.toString().getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
                                        MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(bodyContainer.getValue() instanceof ResponseMessage)) {
            bodyContainer.setValue(new ResponseMessage<>(StatusCode.CODE_SUCCESS, bodyContainer.getValue()));
        }
        super.beforeBodyWriteInternal(bodyContainer, contentType, returnType, request, response);

    }

}