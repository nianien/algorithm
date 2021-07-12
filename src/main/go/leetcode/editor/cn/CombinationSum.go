//39.combination-sum
//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。
//
// 说明：
//
//
// 所有数字（包括 target）都是正整数。
// 解集不能包含重复的组合。
//
//
// 示例 1：
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
//
//
// 示例 2：
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//]
//
//
//
// 提示：
//
//
// 1 <= candidates.length <= 30
// 1 <= candidates[i] <= 200
// candidate 中的每个元素都是独一无二的。
// 1 <= target <= 500
//
// Related Topics 数组 回溯
// 👍 1423 👎 0

package main

import (
	"fmt"
	"sort"
)

//leetcode submit region begin(Prohibit modification and deletion)

func combinationSum(candidates []int, target int) [][]int {
	sort.Ints(candidates)
	var ans [][]int
	combinationSum_(candidates, target, []int{}, &ans)
	return ans
}

//递归分解因子
//整数n分解为一个因子i后,递归分解n-i, 直至n=0, 如果n<0则表示无法分解为当前因子
//同时为了避免重复计算, 分解因子时,只能分解为大于等于当前因子的因子
func combinationSum_(candidates []int, target int, temp []int, ans *[][]int) {
	if target == 0 {
		*ans = append(*ans, append([]int{}, temp...))
		return
	}
	if target <= 0 {
		return
	}
	for _, c := range candidates {
		var l = len(temp)
		//保证因子有序,避免重复计算
		if l == 0 || c >= temp[l-1] {
			combinationSum_(candidates, target-c, append(temp, c), ans)
		}
	}
}

//递归算法
func combinationSum_r(candidates []int, target int, i int, temp []int, ans *[][]int) {
	if target == 0 {
		var dst = make([]int, len(temp))
		copy(dst, temp)
		*ans = append(*ans, dst)
	}
	if target <= 0 || i == len(candidates) {
		return
	}
	//第i个元素作为因子之后, 递归分解
	combinationSum_r(candidates, target-candidates[i], i, append(temp, candidates[i]), ans)
	//从i+1一个元素重新开始分解
	combinationSum_r(candidates, target, i+1, temp, ans)
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	var candidates = []int{2, 3, 5}
	var target = 8
	fmt.Println(combinationSum(candidates, target))

}
