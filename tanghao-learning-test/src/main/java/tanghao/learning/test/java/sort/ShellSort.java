package tanghao.learning.test.java.sort;

public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {1,5,6,4,3,8,7,9,2};
        shell(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    private static void shell(int[] array){
        int len = array.length;
        int preIndex,curr;
        for(int gap=len/2;gap>=1;gap=gap/2){
            for(int i=gap;i<len;i++){
                preIndex = i-gap;
                curr = array[i];
                while(preIndex>=0&&array[preIndex]>curr){
                    array[preIndex+gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex+gap] = curr;
            }
        }
    }
}
