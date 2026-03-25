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
                // 此时，数组最后一个位置k上的元素已确定，从而确定了一种排列
                ans.add(Arrays.stream(nums).boxed().toList());
                return ans;
            }
            // 位置k上的元素有ch.Length-k+1种可能值
            // 即k和其后位置上的元素都可能放在k位置上
            for (int i = k; i < nums.length; i++) {
                // 确定i位置上的字符是不是第一次出现，
                // 如果不是第一次出现，则相同的字符已经调换过
                if (this.isFirstChar(nums, k, i)) {
                    //将i位置的元素移动到k之前
                    this.swap(nums, k, i);
                    this.permuteUnique(nums, k + 1, ans);
                    //将k位置的元素移动到i之后
                    this.swap(nums, k, i);
                }
            }
            return ans;
        }

        private void swap(int[] nums, int i, int j) {
            //还原排列数组
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