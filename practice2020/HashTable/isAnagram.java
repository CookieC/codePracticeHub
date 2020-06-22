// leetcode 242
//第一个思路 remove使用错误； so 没有合适得使用map
class Solution {
    public Map<Integer, Object> stringToMap(String t){
        Char[] t_arr=t.toCharArray();
        Map<Integer, Object> map=new HashMap<>();
        for(int i=0;i<t_arr.length;i++){
            map.put(i, t_arr[i]);
        }
        return map;
    }
    public boolean isAnagram(String s, String t) {
        int s_len=s.length();
        int t_len=t.length();
        if(s_len!=t_len){
            return false;
        }
        if(s_len==0&&t_len==0){
            return true;
        }
        Map<Integer, Object> map = stringToMap(t);
        for(int i=0;i<s_len; i++){
            if(map.containsValue(s.charAt(i))){  
                map.remove(s.charAt(i));  //!!! 并未移除value，因为remove(object k)参数是key，所以"aacc","ccac"例子错误
            }else{
                return false;
            }
        }
        return true;
    }
}

// refer solution, MAP STORE THE COUNT!!!
class Solution {
    public boolean isAnagram(String s, String t) {
        int s_len=s.length();
        int t_len=t.length();
        if(s_len!=t_len){
            return false;
        }
        if(s_len==0&&t_len==0){
            return true;
        }
        Map<Character, Integer> s_map=new HashMap<>();
        Map<Character, Integer> t_map=new HashMap<>();
        for(int i=0;i<s_len;i++){
            if(s_map.containsKey(s.charAt(i))){
                s_map.put(s.charAt(i),s_map.get(s.charAt(i))+1);
            }else{
                s_map.put(s.charAt(i),1);
            }
        }
        for(int j=0;j<t_len;j++){
            if(t_map.containsKey(t.charAt(j))){
                t_map.put(t.charAt(j),t_map.get(t.charAt(j))+1);
            }else{
                t_map.put(t.charAt(j),1);
            }
        }
        if(s_map.equals(t_map)){
            return true;
        }else{
            return false;
        }

    }
}

