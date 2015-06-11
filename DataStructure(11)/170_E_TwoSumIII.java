/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/
// Tag: Hash Table, Data Structure
public class TwoSum {
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void add(int number) {
	    if (!map.containsKey(number)){
	        map.put(number, 1);
	    }else{
	        map.put(number, map.get(number) + 1);
	    }
	}

	public boolean find(int value) {
	    for (int i : map.keySet()){
	        int target = value - i;
	        if (map.containsKey(target)){
	            // 如果某个数在map中有，value是其二倍而该数只有一个或是没有，continue
	            if (target == i && map.get(target) < 2){
	                continue;
	            }
	            return true;
	        }
	    }
	    return false;
	}
}
