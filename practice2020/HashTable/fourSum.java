// leetcode 18
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
         List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            for (int j = i + 1; j < n - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int tmp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (tmp == target) {
                        List<Integer> tmpList = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        res.add(tmpList);
                        while (left < right && nums[left] == nums[left + 1]) left += 1;
                        while (left < right && nums[right] == nums[right - 1]) right -= 1;
                        left += 1;
                        right -= 1;
                    } else if (tmp > target) right -= 1;
                    else left += 1;
                }
            }

        }

        return res; 
    }
}