package tanghao.learning.test.leetcode;

/**
 * Description： 合并两个有序联表
 * Created By tanghao on 2020/8/31
 */
public class MergeTwoLinkedList {

    public static void main(String[] args) {

    }

    class ListNode{
        private int value;

        private ListNode next;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    private static ListNode merge2List(ListNode a, ListNode b){
        if(a==null)return b;
        if(b==null)return a;
        ListNode head = null;
        ListNode curr = null;
        ListNode n1 = null;
        ListNode n2 = null;

        if(a.getValue()>b.getValue()){
            head = b;
        }else{
            head = a;
        }
        curr = head;
        return head;
    }

    private static ListNode getNewListNode(ListNode a, ListNode b) {
        if(a.getValue()>b.getValue()){
            return b;
        }else{
            return a;
        }
    }
}
