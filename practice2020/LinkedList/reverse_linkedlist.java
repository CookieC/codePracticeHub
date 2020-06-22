// leetcode 206
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public int getLength(ListNode head){
        int len=1;
        while(head.next!=NULL){
            len++;
        }
        return len;
    }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        if(head.getLength(head)==1){
            return head;
        }
        else if(head.getLength(head)==2){
            ListNode p=head;
            ListNode q=p.next;
            head.next=NULL;
            q.next=p;
            return q;
        }
        else{
            ListNode p=head;
            ListNode q=p.next;
            ListNode s=q.next;
            while(s!=NULL){
                q.next=p;
                p=q;
                q=s;
                s=s.next;
            }
            q.next=p;
            return q;
        }
    }
}
// 以上超出时间限制

/*
05-18 leetcode 参考评论解答

class Solution {
    public ListNode reverseList(ListNode head) {       
        ListNode pre= null;
        ListNode cur=head;
        while(cur!= null){
            ListNode nextNode=cur.next;
            cur.next=pre;
            pre=cur;
            cur=nextNode;
        }
        return pre; //why return pre? mine is return cur ???
    }
}
*/

/*
自己在电脑上写怎么加上定义和测试函数呢？
see reverseKGroup dir
 */


/** python3 version */
/**
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        pre, cur = None, head
        while(cur):
            cur.next, pre, cur = pre, cur, cur.next
        return pre
 */