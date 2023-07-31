//132.palindrome-partitioning-ii
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
//
// 返回符合要求的 最少分割次数 。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
//
//
// 示例 2：
//
//
//输入：s = "a"
//输出：0
//
//
// 示例 3：
//
//
//输入：s = "ab"
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 2000
// s 仅由小写英文字母组成
//
//
// Related Topics 字符串 动态规划 👍 689 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)
func minCut(s string) int {
	n := len(s)
	//dp[i][j]表示s[i..j+1]是否为回文串
	dp := make([][]bool, n)
	//默认为true,避免使用dp[i][j]时还没计算
	for i := range dp {
		dp[i] = make([]bool, n)
	}

	//这里计算顺序对i递减,对j递增
	//保证计算dp[i,j]时, dp[i+1,j-1]已经计算过
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			//i=j,单字符一定是回文串
			//j-i=1,双字符串相等也是回文串
			dp[i][j] = s[i] == s[j]
			//s[i+1..j-1]是回文串且s[i]==s[j]，则是s[i..j]是回文串
			if dp[i][j] && j > i+2 {
				dp[i][j] = dp[i+1][j-1]
			}
		}
	}

	if dp[0][n-1] {
		return 0
	}

	var f = make([]int, n)
	for i := 0; i < n; i++ {
		//s[0..i]为回文串,则最小分割次数为0
		if dp[0][i] {
			continue
		}
		//最大分割次数不超过n
		f[i] = n
		for j := 0; j < i; j++ {
			if dp[j+1][i] && f[j]+1 < f[i] {
				f[i] = f[j] + 1
			}
		}
	}
	return f[n-1]
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(minCut("ababababababababababababcbabababababababababababa"))
}
