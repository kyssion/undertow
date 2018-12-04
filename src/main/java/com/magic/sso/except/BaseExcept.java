package com.magic.sso.except;

import com.magic.sso.util.ResultCodeUtil;

public class BaseExcept extends Exception{

    private ResultCodeUtil.ResultCode resultCode;

    public BaseExcept(int code,String desc){
        this(new ResultCodeUtil.ResultCode(code,desc));
    }
    public BaseExcept(ResultCodeUtil.ResultCode resultCode){
        this.resultCode=resultCode;
    }

    public ResultCodeUtil.ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCodeUtil.ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
