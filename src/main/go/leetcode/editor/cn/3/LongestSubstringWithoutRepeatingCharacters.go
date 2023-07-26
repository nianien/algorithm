//3.longest-substring-without-repeating-characters
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 5616 👎 0

package main

import "fmt"

// leetcode submit region begin(Prohibit modification and deletion)
func lengthOfLongestSubstring(s string) int {
	var idxMap = map[int32]int{}
	//-1为了处理为空的情况，索引区间+1刚好为0
	var begin, end, min, max = 0, -1, 0, -1
	//当遇到重复字符，从重复字符的下一个位置开始计算，这样可以排除之前重复的字符串。
	//如abcded, 当d重复时，begin可以直接从e开始计算，避免不必要的迭代。但需要注意的是，begin是递增的，不能往前回溯。
	//例如abcba, 当a重复时，begin(=c）已经越过第一个a，此时不能再回溯至第一个b的位置。
	//也就是说，只有在begin之后发生重复，才算真正的重复，这时才能更新begin的值
	for i, c := range s {
		var idx, ok = idxMap[c]
		if ok {
			//重复发生在begin之后才算重复
			if idx >= begin {
				//更新重复发生时的最大长度
				if max-min < end-begin {
					min = begin
					max = end
				}
				begin = idx + 1
			}
		}
		idxMap[c] = i
		//end不断后移
		end = i
	}
	if max-min < end-begin {
		min = begin
		max = end
	}
	fmt.Println(s[min : max+1])
	return max - min + 1

}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(lengthOfLongestSubstring("abcabcbb"))
	fmt.Println(lengthOfLongestSubstring("aabdbacddeedd"))
	fmt.Println(lengthOfLongestSubstring("aa"))
	fmt.Println(lengthOfLongestSubstring("aabbcc"))
	fmt.Println(lengthOfLongestSubstring(""))
}
