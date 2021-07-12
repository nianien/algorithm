//47.permutations-ii
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
// Related Topics 数组 回溯
// 👍 742 👎 0

package main

import (
	"fmt"
	"sort"
)

//leetcode submit region begin(Prohibit modification and deletion)
func permuteUnique(nums []int) [][]int {
	sort.Ints(nums)
	var check = make([]int, len(nums))
	var ans [][]int
	permuteUnique_(nums, 0, check, &ans)
	return ans
}

func permuteUnique_(nums []int, index int, check []int, ans *[][]int) {
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
		//为了解决重复元素的问题, 只需要保证在填第idx个元素的时候, 重复数字只会被填入一次即可
		//也就是每次填入的数一定是这个数所在集合中「从左往右第一个未被填过的数字」
		//这里的判断逻辑也就是, 如果上一个元素与当前元素值相同且且未使用过, 那么该元素就不能使用了
		if check[i] > 0 || i > 0 && check[i-1] == 0 && nums[i] == nums[i-1] {
			continue
		}
		//注意: 这里原数组元素在排列数组中的索引位置为从1开始,因此使用index+1
		check[i] = index + 1
		permuteUnique_(nums, index+1, check, ans)
		//回溯,重置i元素的索引位置
		check[i] = 0
	}
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	fmt.Println(permuteUnique([]int{1, 2, 3}))
	fmt.Println(permuteUnique([]int{1, 1, 2}))
}
