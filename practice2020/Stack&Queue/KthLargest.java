//leetcode 703
class KthLargest {
    static int k_largest;
    static PriorityQueue<Integer> prior_q;

    public KthLargest(int k, int[] nums) {
        k_largest=k;
        prior_q=new PriorityQueue<>(k);
        for(int i : nums){
            add(nums[i]);
        }
    }
    
    public int add(int val) {
        if(prior_q.size()<k_largest){
            prior_q.offer(val);
        }else if(prior_q.peek()<val){
            prior_q.poll();
            prior_q.offer(val);
        }
        return prior_q.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */