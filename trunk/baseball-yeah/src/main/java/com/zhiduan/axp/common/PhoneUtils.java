package com.zhiduan.axp.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: PhoneUtils
 * @Description: 电话工具类
 * @author 张雷
 * @date 2016年4月19日 下午4:08:53
 */
public class PhoneUtils {

   /**
    * @Description: 是否是手机
    * @param str
    * @return true or false
    */
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    
    /**
     * @Description: 是否是电话
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {   
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(str.length() >9)  
        {   m = p1.matcher(str);  
            b = m.matches();    
        }else{  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }  
}
