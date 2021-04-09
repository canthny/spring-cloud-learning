package tanghao.learning.test.leetcode.linkedList;

public class SortLinkedList {

    public static void main(String[] args) {
        Node node7 = new Node(7,null);
        Node node6 = new Node(2,node7);
        Node node5 = new Node(5,node6);
        Node node4 = new Node(4,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(6,node3);
        Node head = new Node(1,node2);
        Node node = sort(head);
        while(node.next!=null){
            System.out.print(node.val);
            node = node.next;
        }
        System.out.print(node.val);
    }

    private static Node sort(Node node){
        //拆分
        Node next;
        Node n1=node;
        Node n2=node.next;
        while(node.next!=null){
            next = node.next;
            node.next=next.next;
            node=next;
        }
        //反转
        n2 = revert(n2);

        //合并
        return combine(n1,n2);
    }

    private static Node revert(Node node){
        Node pre=null;Node next;
        while(node!=null){
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    private static Node combine(Node n1,Node n2){
        Node curr = null;
        Node head = null;
        while(n1!=null&&n2!=null){
            if(n1.val<=n2.val){
                if(curr!=null)curr.next=n1;
                curr = n1;
                n1 = n1.next;
            }else{
                if(curr!=null)curr.next = n2;
                curr = n2;
                n2 = n2.next;
            }
            if(head==null){
                head=curr;
            }
        }
        if(head==null){
            head = n1!=null?n1:n2;
            return head;
        }
        if(n1!=null){
            curr.next = n1;
        }
        if(n2!=null){
            curr.next = n2;
        }
        return head;
    }
}
