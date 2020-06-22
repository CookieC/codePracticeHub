//leetcode 232
class MyQueue {
    Stack<Integer> in_stack=new Stack<Integer>();
Stack<Integer> out_stack=new Stack<Integer>();

/** Initialize your data structure here. */
public MyQueue() {

}

/** Push element x to the back of queue. */
public void push(int x) {
// as stack push() in in_stack
in_stack.push(x);
}

/** Removes the element from in front of queue and returns that element. */
public int pop() {
if(out_stack.isEmpty()){
     while(!in_stack.isEmpty()){
        out_stack.push(in_stack.pop());
    }
}
return out_stack.pop();
}

/** Get the front element. */
public int peek() {
if(out_stack.isEmpty()){
    while(!in_stack.isEmpty()){
        out_stack.push(in_stack.pop());
    }
}
return out_stack.peek();
}

/** Returns whether the queue is empty. */
public boolean empty() {
if(in_stack.isEmpty()&&out_stack.isEmpty()){
    return true;
}else{
    return false;
}
}
}

/**
* Your MyQueue object will be instantiated and called as such:
* MyQueue obj = new MyQueue();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.peek();
* boolean param_4 = obj.empty();
*/