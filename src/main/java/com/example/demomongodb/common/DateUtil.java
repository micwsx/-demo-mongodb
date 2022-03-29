package com.example.demomongodb.common;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author: Michael
 * @date: 3/28/2022 3:32 PM
 */
public class DateUtil {

    private DateUtil() {
    }

    /**
     * UTC/GMT -> local date
     *
     * @param dateText - e.g. "2022-09-09T04:10:02.013+00:00"
     * @return
     */
    public static Date getDateFromText(String dateText) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateText, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZoneId defaultZoneID = ZoneId.systemDefault();
        System.out.println(defaultZoneID);
        Date date = Date.from(localDateTime.atZone(defaultZoneID).toInstant());
        return date;
    }


    /***
     *
     * @param dateText yyyy-MM-dd'T'HH:mm:ss'Z', "2022-09-09T04:10:02.013+00:00";
     * @param pattern
     * @return
     */
    public static Date getUTC(String dateText,String pattern) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateText);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getUTCToLocalTimeText(String dateText) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateText, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime utcZoneDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        System.out.println(utcZoneDateTime.getZone().getId());
        Date date = Date.from(utcZoneDateTime.toInstant());
        return date;
    }

    public static String formatDate(Date date, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        try{
            if (StringUtils.hasLength(pattern)) {
                return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
            }
        }catch (DateTimeException ex){
            // do nothing.
        }
        return localDateTime.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     *
     * @param dateText
     * @param pattern - e.g. “yyyy-MM-dd'T'HH:mm:ss.SSSXXX”
     * @return
     */
    public static Date getDate(String dateText, String pattern) {
       SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    public static Set<String> getAllZoneIds() {
        return ZoneId.getAvailableZoneIds();
    }


    public static void main(String[] args) {
        String dateText = "2022-09-09T04:10:02.013+00:00";
        System.out.println("utc: "+getDate(dateText,"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));

        Date localDate = getDateFromText(dateText);
        System.out.println(localDate); //Fri Sep 09 04:10:02 CST 2022


//        Date utcOrGMTDateFromText = getUTCOrGMTDateFromText(dateText);
//        System.out.println("utcOrGMTDateFromText: "+utcOrGMTDateFromText);//Fri Sep 09 12:10:02 CST 2022
//
//        System.out.println(formatDate(utcOrGMTDateFromText,"yyyy-MM-dd"));
//
//        String dateText2 = "2022-09-09 04:10:02";
//
//        Date utc = getUTC(dateText2, "yyyy-MM-dd hh:mm:ss");
//        System.out.println(utc);
//
//        LocalDateTime localDateTime = LocalDateTime.parse("2022-09-09T04:10:02.013+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        System.out.println(localDateTime);
    }

}
