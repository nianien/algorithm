//56.merge-intervals
/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals that
 * cover all the intervals in the input.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: intervals = [[4,7],[1,4]]
 * Output: [[1,7]]
 * Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= intervals.length <= 10⁴
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Sorting 👍 24522 👎 902
 */

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
            int start = intervals[0][0];
            int end = intervals[0][1];
            List<int[]> res = new ArrayList<>();
            for (int i = 1; i < intervals.length; i++) {
                int[] e = intervals[i];
                if (end >= e[0]) {
                    end = Math.max(end, e[1]);
                } else {
                    res.add(new int[]{start, end});
                    start = e[0];
                    end = e[1];
                }
            }
            res.add(new int[]{start, end});
            return res.toArray(new int[0][]);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}