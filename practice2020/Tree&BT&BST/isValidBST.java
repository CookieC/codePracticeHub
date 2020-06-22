// leetcode 98
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//中序遍历，判断是否升序
class Solution {
    public TreeNode pre=null;

    public boolean inOrder(TreeNode root){
        if(root==null){
            return true;
        }
        if(!inOrder(root.left)){
            return false;
        }
        if(pre!=null && pre.val>=root.val){
            return false;
        }
        pre=root;
        return inOrder(root.right);
    }
    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        return inOrder(root);
    }
}

// 非递归中序遍历，借用栈实现
// 参考官方题解，借用栈实现非递归中序遍历
class Solution {
    public boolean isValidBST(TreeNode root) {
      Stack<TreeNode> stack = new Stack();
      double inorder = - Double.MAX_VALUE;
  
      while (!stack.isEmpty() || root != null) {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        root = stack.pop();
        // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
        if (root.val <= inorder) return false;
        inorder = root.val;
        root = root.right;
      }
      return true;
    }
  }

// 以下做法也是“中序”，但递归条件没写对，遇到叶子结点便返回了
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        while(root.left!=null){
            if(root.left.val<root.val){
                root=root.left;
                isValidBST(root);
            }else{
                return false;
            }
        }
        while(root.right!=null){
            if(root.right.val>root.val){
                root=root.right;
                isValidBST(root);
            }else{
                return false;
            }
        }
        return true; // !!! 不完整，遇到叶子结点就返回了
    }
}

//递归，根据二叉搜索树性质，设置上界和下界
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
    public boolean helper(TreeNode root, Integer lower, Integer upper){
        if(root==null){
            return true;
        }
        // 遍历 获取子树helper的结果
        if(!helper(root.left, lower, root.val)) return false;
        if(!helper(root.right, root.val, upper)) return false;

        if(upper!=null && root.val >= upper) return false;
        if(lower!=null && root.val <= lower) return false;

        return true;

    }

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
}

// 函数定义中 int lower, int upper
// Line 19: error: bad operand types for binary operator '!='
//         if(upper!=null && root.val >= upper) return false;
//                 ^
//   first type:  int
//   second type: <null>
// Line 20: error: bad operand types for binary operator '!='
//         if(lower!=null && root.val <= lower) return false;
//                 ^
//   first type:  int
//   second type: <null>
// Line 27: error: incompatible types: <null> cannot be converted to int
//         return helper(root, null, null);
//                             ^
// 3 errors