//128.longest-consecutive-sequence
/**
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,0,1,2]
 * Output: 3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= nums.length <= 10⁵
 * -10⁹ <= nums[i] <= 10⁹
 * <p>
 * <p>
 * Related Topics Array Hash Table Union-Find 👍 22795 👎 1230
 */

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new LongestConsecutiveSequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestConsecutive(int[] nums) {
            nums = Arrays.stream(nums).distinct().sorted().toArray();
            Map<Integer, Integer> counter = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                counter.merge(nums[i] - i, 1, Integer::sum);
            }
            return counter.values().stream().max(Comparator.comparingInt(x -> x)).orElse(0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}