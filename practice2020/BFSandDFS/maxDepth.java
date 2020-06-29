// leetcode 104 
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int left_depth=maxDepth(root.left)+1;
        int right_depth=maxDepth(root.right)+1;
        return left_depth>=right_depth? left_depth: right_depth;
    }
}
// BFS，直观，但效率相比DFS递归要低
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int depth=0;
        Queue<TreeNode> helper_q=new LinkedList<>();
        int level_size=0;
        helper_q.offer(root);
        while(!helper_q.isEmpty()){
            depth++;
            level_size=helper_q.size();
            for(int i=0;i<level_size; i++){
                TreeNode node=helper_q.poll();
                if(node.left!=null) helper_q.offer(node.left);
                if(node.right!=null) helper_q.offer(node.right);
            }
        }
        return depth;
    }
}