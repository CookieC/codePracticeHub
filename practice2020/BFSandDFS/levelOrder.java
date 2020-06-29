// leetcode 102
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1. my colution : 2 queues
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> first_q=new LinkedList<>();
        Queue<TreeNode> second_q=new LinkedList<>();
        TreeNode pointer=null;
        first_q.offer(root);
        while(!first_q.isEmpty() || !second_q.isEmpty()){
            List<Integer> level=new ArrayList<>();
            while(!first_q.isEmpty()){
                pointer=first_q.poll();
                level.add(pointer.val);
                if(pointer.left!=null) second_q.offer(pointer.left);
                if(pointer.right!=null) second_q.offer(pointer.right);
            }
            if(level.size()>0) result.add(level);
            List<Integer> level2=new ArrayList<>();
            while(!second_q.isEmpty()){
                pointer=second_q.poll();
                level2.add(pointer.val);
                if(pointer.left!=null) first_q.offer(pointer.left);
                if(pointer.right!=null) first_q.offer(pointer.right);                
            }
            if(level2.size()>0) result.add(level2);            
        }
        return result;      
    }
}

//2. qinchao solution: queue size --- to tag the level 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode pointer=null;
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            int len=queue.size();
            for(int i=0; i<len; i++){
                pointer=queue.poll();
                level.add(pointer.val);
                if(pointer.left!=null) queue.offer(pointer.left);
                if(pointer.right!=null) queue.offer(pointer.right);
            }
            result.add(level);           
        }
        return result;      
    }
}
