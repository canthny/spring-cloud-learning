package tanghao.learning.test.leetcode.tree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MidBinaryTree {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode handsonL = new TreeNode(5,null,null);
        TreeNode handsonR = new TreeNode(3,null,null);
        TreeNode sonL = new TreeNode(7,null,null);
        TreeNode sonR = new TreeNode(6,handsonL,handsonR);
        TreeNode root = new TreeNode(9,sonL,sonR);
        mid(root);
        System.out.println(JSONObject.toJSONString(list));
    }

    private static void mid(TreeNode root){
        if(root==null){
            return;
        }
        if(root.left!=null){
            mid(root.left);
        }
        list.add(root.val);
        if(root.right!=null){
            mid(root.right);
        }
    }
}
