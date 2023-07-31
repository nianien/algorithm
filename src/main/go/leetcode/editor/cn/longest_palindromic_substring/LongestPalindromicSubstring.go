//5.longest-palindromic-substring
//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母组成
//
//
// Related Topics 字符串 动态规划 👍 6676 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)
func longestPalindrome(s string) string {
	n := len(s)
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	max := [2]int{0, 0}
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			dp[i][j] = s[i] == s[j]
			if dp[i][j] && j-i > 2 {
				dp[i][j] = dp[i+1][j-1]
			}
			if dp[i][j] && max[1]-max[0] < j-i {
				max = [2]int{i, j}
			}
		}
	}
	return s[max[0] : max[1]+1]
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(longestPalindrome("babad"))
}
