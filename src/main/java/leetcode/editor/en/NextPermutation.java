//31.next-permutation
/**
 * A permutation of an array of integers is an arrangement of its members into a
 * sequence or linear order.
 * <p>
 * <p>
 * For example, for arr = [1,2,3], the following are all the permutations of arr:
 * [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * <p>
 * <p>
 * The next permutation of an array of integers is the next lexicographically
 * greater permutation of its integer. More formally, if all the permutations of the
 * array are sorted in one container according to their lexicographical order, then
 * the next permutation of that array is the permutation that follows it in the
 * sorted container. If such arrangement is not possible, the array must be rearranged
 * as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does
 * not have a lexicographical larger rearrangement.
 * <p>
 * <p>
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * <p>
 * Related Topics Array Two Pointers 👍 21296 👎 5110
 */

package leetcode.editor.en;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        int[] nums = new int[]{1, 3, 2};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1, 1, 5};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;

            for (; i >= 0 && nums[i] >= nums[i + 1]; i--) ;
            if (i >= 0) {
                //使i之后值最大
                int j = nums.length - 1;
                for (; j > 0 && nums[j] <= nums[i]; j--) ;
                swap(nums, i, j);
            }
            //使i之后值最小
            reverse(nums, i + 1, nums.length - 1);
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        private void reverse(int[] nums, int l, int r) {
            while (l < r) {
                swap(nums, l++, r--);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}