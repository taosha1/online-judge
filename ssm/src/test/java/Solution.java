import java.util.*;

class Solution {
    public int singleNumber(int[] nums) {
        int[] arr = {1, 3, 42};
        Set<Integer> set = new HashSet<>();
//        set.toArray(Integer[]);
        Iterator<Integer> iterator = set.iterator();
        Integer[] integers = set.toArray(new Integer[set.size()]);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            count = count == null ? 1 : ++count;
            map.put(nums[i], count);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                return nums[i];
            }
        }
        return -1;

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num1 : nums1) {
            set1.add(num1);
        }
        for (int num2 : nums2) {
            if (set1.contains(num2)) {
                set2.add(num2);
            }
        }
        int[] array = set2.stream().mapToInt(i -> i).toArray();
        Integer[] ints = set2.toArray(new Integer[]{});
        return array;
    }

    public static boolean isHappy(int n) {
        int num;
        int sum = 0;
        while (n != 0) {
            num = n % 10;
            sum += num * num;
            n = n / 10;
            if (n == 0) {
                if (sum == 1) {
                    return true;
                } else {
                    n = sum;
                    sum = 0;
                }
            }
            if (n == sum) {
                break;
            }
        }
        return false;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                list.add(i);
                list.add(map.get(target - nums[i]));
            }
            map.put(nums[i], i);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public static boolean isIsomorphic(String s, String t) {

        Map<Integer, Character> map = new HashMap<>();
        Map<Integer, Character> map1 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsValue(s.charAt(i)))
                map.put(i, s.charAt(i));
            if (!map1.containsValue(t.charAt(i)))
                map1.put(i, t.charAt(i));
        }
        int[] values = map.keySet().stream().mapToInt(i -> i).toArray();
        int[] values1 = map1.keySet().stream().mapToInt(i -> i).toArray();

        if (values.length != values1.length)
            return false;
        else {
            for (int i = 0; i < values.length; i++) {
                if (values[i] != values1[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * 执行用时 : 34 ms, 在Minimum Index Sum of Two Lists的Java提交中击败了37.83% 的用户
    内存消耗 : 49.3 MB, 在Minimum Index Sum of Two Lists的Java提交中击败了80.26% 的用户
    * */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < list2.length; i++) {
            map1.put(list2[i], i);
        }

        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            if (map1.containsKey(list1[i])) {
                if (minIndex > i + map1.get(list1[i])) {
                    list.clear();
                    list.add(list1[i]);
                    minIndex = i + map1.get(list1[i]);
                } else if (minIndex == i + map1.get(list1[i])) {
                    list.add(list1[i]);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer temp = map.get(s.charAt(i));
            temp = temp == null ? 1 : ++temp;
            map.put(s.charAt(i), temp);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public  static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (list.contains(nums2[i])) {
                list2.add(nums2[i]);
                list.remove((Integer) nums2[i]);
            }
        }
        return list2.stream().mapToInt(i -> i).toArray();
    }


    public static void main(String[] args) {
//        int[] arr = {4, 1, 2, 1, 2};
        int[] arr = {2,2,1,1,3};
        System.out.println(new Solution().singleNumber(arr));
    }
}