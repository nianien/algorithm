//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 
//
// 
//
// 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 300 
// -231 <= nums[i] <= 231 - 1 
// 
// Related Topics 数组 
// 👍 1004 👎 0


package leetcode.editor.cn;

public class FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new FirstMissingPositive().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            if (nums.length <= 0) {
                return 1;
            }
            /*巧妙之处在这里，看似双重循环，其实只遍历了一遍数组，因为在内层交换过后，存在一部分i==a[i],当外层遍历到该元素，就不会进入循环内，相当于只遍历了一遍数组。*/
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] > 0 && nums[i] < nums.length && (nums[i] != i)) {
                    int temp = nums[i];
                    if (nums[temp] == nums[i]) {
                        break;
                    }
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != i) {
                    return i;
                }
            }
            return nums.length + (nums[0] == nums.length ? 1 : 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}