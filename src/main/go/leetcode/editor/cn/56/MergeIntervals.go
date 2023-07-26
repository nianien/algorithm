//56.merge-intervals
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2：
//
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104
//
// Related Topics 数组 排序
// 👍 997 👎 0

package main

import (
	"fmt"
	"sort"
)

// leetcode submit region begin(Prohibit modification and deletion)
func merge(intervals [][]int) [][]int {
	var compare = func(a []int, b []int) bool {
		return a[0] < b[0] || a[0] == b[0] && a[1] < b[1]
	}
	sort.Slice(intervals, func(i int, j int) bool {
		return compare(intervals[i], intervals[j])
	})
	var ans [][]int
	for i, c := range intervals {
		if i == 0 {
			ans = append(ans, c)
		} else {
			var last = ans[len(ans)-1]
			//重叠
			if last[1] >= c[0] {
				last[1] = max_56(last[1], c[1])
			} else {
				ans = append(ans, c)
			}
		}
	}
	return ans
}

func max_56(x int, y int) int {
	if x > y {
		return x
	}
	return y
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	var arr = [][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}}
	fmt.Println(merge(arr))
	arr = [][]int{{1, 4}, {5, 6}}
	fmt.Println(merge(arr))
}
