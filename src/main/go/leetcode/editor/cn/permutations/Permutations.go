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

// leetcode submit region begin(Prohibit modification and deletion)
func permute(nums []int) [][]int {
	var ans [][]int
	permute_(nums, len(nums), 0, &ans)
	return ans
}

// 要确定其中的一种排列,就必须知道数组中每个位置的元素
// 首先，确定第一个位置，有n种可能，即依次将其他位置的元素与首位置调换
// 每次确定当前位置的元素后，接着递归确定其后位置上的元素
// 当最后一个位置的元素确定时，便得出其中的一种排列
func permute_(nums []int, l int, k int, ans *[][]int) {
	if k == l {
		*ans = append(*ans, append([]int{}, nums...))
	}
	for i := k; i < l; i++ {
		swap(nums, k, i)
		permute_(nums, l, k+1, ans)
		swap(nums, k, i)
	}
}

// 根据校验数组和原始数组计算排列数组
func swap(nums []int, i, j int) {
	//还原排列数组
	temp := nums[i]
	nums[i] = nums[j]
	nums[j] = temp
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(permute([]int{1, 1, 2}))
}
