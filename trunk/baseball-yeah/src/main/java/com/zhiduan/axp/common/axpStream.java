package com.zhiduan.axp.common;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

public class axpStream {
    public static String GetImageStr(InputStream inputStream) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理

        byte[] data = null;
        //读取图片字节数组
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
}
