import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            count = count == null ? 1 : ++count;
            map.put(nums[i],count);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i])==1){
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] arr = {4, 1, 2, 1, 2};
        int[] arr = {2,2,1,1,3};
        System.out.println(new Solution().singleNumber(arr));
    }
}