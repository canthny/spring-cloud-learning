package tanghao.learning.test.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标
 */
public class TwoDataSumTest {

    public static void main(String[] args) {
        int[] array = {-1,-2,-3,-4,-5};
        int[] res = getTwoDataIndex1(array,-8);
        System.out.println(Arrays.toString(res));

    }

    private static int[] getTwoDataIndex1(int[] array,int sum){
        Map<Integer,Integer> map = new HashMap<>();
        int index = -1;
        for(int i=0;i<array.length;i++){
            if(map.containsKey(sum-array[i])){
                return new int[]{map.get(sum- array[i]),i};
            }
            map.put(array[i],i);
        }
        return null;
    }

    private static int[] getTwoDataIndex2(int[] array,int sum){
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j< array.length;j++){
                if(array[i]+array[j]==sum){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
