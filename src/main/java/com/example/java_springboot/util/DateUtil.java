package com.example.java_springboot.util;


import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 时间工具类
 * <p>
 * 推荐使用LocalDateTime
 */
@Slf4j
public class DateUtil {

    private static ZoneId zoneId = ZoneId.systemDefault();

    public static final String YYYY_MM_DD="yyyyMMdd";

    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),


    /**
     * 获取当天最早时间
     *
     * @param date
     * @return
     */
    public static Date getDateBegin(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天最晚时间
     *
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 获取某月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(Integer year, Integer month) {
        if (Objects.isNull(year)){
            year=LocalDateTime.now().getYear();
        }
        if (Objects.isNull(month)){
            month=LocalDateTime.now().getMonthValue();
        }
        LocalDateTime dateTime = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endLocalDateTime = LocalDateTime.of(LocalDate.from(dateTime.with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
        return DateUtil.formatTime(endLocalDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取某月第一天，可规定输出格式
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonthByPattern(Integer year, Integer month,String pattern) {
        if (Objects.isNull(year)){
            year=LocalDateTime.now().getYear();
        }
        if (Objects.isNull(month)){
            month=LocalDateTime.now().getMonthValue();
        }
        LocalDateTime dateTime = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endLocalDateTime = LocalDateTime.of(LocalDate.from(dateTime.with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
        return DateUtil.formatTime(endLocalDateTime, pattern);
    }


    /**
     * 获取某月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(Integer year, Integer month) {
        if (Objects.isNull(year)){
            year=LocalDateTime.now().getYear();
        }
        if (Objects.isNull(month)){
            month=LocalDateTime.now().getMonthValue();
        }
        LocalDateTime dateTime = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endLocalDateTime = LocalDateTime.of(LocalDate.from(dateTime.with(TemporalAdjusters.lastDayOfMonth())), LocalTime.MAX);
        return DateUtil.formatTime(endLocalDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当月 天数
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDayNum(Integer year, Integer month) {
        if (Objects.isNull(year)){
            year=LocalDateTime.now().getYear();
        }
        if (Objects.isNull(month)){
            month=LocalDateTime.now().getMonthValue();
        }
        LocalDateTime dateTime = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endLocalDateTime = LocalDateTime.of(LocalDate.from(dateTime.with(TemporalAdjusters.lastDayOfMonth())), LocalTime.MAX);
        return endLocalDateTime.getDayOfMonth();
    }

    /**
     * 获取传入日期是本月的几号
     * @param date
     * @return
     */
    public static int getMonthDayNumByDate(Date date){
        if (date==null){
            return -1;
        }
        // toInstant 格式化时间  systemDefault 设置时区
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.getDayOfMonth();
    }

    /**
     * 给定的时间 + days天
     * @param days
     * @return
     */
    public static Date getDateByAddDays(Date date,int days) {
        LocalDateTime localDateTime = convertDateToLDT(date);
        LocalDateTime plusDate = localDateTime.plusDays(days);
        return convertLDTToDate(plusDate);
    }


    /**
     * 获取 一个月 第一天 是星期几
     * @param year
     * @param month
     * @return
     */
    public static int getFirstDayWeekValueOfMonth(Integer year, Integer month) {
        if (Objects.isNull(year)){
            year=LocalDateTime.now().getYear();
        }
        if (Objects.isNull(month)){
            month=LocalDateTime.now().getMonthValue();
        }
        LocalDateTime dateTime = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endLocalDateTime = LocalDateTime.of(LocalDate.from(dateTime.with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
        return endLocalDateTime.getDayOfWeek().getValue();
    }

    /**
     * 判断 时间范围 区间 在区间内时返回区间的值
     * @param startTime  标准值 1-1
     * @param endTime  标准值 1-31
     * @param source  目标值 1-10
     * @return
     */
    public static Date getDateRangOfDate(Date source, Date startTime, Date endTime) {
        if (Objects.isNull(source)){
            source=endTime;
        }
        if (source.getTime() <= startTime.getTime()) {
            return startTime;
        } else if (source.getTime() >= endTime.getTime()){
            return endTime;
        }else {
            return source;
        }
    }


    /**
     * 将时间戳转换为时间
     * @param time
     * @return
     */
    public static Date timestampToDate(long time){
        return new Date(time);
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的毫秒
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     * @param time
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    // todo 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    // todo 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
    }

    //获取一天的开始时间，2017,7,22 00:00
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    //获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }


    /**
     * 获取当前时间点（毫秒）
     *
     * @author Bryan Wang
     */
    public static Long getNowTimeInMillis() {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Timestamp getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Timestamp currentTime = new Timestamp(cal.getTimeInMillis());
        return currentTime;
    }

    /**
     * 获取当前时间并保存为yyyy-MM-dd 24hh:mm:ss
     *
     * @return
     */
    public static String getDateYMDHMS(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间并保存为yyyy-MM-dd
     *
     * @return
     */
    public static String getDateYMD(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间并保存为yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentYMD() {
        Calendar cal = Calendar.getInstance();
        Timestamp currentTime = new Timestamp(cal.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(currentTime);
    }

    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static Date parseDate(String date, String parseStr) {
        Date ddate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            ddate = f.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ddate;
    }

    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static Timestamp parseTimeStamp(String date, String parseStr) {
        Date ddate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            ddate = f.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Timestamp(ddate.getTime());
    }

    /**
     * @param date, parseStr
     * @return java.lang.Long
     * @description 将字符串日期变成类型
     * @author Bryan Wang
     * @date 2019/11/7 17:33
     */
    public static Long parseTimestamp2Long(String date, String parseStr) {
        Date ddate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            ddate = f.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ddate.getTime();
    }

    public static String parseLong2Str(Long timeLong, String parseStr) {
        Date ddate = new Date(timeLong);
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        return f.format(ddate);
    }

    /**
     * 获得当前的年
     */
    public static Integer getNowYear() {
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        return year;
    }


    /**
     * 返回格式化日期
     *
     * @param date     日期
     * @param parseStr 格式化串
     * @return 返回格式串
     */
    public static String formatDate(Date date, String parseStr) {
        String sdate = null;
        SimpleDateFormat f = new SimpleDateFormat(parseStr);
        try {
            sdate = f.format(date);
        } catch (Exception _ex) {
            log.info("日期转字符串异常：{}",_ex.getMessage());
        }
        return sdate;
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static String getTwoDay(String date1, String date2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(date1);
            Date mydate = myFormatter.parse(date2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }
    public static Date getExpirestime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.parse("2999-01-01");
    }
    public static Date endDate(Date date,int num){
        try{
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.DATE,num);
           return ca.getTime();
        }catch(Exception e){
            return null;
        }
    }

    //获取给定日期是本月的第几天
    public static int getDateMouthDay(Date today){
        Calendar calendar = Calendar.getInstance();
        //时间，可以为具体的某一时间
        calendar.setTime(today);
        int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
        return monthDay;
    }
    //获取时间是本周的第几天
    public static int getDateWeekDay(Date today){
        Calendar calendar = Calendar.getInstance();
        //时间，可以为具体的某一时间
        calendar.setTime(today);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == 1) {
            weekDay = 7;
        } else {
            weekDay = weekDay - 1;
        }
        return weekDay;
    }


    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */

    public static int daysBetween(Date smdate,Date bdate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意三个参数的时间格式要一致
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return 在时间段内返回true，不在返回false
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * 计算2个日期之间相差的  相差多少年
     * 比如：2011-02-02 到  2017-03-02 相差 6年，
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int dayComparePrecise(Date fromDate,Date toDate){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int toYear = to.get(Calendar.YEAR);
        int year = toYear  -  fromYear;
        return year;
    }

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public static Date formatDate(LocalDate date){
        ZonedDateTime zonedDateTime = date.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
}
