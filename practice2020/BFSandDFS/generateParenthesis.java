package practice2020.BFSandDFS;
// leetcode 22
public class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result=new ArrayList<String>();
        gen_helper(n,n,"",result);
        return result;
    }

    // 递归方法，left-剩余左括号数
    public void gen_helper(int left, int right, String cur_res, List<String> result){
        if(left==0&&right==0) {
            result.add(cur_res);
            return;
        }
        if (left>0) {
            gen_helper(left-1,right,cur_res+"(",result);
        }
        if(left<right) {
            gen_helper(left,right-1,cur_res+")",result);
        }
    }
}