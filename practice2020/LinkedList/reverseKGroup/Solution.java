import java.lang.reflect.Field;

public class Solution {
    //reverseLinkedList method
    public ListNode[] reverseLinkedList(final ListNode head, final int k){
        ListNode cur=head;
        ListNode pre=null;
        ListNode nex=null;
        for(int i=0; i<k; i++){
            nex=cur.next;
            cur.next=pre;
            pre=cur;
            cur=nex;
        }
        final ListNode[] result={pre, nex};        
        return result;
    }

    public ListNode reverseKGroup(ListNode head, final int k) {
        //compute length & group num
        int length=0;
        int group_num=0;
        ListNode node=head; // node: use to count the length 
        //！！不需要计算长度，只要遍历时往前数K个 -->减少遍历次数
        if(head==null){
            return head;
        }
        while(node!=null){
            length++;
            node=node.next;
        }
        group_num=length/k; // ！！注意/和%符号的区别

        //reverse each group
        ListNode start=head;
        ListNode pre_inK=null;
        ListNode out_node=null;
        ListNode[] result=new ListNode[2]; 
        result=reverseLinkedList(start,k); 
        pre_inK=result[0];
        out_node=result[1];
        start.next=out_node;
        start=out_node;     
        head=pre_inK;  //set the new head node after first reverse         
        for(int group=1;group<group_num;group++){
            result=reverseLinkedList(start,k); 
            out_node=result[1];
            start.next=out_node;
            start=out_node;
        }
        return head;
    }

    // 循环内重复使用两个ListNode节点，不能保存之前已赋值的节点
//     public ListNode assignListNode(int[] Values){
//         ListNode hair =new ListNode(0);
//         ListNode cur = new ListNode(0);
//         ListNode nex = new ListNode(0);
//         cur.val=Values[0];
//         hair.next=cur;
//         for(int i=1;i<Values.length;i++){
//             nex.val=Values[i];
//             nex.next=null;
//             cur.next=nex;
//             cur=nex;
//         }
//         return hair.next;
//     }
// }

public ListNode assignListNode(int[] Values){
    ListNode hair =new ListNode(0);
    ListNode first = new ListNode(0);
    first.val=Values[0];
    hair.next=first;
    ListNode cur = first;
    for(int i=1;i<Values.length;i++){
        ListNode nex = new ListNode(0);
        nex.val=Values[i];
        nex.next=null;
        cur.next=nex;
        cur=nex;
    }
    return hair.next;
}
}