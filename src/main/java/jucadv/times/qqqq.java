package jucadv.times;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author zcl2806
 * @create 2022-06-23 15:10
 */
public class qqqq {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getMonthValue());
        LocalDate localDate1 = localDate.plusMonths(7);
        System.out.println(localDate1.getMonthValue());
        System.out.println(localDate1.getYear());
        Date date = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        LocalDate localDate2 = date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
