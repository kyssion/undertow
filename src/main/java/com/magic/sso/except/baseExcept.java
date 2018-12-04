package com.magic.sso.except;

import com.magic.sso.util.ResultCodeUtil;

public class baseExcept extends Exception{
    public baseExcept(int code,String desc){

    }
    public baseExcept(ResultCodeUtil.ResultCode resultCode){

    }
}
