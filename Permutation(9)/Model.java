/*
利用recursion和DFS找出给定集合的所有子集，几乎所有搜索问题都适用
根据题目要求修改条件：
（1）什么时候输出
（2）哪些情况需要跳过
*/

void subsets(int[] num){
    ArrayList<Integer> path = new ArrayList<Integer>();
    java.util.Arrays.sort(num);
    subsetsHelper(path, num, 0);
}

void subsetsHelper(ArrayList<Integer> path, int[] num, int pos){
    outputToResult(path); // 进入recursion的下一层时将上一层的path输出
    // pos代表指向数组中哪个元素，从最外层循环开始一层一层向内循环遍历从而得出所有可能的path
    for (int i = pos; i< num.length; i++ ){
        path.add(num[i]); // 将当前指针指向的元素加入path中，递归检查其所有子情况
        // 进入当前指针所指元素的下一层，循环遍历所有情况, pos + 1表示指针指向下一位
        subsetsHelper(path, num, i + 1); // 把当前指针所指元素的所有子孙层都遍历完后会返回到此行，即调用处
        path.remove(path.size() - 1); // 回溯，外层循环的这一次三行语句都执行完，i++继续外层循环
    }
}
