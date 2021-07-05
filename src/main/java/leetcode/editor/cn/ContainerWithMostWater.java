//11.container-with-most-water
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 数组 双指针 
// 👍 2446 👎 0


package leetcode.editor.cn;

public class ContainerWithMostWater {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 我们使用双指针指向数组的左右边界，作为容器的初始边界。然后将较小数字的那个指针往另一个指针的方向移动一个位置, 计算移动后的容器容量。
         * 重复该操作，直到指针重合，每次指针移动过程中的容器容量最大值即是所求结果。
         * 至于为什么要移动较小指针，这里可以采用反正法证明：
         * 假设当前左指针和右指针指向的数分别为x和y，不失一般性，我们假设x≤y。同时，两个指针之间的距离为t。那么，它们组成的容器的容量为：
         * min(x,y)*t=x*t
         * 我们任意向左移动右指针，指向的数为y1，两个指针之间的距离为t1，那么显然有t1<t且min(x,y1) ≤ x = min(x,y)
         * 因此，min(x,y1)*t1<min(x,y)*t，说明不移动小指针的情况下，当前容量已经是最大值。
         *
         * @param height
         * @return
         */
        public int maxArea(int[] height) {
            if (height.length <= 1) {
                return 0;
            }
            int ret = 0;
            int left = 0, right = height.length - 1;
            while (left < right) {
                int h = Math.min(height[left], height[right]);
                int area = h * (right - left);
                if (ret < area) {
                    ret = area;
                }
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return ret;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

}
