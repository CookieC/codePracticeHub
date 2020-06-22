// leetcode 24
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next==null){ //当链表为空或者只有一个节点
            return head;
        }
        ListNode first = head;
        ListNode second = first.next;
        ListNode third = second.next;
        second.next = first;
        first.next = third;
        head = second; //第一次两两交换后重新指定head节点
        while(third!= null && third.next!=null){
            first.next=third.next; // !! 前一个节点的next也要记得变，要不然新的second就不在主链上了 
            first = third;
            second = third.next;
            third = third.next.next;
            second.next = first;
            first.next = third;
        }
        return head;
    }
}