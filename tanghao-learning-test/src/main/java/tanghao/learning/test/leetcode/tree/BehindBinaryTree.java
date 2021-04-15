package tanghao.learning.test.leetcode.tree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BehindBinaryTree {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode handsonL = new TreeNode(5,null,null);
        TreeNode handsonR = new TreeNode(3,null,null);
        TreeNode sonL = new TreeNode(7,null,null);
        TreeNode sonR = new TreeNode(6,handsonL,handsonR);
        TreeNode root = new TreeNode(9,sonL,sonR);
        behind(root);
        System.out.println(JSONObject.toJSONString(list));
    }

    private static void behind(TreeNode root){
        if(root==null){
            return;
        }
        if(root.left!=null){
            behind(root.left);
        }
        if(root.right!=null){
            behind(root.right);
        }
        list.add(root.val);
    }
}
