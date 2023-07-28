//48.rotate-image
//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
//
//
// 示例 2：
//
//
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
//
//
// 示例 3：
//
//
//输入：matrix = [[1]]
//输出：[[1]]
//
//
// 示例 4：
//
//
//输入：matrix = [[1,2],[3,4]]
//输出：[[3,1],[4,2]]
//
//
//
//
// 提示：
//
//
// matrix.length == n
// matrix[i].length == n
// 1 <= n <= 20
// -1000 <= matrix[i][j] <= 1000
//
// Related Topics 数组 数学 矩阵
// 👍 926 👎 0

package main

import (
	"fmt"
	"strconv"
)

//leetcode submit region begin(Prohibit modification and deletion)

func rotate(matrix [][]int) {
	var n = len(matrix)
	var p1, p2 = 0, n - 1
	for p1 < p2 {
		rotateBySwap(matrix, p1, p2)
		p1 += 1
		p2 -= 1
	}
}

func rotateBySwap(matrix [][]int, a int, b int) {
	//将二维数组看成嵌套矩形，我们对每层矩形的边进行旋转, 即可完成数组的旋转
	//在进行每层边旋转时，我们可以每次选择四条边上对应的位置同时进行顺时针置换
	//我们将矩形的四条边定义为：上下左右,则初始置换位置为矩形顶点坐标:(x1,y1),(x2,y1),(x2,y2),(x2,y1)
	//每次置换完成后，上条边：y坐标右移一位，右条边：x坐标下移一位, 下条边：y坐标左移一位，左条边：x坐标上移一位
	//然后继续顺时针置换，直到所有位置完成置换
	type Point struct {
		X int
		Y int
	}
	var p1, p2, p3, p4 = &Point{
		X: a, Y: a,
	}, &Point{
		X: a, Y: b,
	}, &Point{
		X: b, Y: b,
	}, &Point{
		X: b, Y: a,
	}
	for i := a; i < b; i++ {
		//fmt.Println(p1, p2, p3, p4)
		//顺时针交换元素,p1,p2,p3,p4=p4,p1,p2,p3
		matrix[p1.X][p1.Y], matrix[p2.X][p2.Y], matrix[p3.X][p3.Y], matrix[p4.X][p4.Y] =
			matrix[p4.X][p4.Y], matrix[p1.X][p1.Y], matrix[p2.X][p2.Y], matrix[p3.X][p3.Y]
		p1.Y++
		p2.X++
		p3.Y--
		p4.X--
	}
}

func rotate_(matrix [][]int, a, b int) {
	//将二维数组看成嵌套矩形，如果我们能对每层矩形进行旋转后即可完成数组的旋转
	//针对(a,a)->(b,b)坐标形成的矩形，我们按照顺时针访问其边，在对应位置放入合适的元素
	//那么待放入的元素如何计算呢？ 这里我们使用队列保存待放入元素，队首元素放入当前位置，而当前位置的原数据放入队尾
	//其原理是这样的，旋转90度，也就是将每条边的元素依次放入顺时针下一条边相应位置，先入先出，刚好适合队列存储
	//这里需要注意的时，两条边的交点重复，只需要访问一遍，否则顶点位置的元素会被错误覆盖
	//当访问完四条边，然后递归访问内层矩形
	var temp = append([]int{}, matrix[a][a:b]...)
	//right, y=b
	for i := a; i <= b; i++ {
		temp = append(temp, matrix[i][b])
		matrix[i][b] = temp[0]
		temp = temp[1:]
	}

	//bottom, x=b
	for i := b - 1; i >= a; i-- {
		temp = append(temp, matrix[b][i])
		matrix[b][i] = temp[0]
		temp = temp[1:]
	}
	//left, y=a
	for i := b - 1; i >= a; i-- {
		temp = append(temp, matrix[i][a])
		matrix[i][a] = temp[0]
		temp = temp[1:]
	}
	//top, x=a
	for i := a + 1; i <= b; i++ {
		temp = append(temp, matrix[a][i])
		matrix[a][i] = temp[0]
		temp = temp[1:]
	}

}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	var print = func(arr [][]int) {
		fmt.Println("====")
		for _, a := range arr {
			var s = ""
			for _, c := range a {
				s += strconv.Itoa(c) + "\t"
			}
			fmt.Println(s)
		}
	}
	//var arr = [][]int{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}
	var matrix = [][]int{
		{1, 2, 3, 4, 5, 6},
		{7, 8, 9, 10, 11, 12},
		{13, 14, 15, 16, 17, 18},
		{19, 20, 21, 22, 23, 24},
		{25, 26, 27, 28, 29, 30},
		{31, 32, 33, 34, 35, 36}}

	print(matrix)
	rotate(matrix)
	print(matrix)

}
