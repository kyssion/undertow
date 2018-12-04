package com.magic.sso.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magic.sso.util.response.Page;
import com.magic.sso.util.response.ResponseBase;

public class ResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T extends ResponseBase> String getResponseUtil(T item) throws JsonProcessingException {
        return objectMapper.writeValueAsString(item);
    }

    public static String getResponsUtil(Object body, ResultCodeUtil.ResultCode header) throws JsonProcessingException {
        return getResponseUtil(new ResponseBase(body,header));
    }

    public static String getResponsUtil(Object body, int code,String desc) throws JsonProcessingException {
        return getResponsUtil(body,new ResultCodeUtil.ResultCode(code,desc));
    }

    public static <T extends ResponseBase> String getResponseUtil(T item, Page page) throws JsonProcessingException {
        return objectMapper.writeValueAsString(item);
    }

    public static String getResponsUtil(Object body, ResultCodeUtil.ResultCode header,Page page) throws JsonProcessingException {
        return getResponseUtil(new ResponseBase(body,header));
    }

    public static String getResponsUtil(Object body, int code,String desc,Page page) throws JsonProcessingException {
        return getResponsUtil(body,new ResultCodeUtil.ResultCode(code,desc));
    }
}