package tanghao.learning.test.java.base;

/**
 * Description： TODO
 * Created By tanghao on 2019/7/23
 */
public class IntegerCompareTest {

    public static void main(String[] args) {
        Integer a1 = 127;
        Integer b1 = 127;

        Integer a2 = 128;
        Integer b2 = 128;

        int a3 = 1000;
        Integer b3 = 1000;

        int a4 = 1000;
        Integer b4 = new Integer(1000);

        Integer a5 = new Integer(1000);
        Integer b5 = new Integer(1000);

        System.out.println(a1==b1);
        System.out.println(a2==b2);
        System.out.println(a3==b3);
        System.out.println(a4==b4);
        System.out.println(a5==b5);
    }
}
