// leetcode 225
class MyStack {
    Queue<Integer> in_q = new LinkedList<Integer>();
    Queue<Integer> temp_q = new LinkedList<Integer>();

    /** Initialize your data structure here. */
    public MyStack() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        in_q.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        // when in_q not empty
        if(!in_q.isEmpty()){
            while(in_q.size()>1){
                temp_q.offer(in_q.poll());
            }
            
            int result=in_q.poll();
            return result;       
        }
        //when in_q is empty
        while(temp_q.size()>1){
            in_q.offer(temp_q.poll());
        }
        int result=temp_q.poll();
        return result;
    }
    
    /** Get the top element. */
    public int top() {
        // when in_q not empty
        if(!in_q.isEmpty()){
            while(in_q.size()>1){
                temp_q.offer(in_q.poll());
            } 
            int result=in_q.peek();
            return result;       
        }
        //when in_q is empty
        while(temp_q.size()>1){
            in_q.offer(temp_q.poll());
        }
        int result=temp_q.poll();
        in_q.offer(result);
        return result;        
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        if(in_q.isEmpty()&&temp_q.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */