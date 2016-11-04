package com.rofour.baseball.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

public class AxpStream {
    private static Logger logger = LoggerFactory.getLogger(AxpStream.class);

    //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String getImageStr(InputStream inputStream) {
        byte[] data=null ;
        //读取图片字节数组
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            logger.error("图片转换字符串错误:",e);
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }
}
