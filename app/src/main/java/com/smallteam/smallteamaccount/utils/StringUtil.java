package com.smallteam.smallteamaccount.utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 验证码长度
     */
    private static final int VERIFY_CODE_LENGTH = 6;
    /**
     * 密码最低长度
     */
    private static final int PASSWORD_MIN_LENGTH = 6;
    /**
     * 密码 正则
     */
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[A-z]).{6,18}$";
    /**
     * 手机号正则
     */
    private static final String PHONE_REGEX = "[1][3,4,5,7,8][0-9]{9}";

    /**
     * 验证码正则
     */
    private static final String VERIFY_CODE_REGEX = "[0-9]{6}";
    /**
     * 数字正则模型
     */
    private static Pattern pattern = Pattern.compile("[0-9]*");

    /**
     * 判断是否为null或空字符串
     *
     * @param str
     * @return
     * @author zsp
     * @createTime 2016/08/30
     * @lastModify 2016/08/30
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String... str) {
        if (str == null) {
            return true;
        }
        for (String s : str) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否不为null或不是空字符串
     *
     * @param str
     * @return
     * @author zsp
     * @createTime 2016/08/30
     * @lastModify 2016/08/30
     */
    public static boolean isNotEmpty(String str) {
        if (str == null ||"".equals(str.trim())) {
            return false;
        }
        return true;
    }

    /**
     * 根据类名获取对象实例
     *
     * @param className 类名
     * @return
     * @author zsp
     * @createTime 2016/08/30
     * @lastModify 2016/08/30
     */
    public static Object getObject(String className) {
        Object object = null;
        if (StringUtil.isNotEmpty(className)) {
            try {
                object = Class.forName(className).newInstance();
            } catch (ClassNotFoundException cnf) {
            } catch (InstantiationException ie) {
            } catch (IllegalAccessException ia) {
            }
        }
        return object;
    }

    /**
     * 字符串是否数字
     *
     * @param
     * @return
     * @author zsp
     * @createTime 2016/11/17
     * @lastModify 2016/11/17
     */
    public static boolean strIsNum(String str) {
        // 判断是否为空
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        // 去空格
        str = str.trim();
        // 匹配

        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 6位以上，字母+数字组合6位以上，字母+数字组合
     */
    public static boolean strIsPassword(Context context, String str) {
        // 判断是否为空
        if (StringUtil.isEmpty(str)) {
            EasyToast.showShort(context, "密码不能为空");
            return false;
        }
        // 去空格
        str = str.trim();
        if (str.length() < PASSWORD_MIN_LENGTH) {
            EasyToast.showShort(context, "密码不符合规则");
            return false;
        }
        if (str.matches(PASSWORD_REGEX)) {
            return true;
        }
        EasyToast.showShort(context, "密码不符合规则");
        return false;
    }

    /**
     * 字符串是否是手机号码
     */
    public static boolean strIsPhone(Context context, String str) {
        // 判断是否为空
        if (StringUtil.isEmpty(str)) {
            EasyToast.showShort(context, "手机号不能为空");
            return false;
        }
        // 去空格
        str = str.trim();
        // 匹配
        if (str.matches(PHONE_REGEX)) {
            return true;
        }
        EasyToast.showShort(context, "请输入正确的手机号");
        return false;
    }

    /**
     * 字符串是否是验证码
     */
    public static boolean strIsVerifiCode(Context context, String str) {
        // 判断是否为空
        if (StringUtil.isEmpty(str)) {
            EasyToast.showShort(context, "验证码不能为空");
            return false;
        }
        // 去空格
        str = str.trim();
        // 匹配
        if (str.matches(VERIFY_CODE_REGEX)) {
            return true;
        }
        EasyToast.showShort(context, "请输入6位验证码");
        return false;
    }
}