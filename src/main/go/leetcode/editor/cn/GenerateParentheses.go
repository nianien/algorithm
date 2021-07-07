//22.generate-parentheses
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯
// 👍 1859 👎 0

package main

import (
	"fmt"
	"strings"
)

//leetcode submit region begin(Prohibit modification and deletion)
func generateParenthesis(n int) []string {
	return generateParenthesis_(n, 0)
}

//编写一个函数以生成n个小括号，和m个大括号，要求输出格式正确的括号的所有组合。例如，
//给定n = 1，m=1
//输出为 ({}) ,(){}, {}(), {()}
//给定n = 2，m=1
//输出为{}()(), {()}(),{()()}, ({})(),(){}(),(){()}, ()({}), ()(){},{}(()),{(())},({}()),({()}),(({})),((){}),(()){}
//
//1）描述你的设计思路：
//
//2）代码如下：
//
//3）编写对应的测试用例：
//
//4）运行结果如下：
//输出n = 3， m=1 的所有运行结果
//输出n = 1， m=2 的所有运行结果
//
//输入格式统一为：
//3,1
//1,2

/**
题目升级，同时支持大括号和小括号的生成； 当大括号个数为0时，降级为原题目
*/
func generateParenthesis_(n int, m int) []string {
	var ans []string
	doGenerateParenthesis("", "", n, m, 0, 0, 0, 0, &ans)
	return ans
}

/**
递归方法
1）针对某一种括号，当存在左括号时，可以追加右括号(nr++或者mr++）
2）当某一种括号右括号全部使用完时，该括号排列组合完成，当所有右括号使用完成时，生成一种组合输出
3）记录当前追加但未匹配的左括号，只能追加与最后未匹配左括号相对应的右括号。追加完右括号，则移除已匹配的左括号
*/
func doGenerateParenthesis(s string, p string, n int, m int, nl int, nr int, ml int, mr int, ans *[]string) {
	//完成组合
	if mr == m && nr == n {
		*ans = append(*ans, s)
		return
	}
	if ml < m {
		doGenerateParenthesis(s+"{", p+"{", n, m, nl, nr, ml+1, mr, ans)
	}
	if mr < ml && strings.HasSuffix(p, "{") {
		doGenerateParenthesis(s+"}", p[0:len(p)-1], n, m, nl, nr, ml, mr+1, ans)
	}
	if nl < n {
		doGenerateParenthesis(s+"(", p+"(", n, m, nl+1, nr, ml, mr, ans)
	}
	if nr < nl && strings.HasSuffix(p, "(") {
		doGenerateParenthesis(s+")", p[0:len(p)-1], n, m, nl, nr+1, ml, mr, ans)
	}
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	fmt.Println(generateParenthesis(2))
}
