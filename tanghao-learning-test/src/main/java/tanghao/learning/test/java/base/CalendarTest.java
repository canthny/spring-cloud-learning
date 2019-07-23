package tanghao.learning.test.java.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description： TODO
 * Created By tanghao on 2019/7/1
 */
public class CalendarTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        System.out.println("20190719".compareTo("20190722"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2019-07-23 00:00:00");
        Date date2 = sdf.parse("2019-07-08 23:59:59");
        System.out.println(getMonthInterval(date1,date2));
    }
    private static class TestClass{
        private Integer i;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }
    }

    private static int getMonthInterval(Date date1, Date date2){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int yearInterval = year2 - year1;
        if(month2<month1 || (month2==month1 && day2<day1)){
            yearInterval--;
        }
        int monthIntervalTemp = month2+12-month1;
        /** 默认第一个日期00:00:00，第二个日期给的23:59:59 */
        if(day2-day1<-1){
            monthIntervalTemp--;
        }
        monthIntervalTemp %= 12;
        int monthInterval = Math.abs(yearInterval*12+monthIntervalTemp);
        return monthInterval;
    }
}
