//139.word-break
//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
//
//
//
// 示例 1：
//
//
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
//
//
// 示例 2：
//
//
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
//
//
// 示例 3：
//
//
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 300
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 20
// s 和 wordDict[i] 仅有小写英文字母组成
// wordDict 中的所有字符串 互不相同
//
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 👍 2212 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)

func wordBreak(s string, wordDict []string) bool {
	wMap := make(map[string]bool)
	for _, w := range wordDict {
		wMap[w] = true
	}
	l := len(s)
	//dp[i]表示长度为i的s[0:i-1]子串是否能拆分成单词
	dp := make([]bool, l+1)
	//默认空字符串符合条件
	dp[0] = true
	//判断前i个字符是否能拆分成单词
	//如果前s[0..j-1]和s[j..i]能拆成单词,则s[0..i]则能拆成单词
	for i := 0; i <= l; i++ {
		for j := 0; j < i; j++ {
			if dp[j] && wMap[s[j:i]] {
				dp[i] = true
				break
			}
		}
	}
	return dp[l]
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(wordBreak("leetcode", []string{"leet", "code"}))
}
