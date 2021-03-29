package tanghao.learning.test.leetcode.linkedList;

public class Combine2LinkedList {

    public static void main(String[] args) {
        Node node7 = new Node(7,null);
        Node node2 = new Node(3,node7);
        Node head = new Node(1,node2);
        Node node6 = new Node(6,null);
        Node node5 = new Node(5,node6);
        Node head2 = new Node(2,node5);
        Node node = combine(head,head2);
        while(node.next!=null){
            System.out.print(node.val);
            node = node.next;
        }
        System.out.print(node.val);
    }

    private static Node combine(Node node1,Node node2){
        Node curr=null,pre = null,cur1=node1,cur2=node2;
        while(cur1!=null && cur2!=null){
            if(cur1.val<=cur2.val){
                if(pre!=null)pre.next = cur1;
                pre = cur1;
                cur1 = cur1.next;
            }else{
                if(pre!=null)pre.next = cur2;
                pre = cur2;
                cur2 = cur2.next;
            }
            if(curr==null){
                curr = pre;
            }
        }
        if(pre==null){
            return cur1==null?cur2:cur1;
        }
        if(cur1==null){
            pre.next = cur2;
        }
        if(cur2==null){
            pre.next = cur1;
        }
        return curr;
    }
}
