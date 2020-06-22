// leetcode 142
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

//借用set数据结构
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set=new HashSet<ListNode>();
        ListNode cur=head;
        while(cur!=null){
            if(set.add(cur)==false){
                return cur;
            }
            cur=cur.next;
        }
        return null;
    }
}

// 快慢指针做法
public class Solution {
    // decide whether has a cycle
    public ListNode getIntersection(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){ 
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                return fast;
            }
        }
        return null;
    }
    // find cycle location [base-the mathematical proof: 
    // 2*distance(slow)=distance(fast), that is: 2*(F+a)=F+a+N*(a+b)]
    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        } //首先对head判空，少去调用函数的时间和空间
        if(getIntersection(head)==null){
            return null;
        }
        ListNode ptr1=head;
        ListNode ptr2=getIntersection(head);
        while(ptr1!=ptr2){
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }
        return ptr1;
    }
}