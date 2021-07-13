//60.permutation-sequence
//给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
//
//
// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
//
//
// 给定 n 和 k，返回第 k 个排列。
//
//
//
// 示例 1：
//
//
//输入：n = 3, k = 3
//输出："213"
//
//
// 示例 2：
//
//
//输入：n = 4, k = 9
//输出："2314"
//
//
// 示例 3：
//
//
//输入：n = 3, k = 1
//输出："123"
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
// 1 <= k <= n!
//
// Related Topics 递归 数学
// 👍 541 👎 0

package main

import (
	"fmt"
	"strconv"
)

//leetcode submit region begin(Prohibit modification and deletion)
func getPermutation(n int, k int) string {
	var temp = make([]int, n)
	for i := 0; i < n; i++ {
		temp[i] = i + 1
	}
	var count int
	return getPermutation_(temp, k, make([]int, len(temp)), 0, &count)
}

//这里比较困难的是如何中断递归获取中间结果
//解决方案有两种:
//1. 指针参数保存变化的结果, 满足条件时中断, 通过指针获取中间结果
//2. 满足条件时返回特定结果, 在每次递归返回时判断是否满足条件
//3. 对于需要根据返回值改变入参的情况,只能通过指针解决,无法通过返回值更新
// arr: 原始数组
// check: 校验数组,存在当前排列原数组元素在排列数组中的位置(因为数组初始化为0,这里索引起始位置为1,用于判断索引是否被占用)
// index: 当前待排列元素索引位置,当index=n时,表示原数组元素全部参与排列,生成一种排列
func getPermutation_(arr []int, k int, check []int, index int, count *int) string {
	if len(arr) == index {
		*count += 1
		if *count == k {
			//根据校验数组返回当前排列
			return trans(check, arr)
		}
		return ""
	}
	for i := range arr {
		if check[i] > 0 {
			continue
		}
		//这里表示arr[i]在新数组的索引位置为index+1
		check[i] = index + 1
		var ans = getPermutation_(arr, k, check, index+1, count)
		//回溯,重置i位置元素
		check[i] = 0
		if ans != "" {
			return ans
		}
	}
	return ""
}

//check: 校验数组
// origin: 原始数组
func trans(check []int, origin []int) string {
	var ans = make([]int, len(origin))
	//还原数组元素
	for i, e := range check {
		//v表示在arr[i]在新数组的索引+1
		//因此新数组索引位置v-1对应的元素就是arr[i]
		ans[e-1] = origin[i]
	}
	var s = ""
	for _, i := range origin {
		s += strconv.Itoa(i)
	}
	return s
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	fmt.Println(getPermutation(3, 5))
	//fmt.Println(getPermutation(4, 9))

}
