//131.palindrome-partitioning
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
//
// 回文串 是正着读和反着读都一样的字符串。
//
//
//
// 示例 1：
//
//
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
//
//
// 示例 2：
//
//
//输入：s = "a"
//输出：[["a"]]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 16
// s 仅由小写英文字母组成
//
//
// Related Topics 字符串 动态规划 回溯 👍 1576 👎 0

package main

import "fmt"

// leetcode submit region begin(Prohibit modification and deletion)
func partition(s string) [][]string {
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

	var res [][]string
	var dfs func(int, []string)
	//统计从0到n深度遍历,分s[0..i]+s[i..j]+s[j..n]
	//i=n时表示完成一次分割
	dfs = func(i int, parts []string) {
		if i == n {
			//这里使用append([]string{}, parts...)是因为parts是共享的,会被改变
			res = append(res, append([]string{}, parts...))
			return
		}
		for j := i; j < n; j++ {
			//dp[i][j]表示s[i..j+1]是否为回文串
			if dp[i][j] {
				//s[i]之前的部分+s[i:j+1]+s[i]之后的部分
				dfs(j+1, append(parts, s[i:j+1]))
			}
		}
	}
	dfs(0, []string{})
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(partition("abbaaaaab"))
}
