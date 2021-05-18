//26.remove-duplicates-from-sorted-array
//给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。 
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。 
//
// 
//
// 说明: 
//
// 为什么返回数值是整数，但输出的答案是数组呢? 
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下: 
//
// 
//// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：2, nums = [1,2]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3 * 104 
// -104 <= nums[i] <= 104 
// nums 已按升序排列 
// 
//
// 
// Related Topics 数组 双指针 
// 👍 2060 👎 0

package leetcode.editor.cn


object RemoveDuplicatesFromSortedArray extends App {

    //leetcode submit region begin(Prohibit modification and deletion)
    object Solution {
        /**
         * 使用两个指针，右指针始终往右移动，
         * 如果右指针指向的值等于左指针指向的值，左指针不动。
         * 如果右指针指向的值不等于左指针指向的值，那么左指针往右移一步，然后再把右指针指向的值赋给左指针。
         *
         * @param nums
         * @return
         */
        def removeDuplicates(nums: Array[Int]): Int = {
            if (nums.length <= 1) {
                return nums.length
            }
            var left = 0
            for (right <- 1 until nums.length) {
                if (nums(left) != nums(right)) {
                    left += 1
                    nums(left) = nums(right)
                }
            }
            left + 1
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    //test from here
    val arr = Array(0, 1, 1, 1, 2, 2, 2, 3, 4, 5, 5)
    val size = Solution.removeDuplicates(arr)
    println(arr.mkString(","))
    println(arr.slice(0, size).mkString(","))
}