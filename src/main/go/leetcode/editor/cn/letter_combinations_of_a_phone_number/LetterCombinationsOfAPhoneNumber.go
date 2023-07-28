//17.letter-combinations-of-a-phone-number
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 深度优先搜索 递归 字符串 回溯算法
// 👍 1341 👎 0

package main

import (
	"fmt"
	"strings"
)

//leetcode submit region begin(Prohibit modification and deletion)

var keys = []string{
	"abc",
	"def",
	"ghi",
	"jkl",
	"mno",
	"pqrs",
	"tuv",
	"wxyz",
}

func letterCombinations(digits string) []string {
	var ans []string
	var backtrack func(idx int, digits string, res []string)
	backtrack = func(idx int, digits string, res []string) {
		if idx == len(digits) {
			ans = append(ans, strings.Join(res, ""))
			return
		}
		for _, s := range keys[digits[idx]-'0'-2] {
			res[idx] = string(s)
			backtrack(idx+1, digits, res)
		}
	}
	backtrack(0, digits, make([]string, len(digits)))
	return ans
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(letterCombinations("23"))
}
