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
//        int[] h = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] h = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(solution.trap(h));
        System.out.println(solution.trapDp2(h));
        System.out.println(solution.trapDp(h));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            int sum = 0;
            Stack<Integer> st = new Stack<>();
            //单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组元素递减。
            //如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。
            //如果当前高度大于栈顶的墙的高度，说明之前的积水到这里停下，我们可以计算下有多少积水了。
            //计算完，就把当前的墙继续入栈，作为新的积水的墙。
            for (int i = 0; i < height.length; i++) {
                //当前高度大于栈顶的墙的高度
                while (!st.empty() && height[st.peek()] < height[i]) {
                    int mid = st.pop();
                    if (!st.empty()) {
                        //这里是分层计算积水量
                        //对于下标i, left和right分别是左右离i最近值且大于i的下标
                        //那么计算是: left到right之间高于i部分的积水量
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
            //计算每个i位置的积水量, 取决于左右两边墙的最大高度
            while (left <= right) {
                l_max = Math.max(l_max, height[left]);
                r_max = Math.max(r_max, height[right]);
                //如果左边最大值较小,则从左边技术每个位置的积水量
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