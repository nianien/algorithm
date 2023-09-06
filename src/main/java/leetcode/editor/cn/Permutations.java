//46.给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1204 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            permute(nums, 0, list);
            return list;
        }


        /**
         * 对字符数组进行全排列，递归确定位置k上的元素
         *
         * @param arr
         * @param k
         */
        private int permute(int[] arr, int k, List<List<Integer>> result) {
            if (k == arr.length - 1) {
                // 此时，数组最后一个位置k上的元素已确定，从而确定了一种排列
                result.add(toList(arr));
                return 1;
            }
            int count = 0;
            // 位置k上的元素有ch.Length-k+1种可能值
            // 即k和其后位置上的元素都可能放在k位置上
            for (int i = k; i < arr.length; i++) {
                // 确定i位置上的字符是不是第一次出现，
                // 如果不是第一次出现，则相同的字符已经调换过
                if (this.isFirstChar(arr, k, i)) {
                    this.swap(arr, k, i);
                    count += this.permute(arr, k + 1, result);
                    this.swap(arr, k, i);
                }
            }
            return count;
        }

        /**
         * 交换字符数组元素
         *
         * @param arr
         * @param i
         * @param j
         */
        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        /**
         * 查找从位置begin开始，end位置上的元素是不是第一次出现
         *
         * @param arr
         * @param begin
         * @param end
         * @return
         */
        private boolean isFirstChar(int[] arr, int begin, int end) {
            if (end > begin) {
                for (int i = begin; i < end; i++) {
                    if (arr[i] == arr[end]){
                        return false;
                    }
                }
            }
            return true;
        }

        private List<Integer> toList(int[] nums) {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int num : nums) {
                list.add(num);
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}