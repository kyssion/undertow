package com.magic.sso.except;

import com.magic.sso.util.ResultCodeUtil;

public class UserExcept extends BaseExcept{
    public UserExcept(int code, String desc) {
        super(code, desc);
    }

    public UserExcept(ResultCodeUtil.ResultCode resultCode) {
        super(resultCode);
    }
}
