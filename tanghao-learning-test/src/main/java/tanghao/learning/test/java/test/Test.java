package tanghao.learning.test.java.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    private static String getCompareToLast(Object curr,Object last){
        String result = Constant.NULL;
        try{
            if(last==null||curr==null){
                return result;
            }
            if(curr instanceof Long){
                BigDecimal currTemp = new BigDecimal((Long)curr);
                BigDecimal lastTemp = new BigDecimal((Long)last);
                if(lastTemp.compareTo(BigDecimal.ZERO)==0){
                    return result;
                }
                return currTemp.subtract(lastTemp).divide(lastTemp,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString() + Constant.PERCENT;
            }else if(curr instanceof BigDecimal){
                BigDecimal lastTemp = (BigDecimal)last;
                if(lastTemp.compareTo(BigDecimal.ZERO)==0){
                    return result;
                }
                return ((BigDecimal)curr).subtract(lastTemp).divide(lastTemp,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString() + Constant.PERCENT;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

//    public static void main(String[] args) {
//        System.out.println(getCompareToLast(22L,21L));;
//    }

    public static void main(String[] args) {
        testPage();
        SortedSet<Date> dateSet = new TreeSet<>(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });
        dateSet.add(new Date());
        dateSet.add(DateUtil.getBeforeDate(new Date(),1));
        dateSet.add(DateUtil.getBeforeDate(new Date(),-1));


        Iterator<Date> it = dateSet.iterator();
        while (it.hasNext()) {
            Date temp = it.next();
            System.out.println(DateUtil.dateToString(temp,DateUtil.DATE_FORMAT));
        }
    }

    private static void testPage(){
        Date start = DateUtil.getDateOfStart(DateUtil.stringToDate("2021-01-25",DateUtil.DATE_FORMAT));
        Date end = DateUtil.getDateOfEnd(DateUtil.stringToDate("2021-02-25",DateUtil.DATE_FORMAT));
        long distance = DateUtil.getDistanceDay(start,end);
        int pageSize = 10;
        int pageNum = 2;
        long totalCount = distance + 1;
        long pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize + 1;
        end = DateUtil.getBeforeDate(end,(pageNum-1)*pageSize);
        Date temp =  DateUtil.getBeforeDate(end,pageSize-1);
        start = start.compareTo(temp)>0?start:temp;

        System.out.println("Start="+start);
        System.out.println("end="+end);
    }

    private static class DateUtil{

        public static final String DATE_FORMAT = "yyyy-MM-dd";
        public static Date getBeforeDate(Date nowDate, Integer beforeNum) {
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(nowDate);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -beforeNum);  //设置为前beforeNum天
            return calendar.getTime();   //得到前beforeNum天的时间
        }

        public static String dateToString(Date date, String pattern) {
            String dateTime = "";
            try {
                SimpleDateFormat sf = new SimpleDateFormat(pattern);
                dateTime = sf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dateTime;
        }

        public static Date getDateOfStart(Date time) {
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(time);        //把当前时间赋给日历
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return calendar.getTime();   //得到当天的时间0点日期
        }

        public static Date getDateOfEnd(Date time) {
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(time);        //把当前时间赋给日历
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();   //得到当天的时间23点59分59秒
        }
        public static long getDistanceDay(Date startTime, Date endTime) {
            long days = 0;
            try {
                long time1 = startTime.getTime();
                long time2 = endTime.getTime();
                long diff;
                if (time1 < time2) {
                    diff = time2 - time1;
                } else {
                    diff = time1 - time2;
                }
                days = diff / (1000 * 60 * 60 * 24);
            } catch (Exception e) {
            }
            return days;
        }
        public static Date stringToDate(String dateString, String pattern) {
            Date date = null;
            try {
                SimpleDateFormat sf = new SimpleDateFormat(pattern);
                date = sf.parse(dateString);
            } catch (ParseException e) {
            }
            return date;

        }
    }
}
