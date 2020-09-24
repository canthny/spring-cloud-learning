package tanghao.learning.test.leetcode;

/**
 * Description： 斐波那契数列
 * Created By tanghao on 2020/9/24
 */
public class Fibonacci {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            System.out.println(getN(i));
        }
        for(int i=0;i<10;i++){
            System.out.println(getN_2(i));
        }
    }

    /**
     * 递归,复杂度O(2^n)
     * @param n
     * @return
     */
    private static Integer getN(int n){
        if(n>1){
            return getN(n-1)+getN(n-2);
        }else if (n==1){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 循环加，复杂度O(n)
     * @param n
     * @return
     */
    private static Integer getN_2(int n){
        Integer fn_1 = 0;
        Integer fn_2 = 1;
        Integer fn_temp = 0;
        if(n>1){
            for(int i=2;i<n+1;i++){
                fn_temp = fn_1+fn_2;
                fn_1 = fn_2;
                fn_2 = fn_temp;
            }
            return fn_temp;
        }else if (n==1){
            return 1;
        }else{
            return 0;
        }
    }
}
