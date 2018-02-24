package com.vue007.admin.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类
 *
 * @author Xiangyang
 * @date 2017-08-30 14:44
 */
public class DateUtil {

    /**
     * 未指定的日期添加天数
     * @param date 指定日期
     * @param day 天数
     * @return 新日期
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);

        return calendar.getTime();
    }

}
