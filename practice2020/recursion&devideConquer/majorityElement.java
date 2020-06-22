// leetcode 169 
// 还有map法，排序法（中指），摩尔投票法
//分治法，前提是一定存在多数元素，否则判断复杂
class Solution {
    public int count(int[] nums, int to_count, int low, int high){
        int count=0;
        for(int i=low; i<=high; i++){
            if(nums[i]==to_count) count++;
        }
        return count;
    }

    public int helper(int[] nums, int low, int high){
        if(low==high){
            return nums[low];
        }
        //end condition
        //divide
        int mid=(high-low)/2+low;
        int left=helper(nums,low, mid);
        int right=helper(nums, mid+1, high);
        //conquer
        if(left==right){
            return left;
        }
        return count(nums,left,low,high)>count(nums,right,low,high)? left: right;
        // 以下判断不能用，因为wrong type operation，int不能判空

        // else if(left==null){
        //     return right;
        // }else if(right==null){
        //     return left;
        // }else{
        //     return new Integer();
        // }   

    }

    public int majorityElement(int[] nums) {
        if(nums.length<=1){
            return nums[0];
        }
        int result= helper(nums, 0, nums.length-1);
        return result;      
    }
}