// leetcode 51 N皇后2
// DQ version：wrong output 修改后：
class Solution {
    List<List<String>> results=new ArrayList<List<String>>();
    int num_Q=0;
    public List<List<String>> solveNQueens(int n) {
        num_Q=n;
        int row=0;
        int[] col_loc=new int[n];
        int[] pie_loc=new int[2*n-1]; // pie: row+col=CONST
        int[] na_loc=new int[2*n-1+2*n]; // na: row-col=CONST 易越界
        int[] queens=new int[n]; // record each queen's j location
        Arrays.fill(queens,-1); // fill queens with -1, to record queen
        helper(row, col_loc, pie_loc, na_loc, queens);
        return results;
    }
    
    public void helper(int row, int[] col, int[] pie, int[] na, int[] queens){
        if(row>=num_Q) {
            // draw result
            List<String> cur_result=new ArrayList<String>();
            for(int i=0; i< num_Q; i++){
                StringBuilder builder=new StringBuilder();
                int Q_col=queens[i];
                for(int j=0; j<Q_col; j++) builder.append('.');
                builder.append('Q');
                for(int j=Q_col+1; j<num_Q; j++) builder.append('.');
                cur_result.add(builder.toString());
            }
            results.add(cur_result);
        }     
        for(int j=0; j<num_Q; j++){
            if(col[j]!=1 && pie[row+j]!=1 && na[row-j+2*num_Q]!=1){
                //put Q in (row, j)
                queens[row]=j;                
                col[j]=1;
                pie[row+j]=1;
                na[row-j+2*num_Q]=1;
                helper(row+1, col,pie, na, queens);
                // backtrack 否则只会有一个解
                queens[row]=-1;                                
                col[j]=0;            
                pie[row+j]=0;
                na[row-j+2*num_Q]=0;
            }
        }     
    }
}
// my version, wrong output.
class Solution {
    List<List<String>> results=new ArrayList<List<String>>();
    int num_Q=0;
    public List<List<String>> solveNQueens(int n) {
        // special：不用列出，helper可以处理
        // if(n==2||n==0) {
        //     results.add(""); // error: incompatible types: String cannot be converted to List<String>
        //     return results;
        // }
        // if(n==1) {
        //     results.add("Q");
        //     return results;
        // }
        num_Q=n;
        int row=0;
        int[] col_loc=new int[n];
        int[] pie_loc=new int[2*n-1]; // pie: row+col=CONST
        int[] na_loc=new int[2*n-1+2*n]; // na: row-col=CONST 易越界
        char[] cur_arr={' ', ' ', ' ', ' '};        
        List<String> cur_result=new ArrayList<String>(); //记录一个result
        helper(row, col_loc, pie_loc, na_loc, cur_arr, cur_result);
        return results;
    }
    
    public void helper(int row, int[] col, int[] pie, int[] na, char[] cur_arr, List<String> cur_result
){
        if(row==num_Q) {
            List<String> cur_result_copy=cur_result; 
            results.add(cur_result_copy);
        }
        
        for(int j=0; j<num_Q; j++){
            if(col[j]!=1 && pie[row+j]!=1 && na[row-j+2*num_Q]!=1){
                //put Q in (row, j)
                Arrays.fill(cur_arr,'.');  
                cur_arr[j]='Q';
                String cur_row=new String(cur_arr);
                cur_result.add(cur_row);                   
                col[j]=1;
                pie[row+j]=1;
                na[row-j+2*num_Q]=1;
                helper(row+1, col,pie, na, cur_arr, cur_result);
                // backtrack 否则只会有一个解
                cur_result.remove(cur_row);
                col[j]=0;            
                pie[row+j]=0;
                na[row-j+2*num_Q]=0;
            }
        }     
    }

    // public List<String> draw_result(int[] col, int[] pie, int[] na){
    //     List<String> cur_result=new ArrayList<String>();
    //     for(int row_i=0; row_i<num_Q; row_i++){
    //         char[] cur_arr={' ', ' ', ' ', ' '};
    //         for(int col_i=0; col_i<num_Q; col_i++){
    //             if(col[col_i]!=1 && pie[row_i+col_i]!=1 && na[row_i-col_i+2*num_Q]!=1) // 行不通，因为全部“遍历完后”，col, pie, na都是1了
    //             {
    //                 cur_arr[col_i]='Q';
    //             } 
    //             else {
    //                 cur_arr[col_i]='.';
    //             }
    //         }
    //         String cur_row=new String(cur_arr);
    //         cur_result.add(cur_row);
    //     }
    //     return cur_result;
    // }
}


