package jucadv.times;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author zcl2806
 * @create 2022-06-23 15:27
 */
public class TimeTest {
    @Test
    public void test1() {
        Date date = new Date();
        LocalDate localDate = LocalDate.now();
        Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate localDate1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("date = " + date);
        System.out.println("localDate1 = " + localDate1);
        System.out.println("localDate = " + localDate);
        System.out.println("date1 = " + date1);
    }

    @Test
    public void test2() {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime localDateTime1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("date = " + date);
        System.out.println("localDateTime1 = " + localDateTime1);
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("date1 = " + date1);
    }

    @Test
    public void test3() {
        Date date = new Date();
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        Date date1 = Date.from(LocalDateTime.of(localDate, localTime).atZone(ZoneId.systemDefault()).toInstant());
        LocalTime localTime1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        System.out.println("date = " + date);
        System.out.println("localTime1 = " + localTime1);
        System.out.println("localTime = " + localTime);
        System.out.println("date1 = " + date1);
    }

    @Test
    public void test4() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("localDate = " + localDate);
        System.out.println("localTime = " + localTime);
    }
}
