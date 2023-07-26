//69.sqrtx
//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
// 示例 1:
//
// 输入: 4
//输出: 2
//
//
// 示例 2:
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
//
// Related Topics 数学 二分查找
// 👍 692 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)
func mySqrt(x int) int {
	if x <= 1 {
		return x
	}
	if x <= 1 {
		return x
	}
	// f(x)=x^2-a
	// 导数为2x, 在坐标(x,f(x))处的切线斜率为2x,则切线与x的交点,记为r=x-f(x)/2x, 该值比x更接近a的平方根
	// 代入f(x)得r=x-(x^2-a)/2x=(x+a/x)/2, 然后令x=r继续迭代,不断逼近a的平方根
	// 注意这里是关于r的函数:f(r)=r^2-x, 因此√x=(r+x/r)/2
	var r = x
	for r*r > x {
		r = (r + x/r) / 2
	}
	return r

}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {

	fmt.Println(mySqrt(15))
}
