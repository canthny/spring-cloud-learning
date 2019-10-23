package tanghao.learning.test.leetcode;

/**
 * Description： 盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * Created By tanghao on 2019/10/23
 */
public class TheMostWaterContainer {

    public static void main(String[] args) {
        Integer[] arrays = {1,8,6,2,5,4,8,3,7};
        System.out.println(methodViolence(arrays));
    }

    /**
     * 暴力破解
     * @param arrays
     * @return
     */
    private static int methodViolence(Integer[] arrays){
        int max = 0;
        for(int i=0;i<arrays.length;i++){
            for(int j=arrays.length-1;j>i;j--){
                int temp;
                if(arrays[i]<=arrays[j]){
                    temp = arrays[i]*(j-i);
                }else{
                    temp = arrays[j]*(j-i);
                }
                if(max<temp){
                    max = temp;
                }
            }
        }
        return max;
    }

    private static int method2(Integer[] arrays){
        for(int i=0;i<arrays.length;i++){

        }
        return 0;
    }
}
