package tanghao.learning.test.leetcode.linkedList;

import java.util.Stack;

public class ReturnKNodeInLinkedList {

    public static void main(String[] args) {
        Node node3 = new Node(3,null);
        Node node2 = new Node(2,node3);
        Node head = new Node(1,node2);
//        System.out.println(kNode(head,2));
        System.out.println(kNodeStack(head,3));
        System.out.println(kNodeDoublePoint(head,3));
        System.out.println(kNodeDiGui(head,3));

    }

    //笨办法，2n
    private static Node kNode(Node node,int k){
        Node pre=null;Node next;
        while(node!=null){
            next = node.next;
            node.next=pre;
            pre=node;
            node = next;
        }
        Node res = null;
        for(int i=0;i<k;i++){
            if(pre==null){
                break;
            }
            res = pre;
            pre = pre.next;
        }
        return res;
    }

    private static Node kNodeDoublePoint(Node node,int k){
        Node fast = node;
        Node slow = node;
        while(fast!=null && k-->0){
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static Node kNodeStack(Node node,int k){
        Stack<Node> stack = new Stack<>();
        while (node!=null){
            stack.push(node);
            node = node.next;
        }
        while(!stack.empty()&&k-->1){
            stack.pop();
        }
        return stack.peek();
    }

    private static int size=0;

    private static Node kNodeDiGui(Node node,int k){
        if(node==null){
            return null;
        }
        Node n = kNodeDiGui(node.next,k);
        if(++size==k){
            return node;
        }
        return n;
    }
}
