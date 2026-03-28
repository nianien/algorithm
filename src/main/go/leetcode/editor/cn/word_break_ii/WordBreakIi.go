//140.word-break-ii
//给定一个字符串 s 和一个字符串字典
// wordDict ，在字符串
// s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
//
// 注意：词典中的同一个单词可能在分段中被重复使用多次。
//
//
//
// 示例 1：
//
//
//输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//输出:["cats and dog","cat sand dog"]
//
//
// 示例 2：
//
//
//输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
//"pineapple"]
//输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//解释: 注意你可以重复使用字典中的单词。
//
//
// 示例 3：
//
//
//输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//输出:[]
//
//
//
//
// 提示：
//
//
//
//
//
// 1 <= s.length <= 20
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 10
// s 和 wordDict[i] 仅有小写英文字母组成
// wordDict 中所有字符串都 不同
//
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 回溯 👍 705 👎 0

package main

import (
	"fmt"
	"strings"
)

// leetcode submit region begin(Prohibit modification and deletion)
func wordBreak(s string, wordDict []string) []string {
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
	for i := range l + 1 {
		for j := range i {
			if dp[j] && wMap[s[j:i]] {
				dp[i] = true
				break
			}
		}
	}
	var res []string
	dfs(s, wMap, dp, l, []string{}, &res)
	return res
}

func dfs(s string, wordDict map[string]bool, dp []bool, l int, paths []string, res *[]string) {
	if l == 0 {
		*res = append(*res, strings.Join(paths, " "))
	}
	for i := l - 1; i >= 0; i-- {
		e := s[i:l]
		if wordDict[e] && dp[i] {
			//数组指定位置插入元素:
			//s=append(s[:i], append([]T{e}, slice[i:]...))
			//数组向前插入元素
			//s=append([]T{e}, s...)
			dfs(s, wordDict, dp, i, append([]string{e}, paths...), res)
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(wordBreak("leetcode", []string{"leet", "code"}))
	fmt.Println(wordBreak("catsanddog", []string{"cat", "cats", "and", "sand", "dog"}))
}
