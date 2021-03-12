package tanghao.learning.test.java.nio;

public class tas {

    public static void main(String[] args) {
        System.out.println(getLongSub(null,null));
    }

    private  static  Long getLongSub(Long long1,Long long2){
        Long temp1 = null;
        Long temp2 = null;
        if(long1 == null){
            temp1 = 0L;
        }
        if(long2 == null){
            temp2 = 0L;
        }
        return temp1-temp2;
    }
}
