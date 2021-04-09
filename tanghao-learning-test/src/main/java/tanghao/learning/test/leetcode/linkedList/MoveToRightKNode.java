package tanghao.learning.test.leetcode.linkedList;

/**
 * 向右旋转列表
 */
public class MoveToRightKNode {

    public static void main(String[] args) {
        Node node3 = new Node(3,null);
        Node node2 = new Node(2,node3);
        Node head = new Node(1,node2);
        System.out.println(moveK(head,7));
    }
    private static Node moveK(Node node,int k){
        Node head = node;
        Node tail = node;
        int size = 1;
        while(tail.next!=null){
            tail = tail.next;
            size++;
        }
        tail.next = head;
        int move = size-k%size;
        while(move-->0){
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }
}
