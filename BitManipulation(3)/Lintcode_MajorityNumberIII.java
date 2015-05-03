/*
Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the
size of the array.

Find it.

Example
Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.

Note
There is only one majority number in the array.

Challenge
O(n) time and O(k) extra space
*/
// Tag: Hash Table, Linked List

/*
分析：
对于k分之一的问题，我们可以维护一个哈希表，遍历数组中的元素加到哈希表中，如果此元素在哈希表中则count++；如果没在哈希表且哈希表未满则加入，
如果哈希表已满证明这k个数可以抵消了，哈希表中所有key所对应的value都减一，如果某个value = 0了就删除掉这个key。
之后将所有value清零，然后重新遍历一遍数组求出真正的主元素。
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        if (nums == null || nums.size() == 0){
            return -1;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.size(); i++){
            // 如果当前元素已在哈希表中则count+1
            if (map.containsKey(nums.get(i))){
                map.put(nums.get(i), map.get(nums.get(i)) + 1);
            }else{
                // 当前元素不在哈希表中，且加上此元素后还凑不到k个数
                if (map.size() + 1 < k){
                    map.put(nums.get(i), 1);
                // 哈希表已经存满了k-1个元素，当前元素不在哈希表中，可以凑成k个数了。
                // 所有哈希表中元素的count - 1，减完如果count为0的remove
                }else{
                    List<Integer> zeroList = new ArrayList<Integer>();
                    for (int key : map.keySet()){
                        int val = map.get(key);
                        if (val - 1 == 0){
                            zeroList.add(key);
                        }
                        map.put(key, val - 1);
                    }
                    for (int key : zeroList){
                        map.remove(key);
                    }
                }
            }
        }

        // 最后将map的value都清零，重新再遍历一遍，返回value最大的key
        for (Map.Entry entry : map.entrySet()){
            entry.setValue(0);
        }
        int candidate = 0;
        int count = 0;

        for (int i = 0; i < nums.size(); i++){
            if (map.containsKey(nums.get(i))){
                int val = map.get(nums.get(i)) + 1;
                if (val > count){
                    candidate = nums.get(i);
                    count = val;
                }
                map.put(nums.get(i), val);
            }
        }

        return candidate;
    }
}
