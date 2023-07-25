//1545.find-kth-bit-in-nth-binary-string
//给你两个正整数 n 和 k，二进制字符串 Sn 的形成规则如下：
//
//
// S1 = "0"
// 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
//
//
// 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为
//0）。
//
// 例如，符合上述描述的序列的前 4 个字符串依次是：
//
//
// S1 = "0"
// S2 = "011"
// S3 = "0111001"
// S4 = "011100110110001"
//
//
// 请你返回 Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
//
//
//
// 示例 1：
//
//
//输入：n = 3, k = 1
//输出："0"
//解释：S3 为 "0111001"，其第 1 位为 "0" 。
//
//
// 示例 2：
//
//
//输入：n = 4, k = 11
//输出："1"
//解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
//
//
// 示例 3：
//
//
//输入：n = 1, k = 1
//输出："0"
//
//
// 示例 4：
//
//
//输入：n = 2, k = 3
//输出："1"
//
//
//
//
// 提示：
//
//
// 1 <= n <= 20
// 1 <= k <= 2ⁿ - 1
//
//
// Related Topics 递归 字符串 👍 57 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)

func findKthBit(n int, k int) byte {
	return findKthBit3(n, k)
}

// 递归定位K位置元素
func findKthBit1(n int, k int) byte {
	if k == 1 {
		return '0'
	}
	if k == 1<<(n-1) {
		return '1'
	} else if k < 1<<(n-1) {
		return findKthBit1(n-1, k)
	} else {
		return invert(findKthBit1(n-1, 1<<n-k))
	}
}

// 递归计算序列
func findKthBit2(n int, k int) byte {
	arr := si(n)
	return arr[k-1]
}

// 模拟计算前K个元素
func findKthBit3(n int, k int) byte {
	bs := make([]byte, k)
	bs[0] = '0'
	l := 1
	for l < k {
		p := l
		for i := 0; i <= p && p+i < k; i++ {
			if i == 0 {
				bs[p+i] = '1'
			} else {
				bs[p+i] = invert(bs[p-i])
			}
			l++
		}
	}
	return bs[k-1]
}
func invert(b byte) byte {
	return '0' + '1' - b
}

func si(n int) []byte {
	if n > 1 {
		arr := si(n - 1)
		l := len(arr)
		rev := make([]byte, l*2+1)
		rev[l] = '1'
		for i, b := range arr {
			rev[i] = b
			rev[2*l-i] = invert(b)
		}
		//(*res)[n-1] = rev
		return rev
	}
	return []byte{'0'}
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {

	//bs := make([]BS, 4)
	//fmt.Println(si(1, &bs))
	//fmt.Println(si(2, &bs))
	//fmt.Println(si(3, &bs))
	//fmt.Println(si(4, &bs))
	fmt.Println(findKthBit1(3, 5))
	fmt.Println(findKthBit2(3, 5))
	fmt.Println(findKthBit3(3, 5))

}
