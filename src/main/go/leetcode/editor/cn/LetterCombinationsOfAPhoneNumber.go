////17.letter-combinations-of-a-phone-number
////给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
////
//// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
////
////
////
////
////
//// 示例 1：
////
////
////输入：digits = "23"
////输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
////
////
//// 示例 2：
////
////
////输入：digits = ""
////输出：[]
////
////
//// 示例 3：
////
////
////输入：digits = "2"
////输出：["a","b","c"]
////
////
////
////
//// 提示：
////
////
//// 0 <= digits.length <= 4
//// digits[i] 是范围 ['2', '9'] 的一个数字。
////
//// Related Topics 深度优先搜索 递归 字符串 回溯算法
//// 👍 1341 👎 0
//
//package main
//
//import (
//	"fmt"
//)
//
//var buttons = [8]string{
//	"abc",
//	"def",
//	"ghi",
//	"jkl",
//	"mno",
//	"pqrs",
//	"tuv",
//	"wxyz",
//}
//
//func backtrack(i int, j int, res []string) []string {
//	res = append(res, string(buttons[i][j]))
//	if i == len(buttons)-1 {
//
//	}
//	for i2, i3 := range collection {
//
//	}
//	return res
//
//}
//
////leetcode submit region begin(Prohibit modification and deletion)
//func letterCombinations(digits string) []string {
//	var res = make([]string, len(digits))
//	println(res)
//	// 取每个数字
//	for i, digit := range digits {
//		// 每个数字对应的字母
//		for j, letter := range buttons[digit-'0'-2] {
//			res[i] = string(letter)
//		}
//
//	}
//	//println(buttons)
//	return nil
//}
//
////leetcode submit region end(Prohibit modification and deletion)
//
////test from here
//func main() {
//	letterCombinations("299")
//	fmt.Println()
//}
