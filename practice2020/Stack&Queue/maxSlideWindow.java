// leet code 239 maxSlidingWindow 
// 使用priorityQueue方法超出时间限制
class Solution {
    static Comparator<Integer> cmp = new Comparator<Integer>() {
      public int compare(Integer e1, Integer e2) {
        return e2 - e1; //重载优先级以实现大根堆
      }
    };

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0){
            return new int[0];
        }
        int length=nums.length;
        int[] max_values=new int[length-k+1];
        PriorityQueue<Integer> max_heap = new PriorityQueue<> (cmp); //大根堆
        for(int nums_i=0, j=0; nums_i<length-k+1; nums_i++){
            if(max_heap.size()>0){
                max_heap.clear();
            }
            for(int window_i=0; window_i<k; window_i++){
                max_heap.offer(nums[nums_i+window_i]);
            }
            max_values[j]=max_heap.peek();
            j++;
        }
        return max_values;
    }
}

//双端队列 参考题解
class Solution {
    ArrayDeque<Integer> deq=new ArrayDeque<>(); 
    //用于存储窗口，但存的是索引-便于判断移除上一个窗口元素，且也容易取到原本nums中的值
    int[] result;
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length*k==0){ //nums.length*k包含两者为0的情况
            return new int[0];
        }
        if(k==1){
            return nums;
        }
        result=new int[nums.length-k+1];
        for(int i=0, j=0;i<nums.length;i++){
            if(i>=k&&deq.peekFirst()<=i-k){
                deq.pollFirst();
            }
            while(!deq.isEmpty()&&nums[i]>=nums[deq.peekLast()]){ 
                // 从队列尾部开始比较，能保留前一个max；初始时误用 peekFirst，当遇到3,1,2,0时出错
                //使用while对当前检索值与队列所有值作比较，开始时误用if判断不能完全去除更小值
                deq.pollLast(); 
            }
            deq.offerLast(i);
            if(i>=k-1){
                result[j]=nums[deq.peekFirst()];
                j++;
            }
        }
        return result;
    }
}