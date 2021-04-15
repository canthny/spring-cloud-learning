package tanghao.learning.test.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelTraversalTest {

    public static void main(String[] args) {
        TreeNode handsonL = new TreeNode(5,null,null);
        TreeNode handsonR = new TreeNode(3,null,null);
        TreeNode sonL = new TreeNode(7,null,null);
        TreeNode sonR = new TreeNode(6,handsonL,handsonR);
        TreeNode root = new TreeNode(9,sonL,sonR);
        System.out.println(traversal(root));
    }

    private static List<List<Integer>> traversal(TreeNode root){
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while(queue.size()>0){
            List<Integer> tmp = new ArrayList<>();
            int qSize = queue.size();
            for(int i=0;i<qSize;i++){
                TreeNode cur = queue.poll();
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
