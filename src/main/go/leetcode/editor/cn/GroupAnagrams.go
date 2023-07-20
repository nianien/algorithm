//49.group-anagrams
//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
//
//
//
// 示例 1:
//
//
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2:
//
//
//输入: strs = [""]
//输出: [[""]]
//
//
// 示例 3:
//
//
//输入: strs = ["a"]
//输出: [["a"]]
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 10⁴
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
//
// Related Topics 数组 哈希表 字符串 排序 👍 1525 👎 0

package main

import (
	"fmt"
	"sort"
	"strconv"
	"strings"
)

// leetcode submit region begin(Prohibit modification and deletion)
func groupAnagrams(strs []string) [][]string {
	mp := map[string][]string{}
	for _, str := range strs {
		key := std2(str)
		mp[key] = append(mp[key], str)
	}
	//make(Type, len, cap)
	ans := make([][]string, 0, len(mp))
	for _, v := range mp {
		ans = append(ans, v)
	}
	return ans

}

func std(str string) string {
	s := []byte(str)
	sort.Slice(s, func(i, j int) bool { return s[i] < s[j] })
	return string(s)
}

func std2(str string) string {
	cnt := [26]int{}
	for _, b := range str {
		cnt[b-'a']++
	}
	build := strings.Builder{}
	for i, i2 := range cnt {
		if i2 > 0 {
			build.WriteString(string(i + 'a'))
			if i2 > 1 {
				build.WriteString(strconv.Itoa(i2))
			}
		}
	}
	return build.String()
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {

	//fmt.Println(groupAnagrams([]string{"eat", "tea", "tan", "ate", "nat", "bat"}))
	fmt.Println(std("eatttttttt"))
	fmt.Println(std2("eatttttttt"))
}
