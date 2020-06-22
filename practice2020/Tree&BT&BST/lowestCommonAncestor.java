// leetcode 236
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    // recursion like: findPorQ 模拟从下往上找parent过程（比较left，right的效果）
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // if(left==null && right==null)return null;
        // if(left==null && right!=null) return right;
        // if(left!=null && right==null) return left;
        // return root;
        // 简化1：合并
        // if (left == null)
        //     return right;
        // if (right == null)
        //     return left;
        // return root;
        //使用java 三目表达式
        return left==null? right: right==null? left: root;
    }
}

// leetcode 235
// BST 遍历更方便
// dq: record paths 空间复杂度较大，而且代码冗长
class Solution {
    public List<TreeNode> getPath(TreeNode root, TreeNode node){
        TreeNode ptr=root;
        List<TreeNode> path=new ArrayList<>();
        path.add(ptr);
        while(ptr!=node){
            if(ptr.val<node.val){
                ptr=ptr.right;
                path.add(ptr);
            }else{
                ptr=ptr.left;
                path.add(ptr);
            }
        }
        return path;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        TreeNode lCA=null;
        // record paths
        List<TreeNode> path_p=new ArrayList<>();
        path_p=getPath(root, p);
        List<TreeNode> path_q=new ArrayList<>();
        path_q=getPath(root, q);
        Map<TreeNode, Integer> map_q=new HashMap<>();
        for(int i=0;i<path_q.size();i++){
            map_q.put(path_q.get(i), path_q.get(i).val);
        }
        //compare
        for(int j=0;j<path_p.size();j++){
            if(map_q.containsKey(path_p.get(j))){
                lCA=path_p.get(j);
            }
        }
        return lCA;
    }
}
//精简，根据BST性质，分析两个节点的位置 3种情况  1) p, q不在同一颗子树；2) q在p的左子树；3)q在p的柚子树
//1.递归
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // recursion, compare val
        if(p.val< root.val&&root.val > q.val){
            return lowestCommonAncestor(root.left, p, q); 
            // 总是忘记在调用子树时加return，导致调用不起作用
        }
        if(p.val> root.val&& root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;    
    }
}


