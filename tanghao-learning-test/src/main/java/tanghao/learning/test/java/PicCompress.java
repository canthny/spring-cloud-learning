package tanghao.learning.test.java;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description： TODO
 * Created By tanghao on 2019/7/26
 */
public class PicCompress {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String test = sdf.format(new Date()) ;
        System.out.println(test);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int month = cal.get(Calendar.MONTH )+1;
    }
}
