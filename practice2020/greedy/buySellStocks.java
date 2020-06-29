//leetcode 122

/*思路
1. 先找到买入（当后一元素大于当前，则买入），然后找最合适的卖出；【但未实现如何找最合适的卖出】
2. （参考覃超思路）不限买卖次数，只要后一元素高于buy，就sell。
*/
// 2 实现 ** 有缺陷：buy并非限制了 非负数
class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        int buy=0;
        int iter=0;
        while(iter<prices.length){
            if(buy>0){
                if(prices[iter]>prices[iter-1]){
                    profit+=prices[iter]-buy;
                    buy=0;
                    continue;
                }else{
                    iter++;
                }
            }else{
                if(iter<prices.length-1 && prices[iter]<prices[iter+1]){
                    buy=prices[iter];
                }
                iter++;
            } 
        }
                
        return profit;
    }
}

// 题解优化的代码 
class Solution {
    public int maxProfit(int[] prices) {
        int i = 0;
        int buy =prices[0];
        int sell = prices[0];
        int max_profit=sell-buy;
        while(i<prices.length-1){
            // 通过while循环找合适的买入、卖出值；常用的“移动指针”方式 （总是想不到，就拐弯用很多判断）
            while(i<prices.length-1 && prices[i]>=prices[i+1]) i++; 
            buy=prices[i];
            while(i<prices.length-1 && prices[i]<=prices[i+1]) i++;
            sell=prices[i];
            max_profit+=sell-buy;
        }
        return max_profit;
    }
}