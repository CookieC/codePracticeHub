//leetcode 111
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
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left_depth=minDepth(root.left)+1;
        int right_depth=minDepth(root.right)+1;
        if(left_depth==1) return right_depth;
        if(right_depth==1) return left_depth;
        return left_depth<=right_depth? left_depth: right_depth;
    }
}

// ERROR！！ 未理解清楚深度定义
/**
 * 注意深度的定义
 * 给定一个二叉树，找出其最小深度。
 *  二叉树的最小深度为根节点到最近叶子节点的最短路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 */
//最开始按照maxDepth思路 [1,3]应返回2，错误返回1
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left_depth=minDepth(root.left)+1;
        int right_depth=minDepth(root.right)+1;
        return left_depth<=right_depth? left_depth: right_depth;
    }
}
//ERROR 2 改进：对根节点判断左右子树是否为空，空的话就返回非空子树的深度
// BUT [1,2,3,4,null,null,5]应返回3，错误返回2
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left_depth=smallerDepth(root.left)+1;
        int right_depth=smallerDepth(root.right)+1;
        if(left_depth==1) return right_depth;
        if(right_depth==1) return left_depth;
        return left_depth<=right_depth? left_depth: right_depth;
    }

    public int smallerDepth(TreeNode root){
        if(root==null) return 0;
        int left_depth=smallerDepth(root.left)+1;
        int right_depth=smallerDepth(root.right)+1;
        return left_depth<=right_depth? left_depth: right_depth;
    }
}

// 用BFS，更好理解
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int min_depth=0;
        Queue<TreeNode> helper_q=new LinkedList<>();
        int level_size=0;
        helper_q.offer(root);
        while(!helper_q.isEmpty()){
            min_depth++;
            level_size=helper_q.size();
            for(int i=0;i<level_size; i++){
                TreeNode node=helper_q.poll();
                if(node.left!=null) helper_q.offer(node.left);
                if(node.right!=null) helper_q.offer(node.right);
                if(node.left==null && node.right==null) return min_depth;
            }
        }
        return min_depth;
    }

}