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

import (
	"container/list"
)

/**
利用栈,使用i来记录当前遍历到的位置，使用 j 来记录最近的最长有效括号的开始位置的「前一个位置」
*/
func longestValidParentheses(s string) int {
	//i为当前遍历到的元素，如果s[i]为")", 则包含该元素的最长有效括号为连续已匹配括号前的位置
	//该位置的元素要么为"(", 要么为最后一个未匹配的")"
	//计算当前括号有效长度时, 先出栈,如果出栈后,栈为空,则说明当前")"为匹配, 则更新最后一个未匹配的")"索引位置
	//否则,计算当前括号长度为:i-栈顶元素的索引,然后更新最大值
	maxAns := 0
	var stack = list.New()
	stack.PushBack(-1)
	for i := 0; i < len(s); i++ {
		if s[i] == '(' {
			stack.PushBack(i)
		} else {
			//弹出栈顶元素
			var tail = stack.Remove(stack.Back()).(int)
			if s[tail] == '(' {
				//出栈元素为"(", 说明当前完成括号匹配,可以计算当前括号长度
				//此时栈顶元素为当前最长有效括号起始位置的前一个位置, 要么是"(",要么是未匹配的")"
				var tail = stack.Back().Value.(int)
				maxAns = max_32(i-tail, maxAns)
			} else {
				//出栈元素为")",则说明栈已空,更新未匹配的")"
				stack.PushBack(i)
			}
		}
	}
	return maxAns
}

func longestValidParentheses3(s string) int {
	left, right, maxLength := 0, 0, 0
	for i := 0; i < len(s); i++ {
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

/**
  用栈模拟一遍，将所有能够匹配的括号的位置全部置1, 问题等价于求元素连续为1的最大长度
*/
func longestValidParentheses2(s string) int {
	var stack = list.New()
	var arr = make([]int, len(s))
	for i, e := range s {
		var ch = string(e)
		if ch == ")" {
			if stack.Len() > 0 {
				var t = stack.Remove(stack.Back()).(int)
				if string(s[t]) == "(" {
					arr[t] = 1
					arr[i] = 1
				}
			}
		} else {
			stack.PushBack(i)
		}
	}
	var max, cnt = 0, 0
	for _, i := range arr {
		if i == 1 {
			cnt += 1
		} else {
			if cnt > max {
				max = cnt
			}
			cnt = 0
		}
	}
	if cnt > max {
		max = cnt
	}
	return max
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {

	fmt.Println(longestValidParentheses("(()"))
	//fmt.Println(longestValidParenthesesByDp("(()"))
	//fmt.Println(longestValidParentheses("(()(()"))
	//fmt.Println(longestValidParentheses(")()())"))
	//
	//fmt.Println(longestValidParentheses(")()())()()("))
	//fmt.Println(longestValidParentheses("(()(((()"))
}
