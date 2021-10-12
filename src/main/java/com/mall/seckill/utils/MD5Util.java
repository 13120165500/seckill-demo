package com.mall.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String SALT = "1a2b3c4d";

    public static String inputPassToRecvPass(String inputPass) {
        StringBuilder str = new StringBuilder();
        str.append(SALT.charAt(5))
                .append(SALT.charAt(1))
                .append(inputPass)
                .append(SALT.charAt(0))
                .append(SALT.charAt(6));
        return DigestUtils.md5Hex(str.toString());
    }

    public static String recvPassToDbPass(String RecvPass, String salt) {
        StringBuilder str = new StringBuilder();
        str.append(salt.charAt(5))
                .append(salt.charAt(1))
                .append(RecvPass)
                .append(salt.charAt(0))
                .append(salt.charAt(6));
        return DigestUtils.md5Hex(str.toString());
    }

    public static String inputPassToDbPass(String inputPass) {
        String s1 = inputPassToRecvPass(inputPass);
        String s2 = recvPassToDbPass(s1, "1a2b3c4d");
        return s2;
    }

    public static void main(String[] args) {
        String s1 = inputPassToRecvPass("123456");
        System.out.println(s1);
        String s2 = recvPassToDbPass(s1, "1a2b3c4d");
        System.out.println(s2);
        System.out.println(inputPassToDbPass("123456"));
    }
}
