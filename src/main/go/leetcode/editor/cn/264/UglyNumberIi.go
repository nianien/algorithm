//264.ugly-number-ii
//给你一个整数 n ，请你找出并返回第 n 个 丑数 。
//
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
//
//
//
// 示例 1：
//
//
//输入：n = 10
//输出：12
//解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//解释：1 通常被视为丑数。
//
//
//
//
// 提示：
//
//
// 1 <= n <= 1690
//
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 1090 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)
// 1,2,3,5
func nthUglyNumber(n int) int {
	return findUgly0(n)[n-1]
}

// 每一个丑数都是由之前的丑数乘以2/3/5得到的,我们只需分别记录最后乘以2/3/5的丑数,然后比较其再次乘以2/3/5之后的大小
func findUgly0(n int) []int {
	dp := make([]int, n)
	dp[0] = 1
	//记录乘以2/3/5后成为下一个最小丑数的位置
	p2, p3, p5 := 0, 0, 0
	for i := 1; i < n; i++ {
		//将上次乘以2/3/5的丑数再次乘以2/3/5,获取下一个丑数
		x2, x3, x5 := dp[p2]*2, dp[p3]*3, dp[p5]*5
		dp[i] = min235(x2, x3, x5)
		//可能乘以2/3/5后相等,如2*3和3*2,所以，此时乘以2/3的丑数都将后移
		if dp[i] == x2 {
			p2++
		}
		if dp[i] == x3 {
			p3++
		}
		if dp[i] == x5 {
			p5++
		}
	}
	return dp
}

func min235(a, b, c int) int {
	if a <= b && a <= c {
		return a
	}
	if b <= a && b <= c {
		return b
	}
	return c
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(findUgly0(10))
}
