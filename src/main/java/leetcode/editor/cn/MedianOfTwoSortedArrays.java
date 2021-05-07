//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治算法 
// 👍 3924 👎 0


package leetcode.editor.cn;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        int[] b = {1};
        int[] a = {2, 3, 4, 5, 6};

        System.out.println(solution.findMedianSortedArrays(a, b));
        System.out.println(solution.findMedianSortedArrays2(a, b));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays2(int[] a, int[] b) {
            if (a.length > b.length) {
                return findMedianSortedArrays2(b, a);
            }
            int m = a.length;
            int n = b.length;
            int left = 0, right = m;
            // median1：前一部分的最大值
            // median2：后一部分的最小值
            int median1 = 0, median2 = 0;

            while (left <= right) {
                // 前一部分包含 a[0 .. i-1] 和 b[0 .. j-1]
                // 后一部分包含 a[i .. m-1] 和 b[j .. n-1]
                int i = (left + right) / 2;
                int j = (m + n + 1) / 2 - i;
                // a_left_max, a_right_min, b_left_max, b_right_min 分别表示 a[i-1], a[i], b[j-1], b[j]
                int a_left_max = (i == 0 ? Integer.MIN_VALUE : a[i - 1]);
                int a_right_min = (i == m ? Integer.MAX_VALUE : a[i]);
                int b_left_max = (j == 0 ? Integer.MIN_VALUE : b[j - 1]);
                int b_right_min = (j == n ? Integer.MAX_VALUE : b[j]);

                if (a_left_max <= b_right_min) {
                    median1 = Math.max(a_left_max, b_left_max);
                    median2 = Math.min(a_right_min, b_right_min);
                    left = i + 1;
                } else {
                    right = i - 1;
                }
            }
            return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
        }

        public double findMedianSortedArrays(int[] a, int[] b) {
            int k1 = (a.length + b.length + 1) / 2;
            int k2 = (a.length + b.length + 2) / 2;
            return (findKth(a, 0, b, 0, k1) + findKth(a, 0, b, 0, k2)) / 2.0;
        }


        /**
         * 因为递归过程是排除K之前的元素,因此需要递归修正起始索引位置
         *
         * @param arr1   第一个数组
         * @param start1 第一个数组起始位置
         * @param arr2   第二个数组
         * @param start2 第二个数组起始位置
         * @param k
         * @return
         */
        private int findKth(int[] arr1, int start1, int[] arr2, int start2, int k) {
            if (start1 == arr1.length) {
                return arr2[start2 + k - 1];
            }
            if (start2 == arr2.length) {
                return arr1[start1 + k - 1];
            }
            if (k == 1) {
                return Math.min(arr1[start1], arr2[start2]);
            }
            //定位第k/2个元素的索引
            int index1 = Math.min(arr1.length - 1, start1 + k / 2);
            int index2 = Math.min(arr2.length - 1, start2 + k / 2);
            //判定两个数组第(k/2)个元素的大小,对于较小的数组,前(k/2)个元素一定属于top(k/2),因此只需在剩余元素中查找top(k/2  )即可
            if (arr1[index1] < arr2[index2]) {
                return findKth(arr1, index1 + 1, arr2, start2, k - (index1 - start1 + 1));
            } else {
                return findKth(arr1, start1, arr2, index2 + 1, k - (index2 - start2 + 1));
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}