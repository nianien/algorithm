//32.longest-valid-parentheses
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
//
//
//
//
// 示例 1：
//
//
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
//
// 示例 2：
//
//
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
//
// 示例 3：
//
//
//输入：s = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 3 * 104
// s[i] 为 '(' 或 ')'
//
//
//
// Related Topics 栈 字符串 动态规划
// 👍 1362 👎 0

package main

import (
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)

func longestValidParentheses(s string) int {
	return longestValidParentheses2(s)
}

/*
*
利用栈记录未匹配元素的索引，首先栈底元素存储最后一个未匹配的")"的索引位置, 如此, 则栈顶元素为当前最长有效括号起始位置的前一个位置
那么，当前有效括号的长度=当前索引位置-栈顶元素的索引位置
*/
func longestValidParentheses1(s string) int {
	maxAns := 0
	//为了统一边界处理, 栈底元素")"的索引初始为-1
	var stack = []int{-1}
	for i := range len(s) {
		if s[i] == '(' {
			stack = append(stack, i)
		} else {
			//弹出栈顶元素
			stack = stack[:len(stack)-1]
			if len(stack) == 0 {
				//栈为空,说明出栈元素为")",未完成当前匹配, 需要更新未匹配")"的索引位置,重新入栈
				stack = append(stack, i)
			} else {
				//栈不为空,说明出栈元素为"(", 完成当前括号匹配,此时可以计算当前有效括号长度
				//栈顶元素为当前最长有效括号起始位置的前一个位置, 要么是"(",要么是未匹配的")"
				maxAns = max_32(i-stack[len(stack)-1], maxAns)
			}
		}
	}
	return maxAns
}

/*
*

	用栈模拟一遍，将所有能够匹配的括号的位置全部置1, 问题等价于求元素连续为1的最大长度
*/
func longestValidParentheses2(s string) int {
	var stack []int
	var arr = make([]int, len(s))
	for i := range s {
		if s[i] == ')' {
			if len(stack) > 0 {
				//判断栈顶元素
				if t := stack[len(stack)-1]; s[t] == '(' {
					arr[t] = 1
					arr[i] = 1
				}
				//移除栈顶元素
				stack = stack[:len(stack)-1]
			}
		} else {
			stack = append(stack, i)
		}
	}
	var max, cnt = 0, 0
	for _, i := range arr {
		if i == 1 {
			cnt += 1
		} else {
			max = max_32(cnt, max)
			cnt = 0
		}
	}
	return max_32(cnt, max)
}

/*
*
贪心算法
*/
func longestValidParentheses3(s string) int {
	left, right, maxLength := 0, 0, 0
	for i := range len(s) {
		if s[i] == '(' {
			left++
		} else {
			right++
		}
		if left == right {
			maxLength = max_32(maxLength, 2*right)
		} else if right > left {
			left, right = 0, 0
		}
	}
	left, right = 0, 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] == '(' {
			left++
		} else {
			right++
		}
		if left == right {
			maxLength = max_32(maxLength, 2*left)
		} else if left > right {
			left, right = 0, 0
		}
	}
	return maxLength
}

func max_32(x, y int) int {
	if x > y {
		return x
	}
	return y
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {

	fmt.Println(longestValidParentheses1("(()"))
	fmt.Println(longestValidParentheses2("(()"))
	fmt.Println(longestValidParentheses3("(()"))
	fmt.Println(longestValidParentheses(")()())"))
	fmt.Println(longestValidParentheses3("(()"))
	fmt.Println(longestValidParentheses("(()(()"))
	fmt.Println(longestValidParentheses(")()())"))

	fmt.Println(longestValidParentheses(")()())()()("))
	fmt.Println(longestValidParentheses("(()(((()"))
}
