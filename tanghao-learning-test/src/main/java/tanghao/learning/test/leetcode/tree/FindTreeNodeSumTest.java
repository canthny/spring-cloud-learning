package tanghao.learning.test.leetcode.tree;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;


public class FindTreeNodeSumTest {

    static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> path = new ArrayList();

    public static void main(String[] args) {
        TreeNode handsonL = new TreeNode(5,null,null);
        TreeNode handsonR = new TreeNode(3,null,null);
        TreeNode sonL = new TreeNode(7,null,null);
        TreeNode sonR = new TreeNode(6,handsonL,handsonR);
        TreeNode root = new TreeNode(9,sonL,sonR);
        getNodeEqualSum(root,16);
        System.out.println(JSONObject.toJSONString(result));
    }

    private static void getNodeEqualSum(TreeNode node, int sum){
       if(node==null){
           return;
       }
       if(node.right==null && node.left==null && sum== node.val){
           path.add(node.val);
           result.add(new ArrayList<>(path));
           path.remove(path.size()-1);
           return;
       }
       path.add(node.val);
       getNodeEqualSum(node.left,sum- node.val);
       getNodeEqualSum(node.right,sum- node.val);
       path.remove(path.size()-1);
    }

}
