//47.permutations-ii
/**
 * Given a collection of numbers, nums, that might contain duplicates, return all
 * possible unique permutations in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * <p>
 * <p>
 * Related Topics Array Backtracking Sorting 👍 9121 👎 163
 */

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            return permuteUnique(nums, 0, new ArrayList<>());
        }


        private List<List<Integer>> permuteUnique(int[] nums, int k, List<List<Integer>> ans) {
            if (k == nums.length - 1) {
                // 当递归到最后一位时，整个排列已经确定
                ans.add(Arrays.stream(nums).boxed().toList());
                return ans;
            }
            // 枚举区间 [k, nums.length - 1] 中的元素，尝试放到位置 k
            for (int i = k; i < nums.length; i++) {
                // 本层去重：
                // 判断 nums[i] 是否在区间 [k, i) 中第一次出现
                // 若不是第一次出现，说明相同值已经放到过位置 k，会产生重复排列
                if (this.isFirstChar(nums, k, i)) {
                    // 交换 nums[k] 和 nums[i]，将 nums[i] 放到位置 k
                    this.swap(nums, k, i);
                    // 递归处理下一个位置
                    this.permuteUnique(nums, k + 1, ans);
                    // 回溯：恢复交换前的状态
                    this.swap(nums, k, i);
                }
            }
            return ans;
        }

        /**
         * 交互数组元素
         * @param nums
         * @param i
         * @param j
         */
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        /**
         * 查找从位置begin开始，end位置上的元素是不是第一次出现
         *
         * @param arr
         * @param begin
         * @param end
         * @return
         */
        private boolean isFirstChar(int[] arr, int begin, int end) {
            if (end > begin) {
                for (int i = begin; i < end; i++) {
                    if (arr[i] == arr[end])
                        return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}