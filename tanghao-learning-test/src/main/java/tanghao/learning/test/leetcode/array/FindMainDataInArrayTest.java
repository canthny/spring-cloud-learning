package tanghao.learning.test.leetcode.array;

/**
 * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 */
public class FindMainDataInArrayTest {

    public static void main(String[] args) {
        int[] array1 = {1,2,5,9,5,9,5,5,5};
        int[] array2 = {3,2};
        System.out.println(majorityElement(array1));
        System.out.println(majorityElement(array2));

    }

    private static int majorityElement(int[] nums){
        int tmp,i=0;
        while(i<(nums.length+1)/2-1){
            if(nums[i]<nums[i+1]){
                tmp = nums[i=1];
                nums[i+1] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }
}
