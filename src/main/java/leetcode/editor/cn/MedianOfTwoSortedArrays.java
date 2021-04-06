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
        int[] b = {2, 3, 5, 6};
        int[] a = {4, 5, 6, 7, 8, 9, 10};
        int total = a.length + b.length;
        int mid = total / 2;
        if (total % 2 == 0) {
            int topk1 = solution.findTopK(a, b, 0, 0, mid);
            int topk2 = solution.findTopK(a, b, 0, 0, mid + 1);
            System.out.println("==>[k,k+1]/2:" + (topk1 + topk2) / 2.0);
        } else {
            System.out.println("==>[k+1]:" + solution.findTopK(a, b, 0, 0, (total + 1) / 2));
        }
        System.out.println(solution.findMedianSortedArrays(a, b));
        int k = 11;
        System.out.println(solution.findTopK(a, b, 0, 0, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] a, int[] b) {
            if (a.length > b.length) {
                return findMedianSortedArrays(b, a);
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

        /**
         * 因为递归过程是排除K之前的元素,因此需要递归修正起始索引位置
         *
         * @param arr1   第一个数组
         * @param arr2   第二个数组
         * @param start1 第一个数组起始位置
         * @param start2 第二个数组起始位置
         * @param k
         * @return
         */
        private int findTopK(int[] arr1, int[] arr2, int start1, int start2, int k) {
            if (start1 == arr1.length) {
                return arr2[start2 + k - 1];
            }
            if (start2 == arr2.length) {
                return arr1[start1 + k - 1];
            }
            if (k == 1) {
                return Math.min(arr1[start1], arr2[start2]);
            }
            //查找剩余的k/2个元素, 如果不足k/2,则查找剩余全部元素
            int mid1 = Math.min(arr1.length - start1, k / 2);
            int mid2 = Math.min(arr2.length - start2, k / 2);
            //判定两个数组第mid个元素的大小,对于mid较小的数组,前mid个元素一定属于top(k/2),因此只需在剩余元素中查找top(k-mid)即可
            if (arr1[start1 + mid1 - 1] < arr2[start2 + mid2 - 1]) {
                return findTopK(arr1, arr2, start1 + mid1, start2, k - mid1);
            } else {
                return findTopK(arr1, arr2, start1, start2 + mid2, k - mid2);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}