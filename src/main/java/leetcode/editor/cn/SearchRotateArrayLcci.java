//搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若
//有多个相同元素，返回索引值最小的一个。 
//
// 示例1: 
//
//  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
// 输出: 8（元素5在该数组中的索引）
// 
//
// 示例2: 
//
//  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
// 输出：-1 （没有找到）
// 
//
// 提示: 
//
// 
// arr 长度范围在[1, 1000000]之间 
// 
// Related Topics 数组 二分查找 
// 👍 41 👎 0


package leetcode.editor.cn;

public class SearchRotateArrayLcci {
    public static void main(String[] args) {
        Solution solution = new SearchRotateArrayLcci().new Solution();
        int[] arr = null;
        arr = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        System.out.println(solution.search(arr, 5));
        System.out.println(solution.search(arr, 11));
        arr = new int[]{1, 1, 1, 1, 1, 2, 1, 1, 1};
        System.out.println(solution.search(arr, 2));
        arr = new int[]{18, 19, 22, 30, 31, 38, 38, 40, 43, 43, 45, 45, 46, 46, 48, 53, 56, 58, 62, 62, 62, 65, 71, 75, 77, 78, 81, -81, -75, -74, -74, -72, -70, -69, -69, -67, -60, -59, -56, -55, -54, -52, -52, -51, -51, -44, -41, -41, -39, -38, -34, -32, -32, -31, -31, -27, -26, -24, -24, -23, -22, -20, -18, -18, -18, -17, -16, -14, -11, -9, -8, -6, -5, -3, -1, 4, 10, 11, 11, 15, 16};
        System.out.println(solution.search(arr, 53));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] arr, int target) {
            int n = search(arr, target, 0, arr.length - 1);
            return n != -1 && arr[n] == arr[0] ? 0 : n;
        }

        private int search(int[] arr, int target, int low, int high) {
//            System.out.println("low=" + low + ",high=" + high);
            if (low > high) {
                return -1;
            }
            low = right(arr, low, high);
            high = left(arr, high, low);

            int mid = (low + high) / 2;
            int left = left(arr, mid, low);
            int right = right(arr, mid, high);
            if (target == arr[mid]) {
                return left;
            }
            if (arr[mid] < arr[high]) {
                if (target >= arr[mid] && target <= arr[high]) {
                    return search(arr, target, right + 1, high);
                } else {
                    return search(arr, target, low, left - 1);
                }
            } else {
                if (target >= arr[low] && target <= arr[mid]) {
                    return search(arr, target, low, left - 1);
                } else {
                    return search(arr, target, right + 1, high);
                }
            }
        }


        private int left(int[] arr, int index, int low) {
            for (int i = index - 1; i >= low; i--) {
                if (arr[i] != arr[index]) {
                    return i + 1;
                }
            }
            return low;
        }

        private int right(int[] arr, int index, int high) {
            for (int i = index + 1; i <= high; i++) {
                if (arr[i] != arr[index]) {
                    return i - 1;
                }
            }
            return high;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}