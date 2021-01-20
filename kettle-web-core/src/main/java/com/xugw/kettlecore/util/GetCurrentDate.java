package com.xugw.kettlecore.util;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Title: 获取当前时间
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/13 - 10:48
 */
public class GetCurrentDate {

    public static Timestamp getCurrentsqlDate(){
        // 保证线程安全
        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Timestamp timeStamp = new Timestamp(date.getTime());
        return timeStamp;
    }
}
