package tanghao.learning.test.leetcode.tree;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindTreeNodeSumTest {

    private static int stackSum = 0;


    public static void main(String[] args) {

    }

    private static List<List<BinaryTreeNode>> getNodeEqualSum(BinaryTreeNode node,int sum){
        Stack<BinaryTreeNode> stack = new Stack<>();
        List<List<BinaryTreeNode>> res = new ArrayList<>();
        List<BinaryTreeNode> temp = push(stack,node,sum);
        if(!CollectionUtils.isEmpty(temp)){
            res.add(temp);
            return res;
        }
        while(!stack.empty()){
            BinaryTreeNode curr = stack.peek();
            if(curr.right==null&&curr.left==null){
                stack.pop();
            }
        }
        return null;
    }

    private static  List<BinaryTreeNode> push(Stack<BinaryTreeNode> stack,BinaryTreeNode node,int sum){
        stack.push(node);
        stackSum+=node.val;
        if(stackSum==sum){
            while(!stack.empty()){
                //输出栈中的所有元素到list，但是栈不能动，copy一个出来
            }
        }
        return null;
    }
}
