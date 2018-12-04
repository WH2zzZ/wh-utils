package com.wh.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);



    /**
     * String -> Date
     * @param date
     * @return
     */
    public static Date parseDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            LOGGER.warn("date parse exception : {}", date);
            return null;
        }
    }

    /**
     * date -> String
     * @param date
     * @return
     */
    public static String formateDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Date getDatOfWeekTime(int i){
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
        c.add(Calendar.DATE,-dayOfWeek + i);
        String date = format.format(c.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            LOGGER.warn("date parse exception : {}", date);
            return null;
        }
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Date now(){
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Asia/Shanghai"));
        //ZoneDateTime 转换成Date
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }

}
