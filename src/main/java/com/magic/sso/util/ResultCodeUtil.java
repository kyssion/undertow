package com.magic.sso.util;

public class ResultCodeUtil {

    public static ResultCode USER_ID_ERROR = new ResultCode(6001, "用户账号错误,请重试");
    public static ResultCode PASSWORD_ERROR = new ResultCode(6002, "用户密码错误请重试");
    public static ResultCode USER_ID_FORMAT_ERROR = new ResultCode(6003, "用户账号格式错误请重试");
    public static ResultCode PASSWORD_FORMAT_ERROR = new ResultCode(6004, "用户密码格式错误请重试");
    public static ResultCode PASSWORD_NOT_SAME = new ResultCode(6005, "用户两次输入密码不同请重试");
    public static ResultCode EMAIL_FORMAT_ERROR = new ResultCode(6006, "用户email邮箱格式错误请重试");
    public static ResultCode TELL_FORMAT_ERROR = new ResultCode(6007, "用户电话号码格式错误请重试");

    public static class ResultCode {

        private int code;
        private String desc;

        ResultCode() {
            super();
        }

        ResultCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}

