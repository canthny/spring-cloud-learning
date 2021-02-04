package tanghao.learning.test.leetcode;

/**
 * Description： 反转链表
 * Created By tanghao on 2020/9/25
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode pre = head;
        for(int i=2;i<11;i++){
            ListNode curr = new ListNode(i);
            pre.next = curr;
            pre = curr;
        }
        print(head);

        ListNode newHead = reverseLinkedList(head);

        print(newHead);
    }

    private static ListNode reverseLinkedList(ListNode head){
        ListNode last = null;
        ListNode curr = head;
        while(curr!=null){
            ListNode tmp = curr.next;
            curr.next = last;
            last = curr;
            curr = tmp;
        }
        return last;
    }

    private static void print(ListNode node){
        ListNode temp = node;
        System.out.println(temp.val+"-");
        while (temp.next!=null){
            temp= temp.next;
            System.out.println(temp.val+"-");
        }
    }

    static class ListNode {
        Integer val;
        ListNode next;
        ListNode(int i){
            val = i;
        }
    }
}
