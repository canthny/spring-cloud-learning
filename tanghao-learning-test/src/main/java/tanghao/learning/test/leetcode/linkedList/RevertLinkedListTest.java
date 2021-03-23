package tanghao.learning.test.leetcode.linkedList;

/**
 * 反转链表
 */
public class RevertLinkedListTest {

    public static void main(String[] args) {
        Node node3 = new Node(3,null);
        Node node2 = new Node(2,node3);
        Node head = new Node(1,node2);
        Node node = revert(head);
        while(node.next!=null){
            System.out.print(node.val);
            node = node.next;
        }
        System.out.print(node.val);
    }

    private static Node revert(Node node){
        if(node==null){
            return null;
        }
        Node next=null,pre=null;
        while(node.next!=null){
            next = node.next;
            node.next=pre;
            pre = node;
            node = next;
        }
        node.next=pre;
        return node;
    }
}
