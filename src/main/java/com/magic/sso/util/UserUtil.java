package com.magic.sso.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magic.sso.bean.User;
import com.magic.sso.dao.UserDao;
import com.magic.sso.except.BaseExcept;
import io.undertow.server.HttpServerExchange;
import org.apache.ibatis.session.SqlSession;

import java.util.Deque;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UserUtil {

    public static User createUserForRegister(Map<String, Deque<String>> params) throws BaseExcept {
        String userId = isUserIdFormatCorrect(params.get("userId").getFirst());

        String password_f = params.get("passwordFir").getFirst();
        String password_e = params.get("passwordEnd").getFirst();

        if (password_e == null || password_f == null) {
            throw new BaseExcept(ResultCodeUtil.PASSWORD_FORMAT_ERROR);
        }

        if (password_f.equals(password_e)) {
            password_f = isPasswordFormatCorrect(password_f);
        }

        long userYear = isUserYearFormatCorrect(params.get("userYear").getFirst());
        String userTell = isTellFormatCorrect(params.get("tell").getFirst());
        String email = isEmailFormatCorrect(params.get("email").getFirst());

        return creatUser(userId, PasswordHashUtil.passwordToHash(password_f),
                userYear, userTell, email);
    }

    private static long isUserYearFormatCorrect(String userYear) throws BaseExcept {
        try {
            return Long.valueOf(userYear);
        } catch (Exception e) {
            throw new BaseExcept(ResultCodeUtil.USER_YEARS_FORMAT_ERROR);
        }
    }

    public static User creatUser(String userId, String password, long userYear, String tell, String email) {
        return new User(userId, password, userYear, tell, email);
    }

    public static String isUserIdFormatCorrect(String userId) throws BaseExcept {
        if (userId == null || userId.length() <= 4) {
            throw new BaseExcept(ResultCodeUtil.USER_ID_FORMAT_ERROR);
        }
        return userId;
    }

    public static String isPasswordFormatCorrect(String password) throws BaseExcept {
        if (password.length() < 12 || !isStartWithUppercase(password) || !isOnlyHasWord(password.toCharArray())) {
            throw new BaseExcept(ResultCodeUtil.PASSWORD_FORMAT_ERROR);
        }
        return password;
    }

    public static boolean isStartWithUppercase(String password) throws BaseExcept {
        if (password == null || "".equals(password)) {
            throw new BaseExcept(ResultCodeUtil.PASSWORD_FORMAT_ERROR);
        }
        char fist = password.charAt(0);
        return fist >= 'A' && fist <= 'Z';
    }

    /**
     * 是否是数组和字符组合
     *
     * @param passwordChars
     * @return
     */
    public static boolean isOnlyHasWord(char[] passwordChars) {
        boolean hasChar = false;
        boolean hasNumber = false;
        for (char passwordChar : passwordChars) {
            if (!hasChar && (passwordChar >= 'a' && passwordChar <= 'z' || passwordChar >= 'A' && passwordChar <= 'Z')) {
                hasChar = true;
            }
            if (!hasNumber && (passwordChar >= '0' && passwordChar <= '9')) {
                hasNumber = true;
            }
        }
        return hasChar && hasNumber;
    }

    public static String isEmailFormatCorrect(String email) throws BaseExcept {
        if (!(Pattern.matches("\\w+@(\\w+.)+[a-z]{2,3}", email))) {
            throw new BaseExcept(ResultCodeUtil.EMAIL_FORMAT_ERROR);
        }
        return email;
    }

    public static String isTellFormatCorrect(String tell) throws BaseExcept {
        if (!(isChinaPhoneLegal(tell) || isHKPhoneLegal(tell))) {
            throw new BaseExcept(ResultCodeUtil.TELL_FORMAT_ERROR);
        }
        return tell;
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static User getUserForLogin(Map<String, Deque<String>> exchange) throws BaseExcept {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            String userId = exchange.get("user_id").getFirst();
            String password = exchange.get("password").getFirst();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.findUserByIdAndPassword(userId, PasswordHashUtil.passwordToHash(password));
            return user;
        } catch (Exception e) {
            throw new BaseExcept(ResultCodeUtil.SYSTEM_ERROR);
        } finally {
            sqlSession.close();
        }
    }

    public static int insertLoginInfo(User user){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.deleteAllLoginInfo(user.getUserId());
            return userDao.insertLoginInfo(user.getUserId(),user.getToken(),System.currentTimeMillis());
        }finally {
            sqlSession.close();
        }
    }

    public static void deleteLoginInfo(String user_id, HttpServerExchange exchange) throws JsonProcessingException {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.deleteAllLoginInfo(user_id);
            CookieUtil.deleteCookie(user_id,exchange);
        }finally {
            exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, ResultCodeUtil.SYSTEM_ERROR));
        }
    }
}
