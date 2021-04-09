package tanghao.learning.test.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelTraversalTest {

    public static void main(String[] args) {
        BinaryTreeNode handsonL = new BinaryTreeNode(5,null,null);
        BinaryTreeNode handsonR = new BinaryTreeNode(3,null,null);
        BinaryTreeNode sonL = new BinaryTreeNode(7,null,null);
        BinaryTreeNode sonR = new BinaryTreeNode(6,handsonL,handsonR);
        BinaryTreeNode root = new BinaryTreeNode(9,sonL,sonR);
        System.out.println(traversal(root));
    }

    private static List<List<Integer>> traversal(BinaryTreeNode root){
        if(root == null){
            return null;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while(queue.size()>0){
            List<Integer> tmp = new ArrayList<>();
            int qSize = queue.size();
            for(int i=0;i<qSize;i++){
                BinaryTreeNode cur = queue.poll();
                tmp.add(cur.val);
                if(cur.left!=null){
                    queue.add(cur.left);
                }
                if(cur.right!=null){
                    queue.add(cur.right);
                }
            }
            result.add(tmp);
        }
        return result;
    }

}
