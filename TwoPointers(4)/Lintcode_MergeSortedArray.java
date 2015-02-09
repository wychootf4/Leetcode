/*
Merge two given sorted integer array A and B into a new sorted integer array.

Example
A=[1,2,3,4]

B=[2,4,5,6]

return [1,2,2,3,4,4,5,6]

Challenge
How can you optimize your algorithm if one array is very large and the other is very small?
*/

class Solution {
    /**
    * @param A and B: sorted integer array A and B.
    * @return: A new sorted integer array
    */
    public ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        int indexA = 0;
        int indexB = 0;

        while (indexA < A.size() && indexB < B.size()){
            if (A.get(indexA) <= B.get(indexB)){
                result.add(A.get(indexA));
                indexA++;
            }else{
                result.add(B.get(indexB));
                indexB++;
            }
        }

        while (indexA < A.size()){
            result.add(A.get(indexA));
            indexA++;
        }
        while (indexB < B.size()){
            result.add(B.get(indexB));
            indexB++;
        }

        return result;
    }
}
