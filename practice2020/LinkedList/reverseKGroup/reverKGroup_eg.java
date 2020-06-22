// leetcode 25
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //reverseLinkedList method
    public ListNode reverseLinkedList(ListNode head){
        ListNode cur=head;
        ListNode pre=null;
        ListNode nex=null;
        while(cur!=null){
            nex=cur.next;
            cur.next=pre;
            pre=cur;
            cur=nex;
        }   
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0); //用来指向新的head
        hair.next=head;
        ListNode pre=hair;
        ListNode end=hair;

        // k个一组翻转
        while(end.next!=null){
            for(int i=0;i<k;i++){
                // if(end.next==null){
                //     break;
                // } ！！这里的break只是跳出了if，而不是大循环 
                if(end.next==null){
                    return hair.next;
                }
                end=end.next;
            }
            ListNode nex=end.next;
            ListNode start=pre.next;
            end.next=null;
            pre.next=reverseLinkedList(start); // 连接上一个子链表
            start.next=nex; //连接下一个子链表
            pre=start;
            end=pre;
        }
        return hair.next;

    }
}