//1.two-sum
/**
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 2 <= nums.length <= 10⁴
 * -10⁹ <= nums[i] <= 10⁹
 * -10⁹ <= target <= 10⁹
 * Only one valid answer exists.
 * <p>
 * <p>
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than
 * O(n²)
 * time complexity?
 * <p>
 * Related Topics Array Hash Table 👍 68096 👎 2532
 */

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int remain = target - nums[i];
                if (map.containsKey(remain)) {
                    return new int[]{i, map.get(remain)};
                } else {
                    map.put(nums[i], i);
                }
            }
            return new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}