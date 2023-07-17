//45.jump-game-ii
//给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
//
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
//
//
// 0 <= j <= nums[i]
// i + j < n
//
//
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 示例 2:
//
//
//输入: nums = [2,3,0,1,4]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 10⁴
// 0 <= nums[i] <= 1000
// 题目保证可以到达 nums[n-1]
//
//
// Related Topics 贪心 数组 动态规划 👍 2167 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)

func jump(nums []int) int {

	n := len(nums)
	if n <= 1 {
		return 0
	}
	//当前位置可达最远距离
	far := 0
	//上一跳最远距离，到达该位置时，进行下一跳，跳至下一个位置最远距离
	end := 0
	step := 0
	for i := 0; i < n-1; i++ {
		if i+nums[i] > far {
			far = i + nums[i]
		}
		if i == end {
			end = far
			step++
		}
	}
	//判断最远距离是否到达终点
	if far < n-1 {
		return -1
	}
	return step
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {

	//fmt.Println(jump([]int{2, 1}))
	//fmt.Println(jump([]int{1, 3, 2}))
	//fmt.Println(jump([]int{1, 2}))
	//fmt.Println(jump([]int{1, 1, 1, 1, 1}))
	//fmt.Println(jump([]int{1, 2, 1, 1, 1}))
	//fmt.Println(jump([]int{2, 3, 1, 1, 4}))
	fmt.Println(jump([]int{3, 2, 1, 0, 4}))
}
