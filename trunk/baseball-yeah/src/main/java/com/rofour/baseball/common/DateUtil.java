package com.rofour.baseball.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtil
{
    // private final static SimpleDateFormat sdfYear = new
    // SimpleDateFormat("yyyy");
    //
    // private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
    // "yyyy-MM-dd");
    //
    // private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
    // "yyyyMMdd");
    //
    // private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
    // "yyyy-MM-dd HH:mm:ss");
    //
    
    private final static FastDateFormat sdfYear = FastDateFormat.getInstance("yyyy");
    
    private final static FastDateFormat sdfDay = FastDateFormat.getInstance("yyyy-MM-dd");
    
    private final static FastDateFormat sdfDays = FastDateFormat.getInstance("yyyyMMdd");
    
    private final static FastDateFormat sdfTime = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 获取YYYY格式
     * 
     * @return
     */
    public static String getYear()
    {
        return sdfYear.format(new Date());
    }
    
    /**
     * 获取YYYY-MM-DD格式
     * 
     * @return
     */
    public static String getDay()
    {
        return sdfDay.format(new Date());
    }
    
    /**
     * 获取YYYYMMDD格式
     * 
     * @return
     */
    public static String getDays()
    {
        return sdfDays.format(new Date());
    }
    
    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * 
     * @return
     */
    public static String getTime()
    {
        return sdfTime.format(new Date());
    }
    
    /**
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return boolean
     * @throws @author luguosui
     */
    public static boolean compareDate(String s, String e)
    {
        if (fomatDate(s) == null || fomatDate(e) == null)
        {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }
    
    /**
     * 格式化日期
     * 
     * @return
     */
    public static Date fomatDate(String date)
    {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return fmt.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 校验日期是否合法
     * 
     * @return
     */
    public static boolean isValidDate(String s)
    {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            fmt.parse(s);
            return true;
        }
        catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }
    
    public static int getDiffYear(String startTime, String endTime)
    {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            // long aa = 0;
            int years =
                (int)(((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        }
        catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }
    
    /**
     * <li>功能描述：时间相减得到天数
     * 
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    // public static long getDaySub(String beginDateStr, String endDateStr) {
    // long day = 0;
    // java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
    // java.util.Date beginDate = null;
    // java.util.Date endDate = null;
    //
    // try {
    // beginDate = format.parse(beginDateStr);
    // endDate = format.parse(endDateStr);
    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
    // day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
    // // System.out.println("相隔的天数="+day);
    //
    // return day;
    // }
    
    /**
     * 得到n天之后的日期
     * 
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days)
    {
        int daysInt = Integer.parseInt(days);
        
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后的日期
     * 
     * @param day
     * @param daysInt
     * @return
     * @author wuzhiquan
     */
    public static String getAfterDay(Date day, int daysInt)
    {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(day);
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * 
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days)
    {
        int daysInt = Integer.parseInt(days);
        
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到月底的日期
     * 
     * @param year，month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return sdfDay.format(cal.getTime());
    }
    
    /**
     * 得到月初的日期
     * 
     * @param year，month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));
        return sdfDay.format(cal.getTime());
    }
    
    /**
     * 得到日期
     * 
     * @param strDate
     * @return
     */
    public static Date getDate(String strDate)
    {       
        try
        {
            Date date = sdfDay.parse(strDate);
            return date;
        }
        catch (ParseException e)
        {
            return new Date();
        }
        
    }
    
    /**
     * 得到日期的字符串
     * 
     * @param strDate
     * @return
     */
    public static String getDateStr(Date date)
    {
        String str = sdfDay.format(date.getTime());
        return str;
    }
    
    
    /**
     * 得到下一天的日期
     * 
     * @param strDate
     * @return
     */
    public static Date getDayAfter(String specifiedDay)
    {
        Calendar c = Calendar.getInstance();
        Date date = getDate(specifiedDay);
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);        
        String dayAfter = sdfDay.format(c.getTime());
        return getDate(dayAfter);
    }
    
    
    /**
     * 得到下一天的日期
     * 
     * @param strDate
     * @return
     */
    public static Date getDayAfter(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String dayAfter = sdfDay.format(c.getTime());
        return getDate(dayAfter);
    }
    
    /**
     * 得到前一天的日期
     * 
     * @param strDate
     * @return
     */
    public static Date getDayBefore(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);       
        String dayAfter = sdfDay.format(c.getTime());
        return getDate(dayAfter);
    }
    

  
    public static void main(String[] args)
    {
        Date curr=new Date();
        String day=getFirstDayOfMonth(curr.getYear()+1900, curr.getMonth());
        System.out.println(day);
        System.out.println(getAfterDayWeek("3"));
    }
    
}
