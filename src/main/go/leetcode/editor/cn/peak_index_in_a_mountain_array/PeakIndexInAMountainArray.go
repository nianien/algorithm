//852.peak-index-in-a-mountain-array
//符合下列属性的数组 arr 称为 山脉数组 ：
//
// arr.length >= 3
// 存在 i（0 < i < arr.length - 1）使得：
//
// arr[0] < arr[1] < ... arr[i-1] < arr[i]
// arr[i] > arr[i+1] > ... > arr[arr.length - 1]
//
//
//
//
// 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i +
//1] > ... > arr[arr.length - 1] 的下标 i 。
//
//
//
// 示例 1：
//
//
//输入：arr = [0,1,0]
//输出：1
//
//
// 示例 2：
//
//
//输入：arr = [0,2,1,0]
//输出：1
//
//
// 示例 3：
//
//
//输入：arr = [0,10,5,2]
//输出：1
//
//
// 示例 4：
//
//
//输入：arr = [3,4,5,1]
//输出：2
//
//
// 示例 5：
//
//
//输入：arr = [24,69,100,99,79,78,67,36,26,19]
//输出：2
//
//
//
//
// 提示：
//
//
// 3 <= arr.length <= 104
// 0 <= arr[i] <= 106
// 题目数据保证 arr 是一个山脉数组
//
//
//
//
// 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
// Related Topics 二分查找
// 👍 186 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
/**
  本质是一个先升后降数组求最大值
*/
func peakIndexInMountainArray(arr []int) int {
	if len(arr) < 3 {
		return -1
	}
	var begin, end, mid = 0, len(arr) - 1, 0
	for begin <= end {
		mid = (begin + end) / 2
		// 上坡，未到峰顶
		if arr[mid] < arr[mid+1] {
			begin = mid + 1
		} else if arr[mid] > arr[mid+1] { //下坡，已过峰顶
			end = mid - 1
		}
	}
	return mid
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	//fmt.Println(peakIndexInMountainArray([]int{3, 4, 5, 1}))
	fmt.Println(peakIndexInMountainArray([]int{3, 5, 3, 2, 0}))
}