// 参考1 使用二维数组画板记录 （DQ：参考2更节省空间）
class Solution {
    boolean[] col = null;
    boolean[] left = null;
    boolean[] right = null;
    List<List<String>> ret = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
	col = new boolean[n];
	left = new boolean[2*n-1];
	right = new boolean[2*n-1];
	char[][] board = new char[n][n];
	backTrack(board, 0, n);
	return ret;
    }

    void backTrack(char[][] board, int r, int n) {
	if (r >= n) {
	    List<String> list = new ArrayList<>();
	    for (int i = 0; i < n; i++) {
		list.add(new String(board[i]));
	    }
	    ret.add(list);
	    return;
	}
	Arrays.fill(board[r], '.');
	for (int i = 0; i < n; i++) {
	    if (!col[i] && !left[r + i] && !right[r - i + n - 1]) {
		board[r][i] = 'Q';
		col[i] = true;
		left[r + i] = true;
		right[r - i + n - 1] = true;
		backTrack(board, r + 1, n);
		board[r][i] = '.';
		col[i] = false;
		left[r + i] = false;
		right[r - i + n - 1] = false;
	    }
	}
    }
}
// 参考2 使用queen[n]记录每行Q的列
class Solution {
    private List<List<String>> output = new ArrayList<>();

    // 用于标记是否被列方向的皇后被攻击
    int[] rows;
    // 用于标记是否被主对角线方向的皇后攻击
    int[] mains;
    // 用于标记是否被次对角线方向的皇后攻击
    int[] secondary;
    // 用于存储皇后放置的位置
    int[] queens;

    int n;

    public List<List<String>> solveNQueens(int n) {
        // 初始化
        rows = new int[n];
        mains = new int[2 * n - 1];
        secondary = new int[2 * n - 1];
        queens = new int[n];
        this.n = n;

        // 从第一行开始回溯求解 N 皇后
        backtrack(0);

        return output;    
    }

    // 在一行中放置一个皇后
    private void backtrack(int row) {
        if (row >= n) return;
        // 分别尝试在 row 行中的每一列中放置皇后
        for (int col = 0; col < n; col++) {
            // 判断当前放置的皇后不被其他皇后的攻击
            if (isNotUnderAttack(row, col)) {
                // 选择，在当前的位置上放置皇后
                placeQueen(row, col);
                // 当当前行是最后一行，则找到了一个解决方案
                if (row == n - 1) addSolution();
                // 在下一行中放置皇后
                backtrack(row + 1);
                // 撤销，回溯，即将当前位置的皇后去掉
                removeQueen(row, col);
            }
        }
    }

    // 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
    private boolean isNotUnderAttack(int row, int col) {
        // 判断的逻辑是：
        //      1. 当前位置的这一列方向没有皇后攻击
        //      2. 当前位置的主对角线方向没有皇后攻击
        //      3. 当前位置的次对角线方向没有皇后攻击
        int res = rows[col] + mains[row - col + n - 1] + secondary[row + col];
        // 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
        return res == 0;
    }

    // 在指定的位置上放置皇后
    private void placeQueen(int row, int col) {
        // 在 row 行，col 列 放置皇后
        queens[row] = col;
        // 当前位置的列方向已经有皇后了
        rows[col] = 1;
        // 当前位置的主对角线方向已经有皇后了
        mains[row - col + n - 1] = 1;
        // 当前位置的次对角线方向已经有皇后了
        secondary[row + col] = 1;
    }

    // 移除指定位置上的皇后
    private void removeQueen(int row, int col) {
        // 移除 row 行上的皇后
        queens[row] = 0;
        // 当前位置的列方向没有皇后了
        rows[col] = 0;
        // 当前位置的主对角线方向没有皇后了
        mains[row - col + n - 1] = 0;
        // 当前位置的次对角线方向没有皇后了
        secondary[row + col] = 0;
    }

    /**
     * 将满足条件的皇后位置放入output中
     */
    public void addSolution() {
        List<String> solution = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; ++j) sb.append(".");
            sb.append("Q");
            for(int j = 0; j < n - col - 1; ++j) sb.append(".");
            solution.add(sb.toString());
        }
        output.add(solution);
    }
}