leetcode 52 N皇后2
class Solution {
    int num_Q=0;
    int result=0;
    public int totalNQueens(int n) {
        if(n==2||n==0) return 0;
        if(n==1) return 1; // n=1时也有一个解，未推算直接认为是0，出错
        num_Q=n;
        int row=0;
        int[] col_loc=new int[n];
        int[] pie_loc=new int[2*n-1]; // pie: row+col=CONST
        int[] na_loc=new int[2*n-1+2*n]; // na: row-col=CONST 易越界
        helper(row, col_loc, pie_loc, na_loc);
        return result;
    }

    public void helper(int row, int[] col, int[] pie, int[] na){
        if(row==num_Q) result=result+1;
        for(int j=0; j<num_Q; j++){
            if(col[j]!=1 && pie[row+j]!=1 && na[row-j+2*num_Q]!=1){
                //put Q in (row, j)
                col[j]=1;
                pie[row+j]=1;
                na[row-j+2*num_Q]=1;
                helper(row+1, col,pie, na);
                // backtrack 否则只会有一个解
                col[j]=0;
                pie[row+j]=0;
                na[row-j+2*num_Q]=0;
            }
        }
    }
}