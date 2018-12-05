package com.magic.sso.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.magic.sso.util.ResultCodeUtil;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBase {

    public ResponseBase(){
        super();
    }

    public ResponseBase(Object body, ResultCodeUtil.ResultCode header){
        this.body = body;
        this.header = header;
    }

    public ResponseBase(Object body, int code, String desc){
        this(body,new ResultCodeUtil.ResultCode(code,desc));
    }

    private ResultCodeUtil.ResultCode header;
    private Object body;

    public ResultCodeUtil.ResultCode getHeader() {
        return header;
    }

    public void setHeader(ResultCodeUtil.ResultCode header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
