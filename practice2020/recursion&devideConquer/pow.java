// 未仔细思考时的做法： WRONG！！
class Solution {
    public double myPow(double x, int n) {
        if(x==0){
            return 0.0;
        }
        if(n==0){
            return 1.0;
        }
        if(n<0){
            return 1/x * myPow(x,n+1);
        }else{
            return x * myPow(x,n-1);
        }
    }
}

//参考题解：快速幂
// 分治法-1 考虑n为负数时越界情况
class Solution {
    public double quickMul(double x, long N){
        if(N==0 || x==1.0){
            return 1.0;
        }
        double y=quickMul(x, N/2);
        return N%2==0? y*y: y*y*x;
    }
    public double myPow(double x, int n) {
        long N = n;
        return N<0? 1.0/quickMul(x, -N): quickMul(x, N);
    }
}
// 分治法-2 不同 long来代替int n
class Solution {
    public double myPow(double x, int n) {
if(n==0)
            return 1;
        double result=0;
        double half=myPow(x,n/2);
        if(n%2==1)
            result=half*half*x;
        else if(n%2==0)
            result=half*half;
        else if(n%2==-1)   // ！！！ 这个判断~
            result=half*half/x;
        return result;
    }
}
//迭代，二进制
class Solution {
    public double quickMul(double x, long N){
        if(N==0 || x==1.0){
            return 1.0;
        }
        double ans=1.0;
        double x_contribute=x;
        while(N>0){
            if(N%2==1){
                ans*=x_contribute; //贡献位（即二进制位为1时） 计算 ans
            }
            x_contribute*=x_contribute; // 非贡献位，x_contribute平方，为下个贡献位做准
            N=N/2;
        }
        return ans;
    }
    public double myPow(double x, int n) {
        long N = n;
        return N<0? 1.0/quickMul(x, -N): quickMul(x, N);
    }
}