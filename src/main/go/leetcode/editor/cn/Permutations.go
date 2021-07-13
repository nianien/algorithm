//46.permutations
//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯
// 👍 1440 👎 0

package main

import (
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)
func permute(nums []int) [][]int {
	var ans [][]int
	//校验数组, 记录原数组元素在排列数组中的索引位置
	//这里由于数组初始化为0,因此排列元素的索引起始位置从1开始, 索引大于0表示该元素被使用
	permute_(nums, 0, make([]int, len(nums)), &ans)
	return ans
}

// 由于原数组和生成的排列数组元素相同,只是位置不同,因此我们这里使用一个校验数组存储原数组元素在生成的排列数组中的索引位置
// 如果原数组的元素在校验数组中的索引为0表示该元素尚未参与排列,大于0表示参与排列
// nums: 原始待排列数组
// check: 校验数组,记录原数组元素在排列数组中的索引位置(因为数组默认初始化为0,排列索引起始位置从1开始, 索引大于0表示该元素被使用)
// index: 当前待排列元素索引,当index=n时,表示全部元素参与排列,生成一种排列
func permute_(nums []int, index int, check []int, ans *[][]int) {
	// 根据校验数组和原始数组计算排列数组
	var calculate = func(check []int, origin []int) []int {
		//还原排列数组
		var perm = make([]int, len(origin))
		for i, e := range check {
			//v表示在arr[i]在新数组的索引+1
			//因此新数组索引位置v-1对应的元素就是arr[i]
			perm[e-1] = origin[i]
		}
		return perm
	}
	if len(nums) == index {
		//根据校验数组返回当前排列数组
		*ans = append(*ans, calculate(check, nums))
	}
	for i := range nums {
		//该元素对应索引位置大于0,表示已经被使用
		if check[i] > 0 {
			continue
		}
		//注意: 这里原数组元素在排列数组中的索引位置为从1开始,因此使用index+1
		check[i] = index + 1
		permute_(nums, index+1, check, ans)
		//回溯,重置i元素的索引位置
		check[i] = 0
	}
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	fmt.Println(permute([]int{1, 2, 3}))
}
