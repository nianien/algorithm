// 28.find-the-index-of-the-first-occurrence-in-a-string
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
// 如果 needle 不是 haystack 的一部分，则返回 -1 。
//
// 示例 1：
//
// 输入：haystack = "sadbutsad", needle = "sad"
// 输出：0
// 解释："sad" 在下标 0 和 6 处匹配。
// 第一个匹配项的下标是 0 ，所以返回 0 。
//
// 示例 2：
//
// 输入：haystack = "leetcode", needle = "leeto"
// 输出：-1
// 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
//
// 提示：
//
// 1 <= haystack.length, needle.length <= 10⁴
// haystack 和 needle 仅由小写英文字符组成
//
// Related Topics 双指针 字符串 字符串匹配 👍 1914 👎 0
package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)

func strStr(haystack string, needle string) int {
	return sunday(haystack, needle)
}

func calShift(str string) map[string]int {
	var shifts = map[string]int{}
	for i, s := range str {
		shifts[string(s)] = len(str) - i
	}
	return shifts
}

func sunday(haystack string, needle string) int {
	var n = len(needle)
	var l = len(haystack)
	if n > l {
		return -1
	}
	if needle == "" {
		return 0
	}
	var shifts = calShift(needle)
	var idx = 0
	for idx+n <= l {
		//待匹配字符串
		var strCut = haystack[idx : idx+n]
		// 如果匹配,直接返回索引位置
		if strCut == needle {
			return idx
		} else {
			//边界溢出,剩余长度不够匹配
			if idx+n >= l {
				return -1
			}
			//计算当前匹配长度的下一个字符偏移位置
			var curC = haystack[idx+n]
			var a, ok = shifts[string(curC)]
			// 字符在模式串中,使用该字符对应的偏移位置
			if ok {
				idx += a
			} else {
				//字符不在模式串中,直接跳过整个模式串
				idx += n + 1
			}
		}
	}
	return -1
}

/*
*
KMP算法求Next数组, 数组下标从1开始, next[0]=-1
*/
func getNext(str string) []int {
	var n = len(str)
	var next = make([]int, n)
	next[0] = -1
	var i, j = 0, -1
	for i < n-1 {
		if j == -1 || str[i] == str[j] {
			i++
			j++
			if str[i] != str[j] {
				next[i] = j
			} else {
				//因为不能出现p[j] = p[next[j]]，需要继续递归
				next[i] = next[j]
			}
		} else {
			j = next[j]
		}
	}
	return next
}

func kmp(haystack string, needle string) int {
	var n = len(needle)
	var l = len(haystack)
	if n > l {
		return -1
	}
	if needle == "" {
		return 0
	}
	var next = getNext(needle)
	//i对应是主串的索引位置, j对应是模式串的位置
	var i, j = 0, 0
	for i < l && j < n {
		//fmt.Println("i=",i,",j=",j)
		//①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
		if j == -1 || haystack[i] == needle[j] {
			i++
			j++
		} else {
			//②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
			//next[j]即为j所对应的next值
			j = next[j]
		}
	}
	if j == n {
		return i - j
	}
	return -1
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(strStr("hello", "ell"))
	fmt.Println(kmp("hello", "ell"))
}
