// leetcode 15
//sort and 1 loop with l, r pointers
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3){
            return new ArrayList<>();
        }
        List<Integer> copy_nums = new ArrayList<Integer>() {{
            for (int num : nums) this.add(num);
        }};
        copy_nums.sort(Comparator.comparing(Integer::intValue));
        List<List<Integer>> result=new ArrayList<>();
        /**
         * ****************** TIP      *********************
         * //Set<List<Integer>> filter=new HashSet<>();
        //用于结果元组去重  NOOOO！因为LeetCode严格匹配输出，用set会导致顺序不对。 
        //本方法不用set：1. HashSet不保证元素顺序与插入顺序一致； 2.本方法已排序，且“加速”，不会有重复元组
         * ****************** TIP      *********************

         */
        
        for(int i=0;i<copy_nums.size()-1;i++){
            //加速（与上一个值相等，则上一轮已经找到过相同解法）
            if(i>0&& copy_nums.get(i)==copy_nums.get(i-1)) continue;
            int a=copy_nums.get(i);
            int l=i+1, r=copy_nums.size()-1;   
            while(l<r){
                int b=copy_nums.get(l), c=copy_nums.get(r);
                if(a+b+c<0){
                    l=l+1;
                }else if(a+b+c>0){
                    r=r-1;
                }else{
                    //filter.add(Arrays.asList(a,b,c)); 
                    result.add(Arrays.asList(a,b,c));
                    while(l<r && copy_nums.get(l)==copy_nums.get(l+1)) l=l+1;
                    while(l<r && copy_nums.get(r)==copy_nums.get(r-1)) r=r-1;
                    l=l+1;
                    r=r-1;
                }
            }         
        }
        return result;
    }
}


// my implmention
// TODO check 为什么会有重复元组
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3){
            return new ArrayList<>();
        }
        List<Integer> copy_nums = new ArrayList<Integer>() {{
            for (int num : nums) this.add(num);
        }};
        copy_nums.sort(Comparator.comparing(Integer::intValue));
        List<List<Integer>> result=new ArrayList<>();
        for(int i=0;i<copy_nums.size()-1;i++){
            if(i>0&& copy_nums.get(i)==copy_nums.get(i-1)){
                continue;
            }
            int a=copy_nums.get(i);
            int l=i+1, r=copy_nums.size()-1;   
            while(l<r){
                int b=copy_nums.get(l), c=copy_nums.get(r);
                if(a+b+c<0){
                    l=l+1;
                }else if(a+b+c>0){
                    r=r-1;
                }else{
                    result.add(Arrays.asList(a,b,c));
                    while(l<r && copy_nums.get(l)==copy_nums.get(l+1)) l=l+1;
                    while(l<r && copy_nums.get(r)==copy_nums.get(r-1)) r=r-1;
                    l=l+1;
                    r=r-1;
                }
            }         
        }
        return result;
    }
}

// 2 loops and use one set; O(n)  注：set首先看起来比较直观，但要去重较麻烦
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3){
            return new ArrayList<>();
        }
        List<Integer> copy_nums = new ArrayList<Integer>() {{
            for (int num : nums) this.add(num);
        }};
        /**
         * 那么第二种方式是怎么工作的呢？它创建了一个匿名的内部类，内部类能够访问父类的一些方法。在这个例子中，实际上就是创建了HashSet的一个子类，在内部类中能够使用add()方法。

 

对于这种解决方法我们要学到什么问题：

1、只要类不是final的，我们就可以使用内部类的方法对其进行初始化

2、初始化在构造之前进行（但在父类构造之后）

参考资料：http://howtodoinjava.com/2012/11/20/double-brace-initialization-in-java/
————————————————
版权声明：本文为CSDN博主「txxs」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/maoyeqiu/article/details/50052193
         */
        copy_nums.sort(Comparator.comparing(Integer::intValue));
        Set<List<Integer>> filter=new HashSet<>();//用于结果元组去重
        /*
        Set<Integer> c_value =new HashSet<>(); 
        !!!*** c_value集合不可以重用，会错误地取到上一个i循环时存的c，
        如 得出结果[[-1,-1,2],[0,-1,1],[0,-2,2],[1,-3,2],[-1,0,1]] while input [-1,0,1,2,-1,-4]
        */ 
        Set<Integer> c_value;
        for(int i=0;i<copy_nums.size()-1;i++){
            //加速（与上一个值相等，则上一轮已经找到过相同解法）
            if(i>0&& copy_nums.get(i)==copy_nums.get(i-1)) continue;
            int a=copy_nums.get(i);
            c_value=new HashSet<>();
            for(int j=i+1;j<copy_nums.size();j++){
                int b=copy_nums.get(j);
                if(!c_value.contains(b)){ // 2.用nums[j]去匹配c_value中存的c，能匹配就说明对应上之前的a,b
                    c_value.add(-(a+b)); // 1.c_value存每次循环时对应 "a","b"的"c"
                }else{
                    filter.add(Arrays.asList(a,-(a+b),b));
                }
            }
        }
        return new ArrayList<>(filter);
    }
}
// nums no sort: output:[[0,-1,1],[2,-1,-1],[-1,2,-1],[0,1,-1],[-1,0,1],[1,0,-1]] while input [-1,0,1,2,-1,-4]
// sort:
// Line 9: error: method sort in interface List<E> cannot be applied to given types;
//         copy_nums.sort();
//                  ^
//   required: Comparator<? super Integer>
//   found:    no arguments
//   reason: actual and formal argument lists differ in length
//   where E is a type-variable:
//     E extends Object declared in interface List