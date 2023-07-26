//31.next-permutation
//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//
// 必须 原地 修改，只允许使用额外常数空间。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1]
//输出：[1,2,3]
//
//
// 示例 3：
//
//
//输入：nums = [1,1,5]
//输出：[1,5,1]
//
//
// 示例 4：
//
//
//输入：nums = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
//
// Related Topics 数组
// 👍 1173 👎 0

package main

import (
	"fmt"
	"sort"
)

//leetcode submit region begin(Prohibit modification and deletion)

func nextPermutation(nums []int) {
	var l = len(nums)
	var r = 0
	//从找到最后一个升序点
	for e := l - 1; e > 0; e-- {
		if nums[e] > nums[e-1] {
			r = e
			break
		}
	}
	if r > 0 {
		//从升序点(num[i]<num[j])将数组分割两部分，i属于左半部分，j属于右半部分
		//我们的思路是，左部分变为大于当前值的最小值，右部分变为最小值，合并后就是大于当前值的最小值
		//对于左半部分，将i位置替换为右半部分大于num[i]的最小值
		//对于右半部分，直接升序排列
		var t = r
		for i := r; i < l; i++ {
			//大于nums[r-1]的最小值
			if nums[i] > nums[r-1] && nums[i] < nums[t] {
				t = i
			}
		}
		minSwap(nums, r-1, t)
	} else { //如果不存在升序，则已经是最大值
		sort.Ints(nums)
	}
}

/*
*
begin和end交换位置，然后begin之后升序排序
*/
func minSwap(nums []int, begin int, end int) {
	//交换数据
	nums[begin], nums[end] = nums[end], nums[begin]
	//将begin之后的数据排序
	var arr = nums[begin+1:]
	sort.Ints(arr)
	for i, j := range arr {
		nums[begin+1+i] = j
	}
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	//var a = []int{4, 2, 0, 2, 3, 2, 0}
	var a = []int{1, 3, 2}
	nextPermutation(a)
	fmt.Println(a)
}
