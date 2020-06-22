// 05-25 直接法，未使用其他函数优化代码简洁度
class Solution {
    public boolean isValid(String s) {
        // empty -->true
        if(s.length()==0){
            return true;
        }
        char[] array=s.toCharArray();
        // 判断是否闭合
        int left_num = 0;
        int right_num =0;
        for(int i=0; i<array.length; i++){
            if(array[i]== '('||array[i]== '['||array[i]== '{'){
                left_num++;
            }
            if(array[i]== ')'||array[i]== ']'||array[i]== '}'){
                right_num++;
            }
        }
        if(left_num!=right_num){
            return false;
        }
        //相同类型、正确顺序闭合
        Stack<Character> brackets=new Stack<Character>();
        for(int j=0; j<array.length; j++){ //注意迭代变量，copy时容易忘记把i改成j
            if(array[j]== '('||array[j]== '['||array[j]== '{'){
                brackets.push(array[j]);
            }
            switch(array[j])
            {
                case ')':
                    if(brackets.empty()!=true&&brackets.peek()=='('){ //一定要先判空，否则报错
                        brackets.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                case ']':
                    if(brackets.empty()!=true&&brackets.peek()=='['){
                        brackets.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                case '}':
                    if(brackets.empty()!=true&&brackets.peek()=='{'){
                        brackets.pop();
                    }
                    else{
                        return false;
                    }
                    break;
            }
        }
        return true;

    }
}

// use map to simplfy switch 
class Solution {
    public boolean isValid(String s) {
        // empty -->true
        if(s.length()==0){
            return true;
        }
        // constrct a map
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        // match
        Stack<Character> brackets = new Stack<>();
        for(char c : s.toCharArray()){ //!! 循环可以这样写，或者如循环2
            if(!map.containsKey(c)){
                brackets.push(c);
            }else if(brackets.isEmpty()||map.get(c)!=brackets.pop()){  //直接判断pop()返回的值，不需要再执行peek
                return false;
            }
        }
        return brackets.isEmpty();
    }
}

//注：循环2
// for(int i = 0 ; i < s.length() ; i ++){

//     char temp = s.charAt(i);
//     if(temp == '(' || temp == '{' || temp == '['){
//         stack.push(temp);
//         continue;
//     }else if(temp == ')' && (stack.isEmpty() || stack.pop() != '(')){
//         return false;
//     }else if(temp == '}' && (stack.isEmpty() || stack.pop() != '{')){
//         return false;
//     } else if(temp == ']' && (stack.isEmpty() || stack.pop() != '[')){
//         return false;
//     }
// }