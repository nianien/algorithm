//40.combination-sum-ii
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//
// 示例 2:
//
//
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//]
//
//
//
// 提示:
//
//
// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30
//
// Related Topics 数组 回溯
// 👍 624 👎 0

package main

import (
	"fmt"
	"sort"
)

// leetcode submit region begin(Prohibit modification and deletion)
func combinationSum2(candidates []int, target int) [][]int {
	sort.Ints(candidates)
	var ans [][]int
	combinationSum2_(candidates, target, []int{}, &ans)
	return ans
}

// 递归分解因子
// 整数n分解为一个因子i后,递归分解n-i, 直至n=0, 如果n<0则表示无法分解为当前因子
// 同时为了避免重复计算, 分解因子时,只能分解为大于等于当前因子的因子
func combinationSum2_(candidates []int, target int, temp []int, ans *[][]int) {
	if target == 0 {
		*ans = append(*ans, append([]int{}, temp...))
	}
	if target <= 0 {
		return
	}
	for i, c := range candidates {
		//过滤相同的因子,避免重复计算
		if i == 0 || candidates[i] != candidates[i-1] {
			combinationSum2_(candidates[i+1:], target-c, append(temp, c), ans)
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	var candidates = []int{10, 1, 2, 7, 6, 1, 5}
	var target = 8
	fmt.Println(combinationSum2(candidates, target))
}
