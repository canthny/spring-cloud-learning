package tanghao.learning.test.leetcode.array;

import java.util.Arrays;

/**
 * 合并3个有序数组，两个为升序，一个为降序
 */
public class Combine3ArraysTest {

    public static void main(String[] args) {
        int[] arr1 = {2,4,6,8};
        int[] arr2 = {1,3,5,7};
        int[] arr3 = {9,9,9,9,9,2,1};
        int[] res = merge(arr1,arr2,arr3);
        System.out.println(Arrays.toString(res));
    }

    private static int[] merge(int[] arr1,int[] arr2,int[]arr3){
        int m=0;
        int n=0;
        int l=0;
        int cap = arr1.length+arr2.length+arr3.length;
        int[] res = new int[cap];
        int cur = 0;
        while(m<arr1.length && n<arr2.length){
            if(arr1[m]<=arr2[n]){
                res[cur++] = arr1[m++];
            }else {
                res[cur++] = arr2[n++];
            }
        }
        while(m<arr1.length){
            res[cur++] = arr1[m++];
        }
        while(n<arr2.length){
            res[cur++] = arr2[n++];
        }
        int newCur = cap-1;
        cur = cur-1;
        while(cur>=0 && l< arr3.length){
            if(res[cur]>=arr3[l]){
                res[newCur--] = res[cur--];
            }else{
                res[newCur--] = arr3[l++];
            }
        }
        while(cur>=0){
            res[newCur--] = res[cur--];
        }
        while(l<arr3.length){
            res[newCur--] = arr3[l++];
        }
        return res;
    }
}
