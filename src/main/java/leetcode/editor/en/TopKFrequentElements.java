//347.top-k-frequent-elements
/**
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * <p>
 * <p>
 * Output: [1,2]
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1], k = 1
 * <p>
 * <p>
 * Output: [1]
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * <p>
 * <p>
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.
 * <p>
 * Related Topics Array Hash Table Divide and Conquer Sorting Heap (Priority
 * Queue) Bucket Sort Counting Quickselect 👍 19386 👎 853
 */

package leetcode.editor.en;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> counter = new HashMap<>();
            for (int num : nums) {
                counter.merge(num, 1, (v1, v2) -> v1 + v2);
            }
            PriorityQueue<int[]> q = new PriorityQueue<>(k, Comparator.comparingInt(x -> x[1]));
            for (Map.Entry<Integer, Integer> en : counter.entrySet()) {
                q.offer(new int[]{en.getKey(), en.getValue()});
                if (q.size() > k) {
                    q.poll();
                }
            }
            return q.stream().mapToInt(x -> x[0]).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}