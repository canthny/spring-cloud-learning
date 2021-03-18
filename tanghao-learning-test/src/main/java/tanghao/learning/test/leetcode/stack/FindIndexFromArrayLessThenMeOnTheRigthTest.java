package tanghao.learning.test.leetcode.stack;

import java.util.Stack;

/**
 * 找出数组中右边比我小的元素,单调栈
 */
public class FindIndexFromArrayLessThenMeOnTheRigthTest {

    public static void main(String[] args) {
        int[] test = {1,2,4,9,4,0,5};
        for(int res:getIndex(test)){
            System.out.println(res);
        }
    }

    private static int[] getIndex(int[] source){
        Stack<Integer> indexStack = new Stack<>();
        int[] result = new int[source.length];
        for(int i=0;i<source.length;i++){
            int tempValue = source[i];
            while(!indexStack.empty()){
                if(source[indexStack.peek()]<=tempValue){
                    break;
                }
                int index = indexStack.pop();
                result[index] = i;
            }
            indexStack.push(i);
        }
        while(!indexStack.empty()){
            int index = indexStack.pop();
            result[index] = -1;
        }
        return result;
    }
}
