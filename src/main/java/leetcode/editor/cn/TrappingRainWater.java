//42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 
// 👍 2190 👎 0


package leetcode.editor.cn;

import java.util.Stack;

public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        int[] h = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution.trap(h));
        System.out.println(solution.trapDp2(h));
        System.out.println(solution.trapDp(h));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            int sum = 0;
            Stack<Integer> st = new Stack<>();
            for (int i = 0; i < height.length; i++) {
                while (!st.empty() && height[st.peek()] < height[i]) {
                    int mid = st.pop();
                    if (!st.empty()) {
                        int h = Math.min(height[st.peek()], height[i]) - height[mid];
                        int w = i - st.peek() - 1;
                        sum += h * w;
                    }
                }
                st.push(i);
            }

            return sum;
        }


        /**
         * 双指针算法,优化版动态规划,边走边计算,空间复杂度o(1)
         *
         * @param height
         * @return
         */
        public int trapDp2(int[] height) {
            if (height.length == 0) {
                return 0;
            }
            int left = 0, l_max = height[left];
            int right = height.length - 1, r_max = height[right];
            int res = 0;
            while (left <= right) {
                l_max = Math.max(l_max, height[left]);
                r_max = Math.max(r_max, height[right]);
                if (l_max < r_max) {
                    res += l_max - height[left];
                    left++;
                } else {
                    res += r_max - height[right];
                    right--;
                }
            }
            return res;
        }

        /**
         * 动态规划,空间复杂度o(n),备忘录
         *
         * @param height
         * @return
         */
        public int trapDp(int[] height) {
            int sum = 0;
            int[] left = new int[height.length];
            left[0] = height[0];
            for (int i = 1; i < height.length; i++) {
                left[i] = Math.max(height[i], left[i - 1]);
            }
            int[] right = new int[height.length];
            right[height.length - 1] = height[height.length - 1];
            for (int i = height.length - 2; i >= 0; i--) {
                right[i] = Math.max(height[i], right[i + 1]);
            }
            for (int i = 0; i < height.length; i++) {
                int h = Math.min(left[i], right[i]) - height[i];
                if (h > 0) {
                    sum += h;
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}