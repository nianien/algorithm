//15.3sum
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 3321 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        System.out.println(solution.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int l = i + 1, r = nums.length - 1;
                int target = -nums[i];
                while (l < r) {
                    int number = nums[l] + nums[r];
                    if (number == target) {
                        results.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        //判断下一个字符
                        for (l++; l < r && nums[l] == nums[l - 1]; l++) ;
                        for (r--; l < r && nums[r] == nums[r + 1]; r--) ;
                    } else if (number < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return results;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}