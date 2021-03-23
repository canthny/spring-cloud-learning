package tanghao.learning.test.leetcode.linkedList;

import java.util.HashSet;
import java.util.Set;

public class CheckLinkedListHasCycle {

    public static void main(String[] args) {
        Node node3 = new Node(3,null);
        Node node2 = new Node(2,node3);
        Node head = new Node(1,node2);
//        node3.next = head;
        System.out.println(checkHasCycle(head));
        System.out.println(checkHasCycle2(head));
    }

    private static boolean checkHasCycle(Node node){
        Node fast = node;
        Node slow = node;
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    private static boolean checkHasCycle2(Node node){
        Set<Node> set = new HashSet<>();
        while(node.next!=null){
            if(set.contains(node)){
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }
}
