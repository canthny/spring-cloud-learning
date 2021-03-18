package tanghao.learning.test.leetcode.stack;

import java.util.Stack;

/**
 * 大鱼吃小鱼
 */
public class BigFishEatSmallFishTest {

    public static void main(String[] args) {
        int[] fishValue = {4,3,2,1,5};
        int[] fishDirection = {1,1,1,1,0};
        System.out.println(eat(fishValue,fishDirection));
    }

    private static int eat(int[] fishValue,int[] fishDirection){
        int fishNum = fishValue.length;
        if(fishNum<=1){
            return fishNum;
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<fishValue.length;i++){
            int tempValue = fishValue[i];
            int tempDirection = fishDirection[i];
            boolean eat = false;
            while(!stack.empty() && fishDirection[stack.peek()]==1 && tempDirection==0){
                if(tempValue<fishValue[stack.peek()]){
                    eat = true;
                    break;
                }
                stack.pop();
            }
            if(!eat){
                stack.push(i);
            }
        }
        return stack.size();
    }
}
