package pers.jess.template.common.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TokenProcessor {

    /**
     * 单例模式（保证类的对象在内存中只有一个）
     * 1 把类的构造函数私有化
     * 2 自己创建一个类的对象
     * 3 对外提供一个公有的方法，返回类的对象
     */
    private TokenProcessor(){}

    private static final TokenProcessor instance = new TokenProcessor();

    /**
     * 返回类对象
     * @return
     */
    private static TokenProcessor getInstance(){
        return instance;
    }


    /**
     * 生成token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public static String createToken(){
        //  7346734837483  834u938493493849384  43434384
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }


    public static String createToken(String str){
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(str.getBytes());
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        System.out.println(createToken("oS8a0jkXuNBTejx7nV7Vx2LmDlNM"));
    }
}
