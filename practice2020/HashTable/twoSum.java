// leetcode 1
// 遍历两遍数组
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length==0){
            return new int[0];
        }
        int[] result=new int[2];
        Map<Integer, Integer> map=new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
        }
        for(int j=0;j<nums.length; j++){
            if(map.containsKey(target-nums[j])&&j!=map.get(target-nums[j])){
                result[0]=j;
                result[1]=map.get(target-nums[j]);
                return result;
            }
        }
        return new int[0];
    }
}
//遍历一遍数组（插入元素到map前就判断）
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length==0){
            throw new IllegalArgumentException("No two sum solution");
        }
        Map<Integer, Integer> map=new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])&&i!=map.get(target-nums[i])){
                return new int[] {map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
