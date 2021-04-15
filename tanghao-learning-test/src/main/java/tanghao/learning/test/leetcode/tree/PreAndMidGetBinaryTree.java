package tanghao.learning.test.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class PreAndMidGetBinaryTree {

    static Map<Integer,Integer> indexMap = new HashMap<>();

    public static void main(String[] args) {
        int[] pre = {3,9,20,15,7};
        int[] mid = {9,3,15,20,7};
        System.out.print(getTree(pre,mid));
    }

    private static TreeNode getTree(int[] pre,int[] mid){
        for(int i=0;i<mid.length;i++){
            indexMap.put(mid[i],i);
        }
        return getTree(pre,mid,0,pre.length-1,0, mid.length-1);
    }

    private static TreeNode getTree(int[] pre,int[] mid,int pre1,int pre2,int mid1,int mid2){
        if (pre1 > pre2) {
            return null;
        }
        int midIndex = indexMap.get(pre[pre1]);
        TreeNode root = new TreeNode(pre[pre1]);
        int preSize = midIndex-mid1;
        root.left = getTree(pre,mid,pre1+1,pre1+preSize,mid1,midIndex-1);
        root.right = getTree(pre,mid,pre1+preSize+1,pre2,midIndex+1,mid2);
        return root;
    }
}
