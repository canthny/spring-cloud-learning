package tanghao.learning.test.leetcode.stack;

import java.util.Stack;

/**
 * 字典序最小的k个数的子序列
 */
public class DictionarySortSmallestKDigitTest {

    public static void main(String[] args) {
        int[] nums = {9,2,4,5,1,2,3,0};
        int k = 3;
        for(int value:getDicSortArray(nums,k)){
            System.out.println(value);
        }
    }

    private static int[] getDicSortArray(int[] nums,int k){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums.length;i++){
            int remain = nums.length - i;
            int tempValue = nums[i];
            while(!stack.empty() && stack.size() + remain > k){
                if(tempValue>stack.peek()){
                    break;
                }else{
                    stack.pop();
                }
            }
            stack.push(tempValue);
        }
        int[] result = new int[k];
        for(int i=stack.size();i>=0;i--){
            if(i>k-1){
                continue;
            }
            result[i] = stack.pop();
        }
        return result;
    }
}
