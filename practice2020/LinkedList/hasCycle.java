// leetcode 141
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
// public class Solution {
//     public boolean hasCycle(ListNode head) {
//         Set<ListNode> set=new HashSet<ListNode>();
//         ListNode cur=head;
//         while(cur!=null){
//             if(set.add(cur)==false){
//                 return true;
//             }
//             cur=cur.next;
//         }
//         return false;
//     }
// }

// 快慢指针做法：
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){ //判断哪些元素为null？【不能取null的next】
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }
}