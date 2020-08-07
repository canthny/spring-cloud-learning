package tanghao.learning.test.leetcode;

/**
 * Description： 获取一个数组中
 * Created By tanghao on 2019/11/5
 */
public class GetTheMostCloseNumberBelow {

    public static void main(String[] args) {
        Integer[] test = {1,3,6,12};
        Integer number = 3;
        System.out.println(getTheMostCloseNumberBelow(test,number));
    }

    private static Integer getTheMostCloseNumberBelow(Integer[] sourceArray,Integer number){
        Integer result = null;
        for(int i=0;i<sourceArray.length;i++){
            if(sourceArray[i]<=number && sourceArray[i]>(null==result?0:result)){
                result = sourceArray[i];
            }
        }
        return result;
    }
}
