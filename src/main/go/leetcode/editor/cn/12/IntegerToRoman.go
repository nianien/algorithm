//12.integer-to-roman
//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
//
//
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
//
//
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
//
//
// 给你一个整数，将其转为罗马数字。
//
//
//
// 示例 1:
//
//
//输入: num = 3
//输出: "III"
//
// 示例 2:
//
//
//输入: num = 4
//输出: "IV"
//
// 示例 3:
//
//
//输入: num = 9
//输出: "IX"
//
// 示例 4:
//
//
//输入: num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
//
//
// 示例 5:
//
//
//输入: num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
//
//
//
// 提示：
//
//
// 1 <= num <= 3999
//
// Related Topics 数学 字符串
// 👍 638 👎 0

package main

import (
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)

// 把每个位数的所有可能枚举出来，然后直接输出每位的表示
var (
	thousands = []string{"", "M", "MM", "MMM"}
	hundreds  = []string{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}
	tens      = []string{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}
	ones      = []string{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}
)

func intToRoman(num int) string {
	return thousands[num/1000] + hundreds[num%1000/100] + tens[num%100/10] + ones[num%10]
}

func intToRoman2(num int) string {

	//struct literal
	var keys = []struct {
		k int
		v string
	}{
		{1000, "M"},
		{900, "CM"},
		{500, "D"},
		{400, "CD"},
		{100, "C"},
		{90, "XC"},
		{50, "L"},
		{40, "XL"},
		{10, "X"},
		{9, "IX"},
		{5, "V"},
		{4, "IV"},
		{1, "I"},
	}
	/*
			var keymap = map[int]string{
			1:    "I",
			4:    "IV",
			5:    "V",
			9:    "IX",
			10:   "X",
			40:   "XL",
			50:   "L",
			90:   "XC",
			100:  "C",
			400:  "CD",
			500:  "D",
			900:  "CM",
			1000: "M",
		}
		var keys []int
		for key := range keymap {
			keys = append(keys, key)
		}
		//按照从大到小排序
		sort.Slice(keys, func(i, j int) bool {
			return keys[i] > keys[j]
		})*/
	var roman []byte
	//从大到小判断
	for _, key := range keys {
		for num >= key.k {
			num -= key.k
			roman = append(roman, key.v...)
		}
	}
	return string(roman)
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(intToRoman2(1994))
}
