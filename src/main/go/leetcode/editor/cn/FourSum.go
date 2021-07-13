//18.4sum
//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。
//
// 注意：答案中不可以包含重复的四元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [], target = 0
//输出：[]
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109
//
// Related Topics 数组 双指针 排序
// 👍 895 👎 0

package main

import (
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)

import (
	"sort"
)

/**
golang可以给一个函数的返回值指定名字。如果指定了一个返回值的名字，则可以视为在该函数的第一行中定义了该名字的变量。
*/
func fourSum(nums []int, target int) (quadruplets [][]int) {
	sort.Ints(nums)
	var n = len(nums)
	for i := 0; i < len(nums)-3; i++ {
		if i > 0 && nums[i] == nums[i-1] || nums[i]+nums[n-3]+nums[n-2]+nums[n-1] < target {
			continue
		}
		for j := i + 1; j < n-2; j++ {
			//最小值大于目标值,跳出
			if nums[i]+nums[j]+nums[j+1]+nums[j+2] > target {
				break
			}
			//最大值小于目标值,j自增
			if j > i+1 && nums[j] == nums[j-1] || nums[i]+nums[j]+nums[n-2]+nums[n-1] < target {
				continue
			}
			//目标值介于最大和最小值中间,寻找边界
			for left, right := j+1, n-1; left < right; {
				if sum := nums[i] + nums[j] + nums[left] + nums[right]; sum == target {
					quadruplets = append(quadruplets, []int{nums[i], nums[j], nums[left], nums[right]})
					for left++; left < right && nums[left] == nums[left-1]; left++ {
					}
					for right--; left < right && nums[right] == nums[right+1]; right-- {
					}
				} else if sum < target {
					left++
				} else {
					right--
				}
			}
		}
	}
	return
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	fmt.Println(fourSum([]int{1, 0, -1, 0, -2, 2}, 0))
}
