package tanghao.learning.test.java.test;

import java.math.BigDecimal;
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
    }
}
