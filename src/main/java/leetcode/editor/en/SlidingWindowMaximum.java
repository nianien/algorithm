//239.sliding-window-maximum
/**
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right. You can only
 * see the k numbers in the window. Each time the sliding window moves right by one
 * position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * 1 <= k <= nums.length
 * <p>
 * <p>
 * Related Topics Array Queue Sliding Window Heap (Priority Queue) Monotonic
 * Queue 👍 20337 👎 838
 */

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] ans = new int[n - k + 1];
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                // 队尾比新元素小，直接丢掉，永远不可能是最大值
                while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                    dq.pollLast();
                }
                dq.offerLast(i);
                // 队头已经滑出窗口，窗口右边界为i，则左边界为i-k+1
                if (dq.peekFirst() < i - k + 1) dq.pollFirst();

                // 窗口形成后开始记录,第一个窗口的右边界为k-1，即[0,k-1]
                if (i >= k - 1) ans[i - k + 1] = nums[dq.peekFirst()];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}