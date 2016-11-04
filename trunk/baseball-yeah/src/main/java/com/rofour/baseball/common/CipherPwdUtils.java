package com.rofour.baseball.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.digest.DigestUtils;

/**
* @ClassName: CipherPwdUtils
* @Description: 用户密码加密
* @author ZhangLei
* @date 2016年4月7日 下午9:49:29 
*
*/
    
public class CipherPwdUtils {
	
    public static String cipherPwd(String accountPwd) throws Exception{
    	String key1="The key is hidden and not see!";
    	String key2= "CDPTBSPTS" + key1 + key1 + "CBDPTSPTS";
    	//1.sha1加密一次
    	accountPwd=DigestUtils.sha1Hex(accountPwd).toUpperCase();
    	//2.des加密第一次
    	accountPwd=encrypt(accountPwd, DigestUtils.md5Hex(key1).toUpperCase().substring(0, 8));
    	//3.des加密第二次
    	accountPwd=encrypt(accountPwd, DigestUtils.md5Hex(key2).toUpperCase().substring(0, 8));
    	//4.前后加字符
    	accountPwd="CDPTBSPTS"+accountPwd+"CBDPTSPTS";
    	//5.md5加密一次
    	accountPwd=DigestUtils.md5Hex(accountPwd).toUpperCase();
		return accountPwd;
    }
	
	
	
	private static String encrypt(String accountPwd, String key)   
            throws Exception {   
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);   
        byte[] b=cipher.doFinal(accountPwd.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
             String plainText = Integer.toHexString(0xff & b[i]);   
             if (plainText.length() < 2)   
                 plainText = "0" + plainText;   
             hexString.append(plainText);   
         }   
         return hexString.toString().toUpperCase();   
    }   
	
}
