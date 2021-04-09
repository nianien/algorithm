//给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 num
//s[i] 的元素的数量。 
//
// 
//
// 示例： 
//
// 输入：nums = [5,2,6,1]
//输出：[2,1,1,0] 
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 561 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        Solution solution = new CountOfSmallerNumbersAfterSelf().new Solution();
        System.out.println(solution.countSmaller(new int[]{5, 2, 6, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //复用临时数组,避免创建新数组
        private Element[] temp;
        //存储结果
        private Integer[] ans;

        public List<Integer> countSmaller(int[] nums) {
            this.temp = new Element[nums.length];
            this.ans = new Integer[nums.length];
            //同时存储元素和坐标
            Element[] arr = new Element[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                arr[i] = new Element(nums[i], i);
                ans[i] = 0;
            }
            int l = 0, r = nums.length - 1;
            mergeSort(arr, l, r);
            return Arrays.asList(ans);
        }

        /**
         * 归并排序从l到r
         *
         * @param a
         * @param l
         * @param r
         */
        private void mergeSort(Element[] a, int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = (l + r) >> 1;
            //排序从l到mid
            mergeSort(a, l, mid);
            //排序从mid+1到r
            mergeSort(a, mid + 1, r);
            //从mid切分,归并已排好序的两部分
            merge(a, l, mid, r);
        }

        /**
         * 归并排序, 从l到mid以及mid+1到r已经有序
         *
         * @param a
         * @param l
         * @param mid
         * @param r
         */
        private void merge(Element[] a, int l, int mid, int r) {
            int i = l, j = mid + 1, p = l;
            while (i <= mid && j <= r) {
                if (a[i].val <= a[j].val) {
                    temp[p] = a[i];
                    //当左半部分归并时, 计算右半部分小于当前值的数量
                    ans[a[i].index] += (j - mid - 1);
                    ++i;
                } else {
                    temp[p] = a[j];
                    ++j;
                }
                ++p;
            }
            while (i <= mid) {
                temp[p] = a[i];
                //当左半部分归并时, 计算右半部分小于当前值的数量
                ans[a[i].index] += (j - mid - 1);
                ++i;
                ++p;
            }
            while (j <= r) {
                temp[p] = a[j];
                ++j;
                ++p;
            }
            //复制临时数组中已排好序的元素
//            System.arraycopy(temp, l, a, l, r - l + 1);
            for (int k = l; k <= r; ++k) {
                a[k] = temp[k];
            }
        }

        /**
         * 元素定义,含坐标
         */
        class Element {
            int val;
            int index;

            public Element(int val, int index) {
                this.val = val;
                this.index = index;
            }

            @Override
            public String toString() {
                return "Pair{" +
                        "val=" + val +
                        ", index=" + index +
                        '}';
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}