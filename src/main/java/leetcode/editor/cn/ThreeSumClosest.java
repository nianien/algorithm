//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 
// 👍 724 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int len = nums.length;
            Arrays.sort(nums);
            int ans = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < len; i++) {
                int l = i + 1;
                int r = len - 1;
                while (l < r) {
                    int res = nums[i] + nums[l] + nums[r];
                    if (Math.abs(target - res) < Math.abs(target - ans)) {
                        ans = res;
                    }
                    if (res > target) {
//                        for (r--; l < r && nums[r] == nums[r + 1]; r--) ;
                        r--;
                    } else if (res < target) {
                        l++;
//                        for (l++; l < r && nums[l] == nums[l - 1]; l++) ;
                    } else {
                        return target;
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}