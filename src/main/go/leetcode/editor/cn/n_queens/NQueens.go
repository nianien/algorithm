//51.n-queens
//按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
//
// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
//
//
// Related Topics 数组 回溯 👍 1830 👎 0

package main

// leetcode submit region begin(Prohibit modification and deletion)
import (
	"fmt"
)

func solveNQueens(n int) [][]string {
	var res [][]string
	//每行最多只有一个皇后，按行从上往下，决定每行可摆放的皇后位置，到第n-1行完成一次尝试
	//因此，我们决定初始化第0行摆放的位置，从[0,0]...[0,n-1]，只需遍历n次
	for i := 0; i < n; i++ {
		arr := create(n)
		//传递指针数组
		solveNQueens_0(0, i, 0, n, arr, &res)
	}
	return res
}

func solveNQueens_0(x int, y int, k int, n int, board [][]byte, res *[][]string) {
	//x,y位置不可放
	if x >= n || y >= n || board[x][y] != 0 {
		return
	}

	var fallback []int
	success := true
	for i := 0; i < n; i++ {
		if i != y {
			//NOTE: 指针传递
			success = success && mark(board, n, x, i, &fallback)
		}
		if i != x {
			success = success && mark(board, n, i, y, &fallback)
		}
		if i > 0 {
			if x+i < n && y+i < n {
				success = success && mark(board, n, x+i, y+i, &fallback)
			}
			if x-i >= 0 && y-i >= 0 {
				success = success && mark(board, n, x-i, y-i, &fallback)
			}
			if x-i >= 0 && y+i < n {
				success = success && mark(board, n, x-i, y+i, &fallback)
			}
			if x+i < n && y-i >= 0 {
				success = success && mark(board, n, x+i, y-i, &fallback)
			}
		}
		if !success {
			break
		}
	}

	if success {
		board[x][y] = 'Q'
		if k == n-1 {
			*res = append(*res, flat(board))
			//back-track
			board[x][y] = 0
			return
		}

		//递归寻找下一行的摆放位置
		for i := 0; i < n; i++ {
			solveNQueens_0(x+1, i, k+1, n, board, res)
		}
		//back-track
		board[x][y] = 0
	}
	//back-track
	for _, p := range fallback {
		board[p/n][p%n] = 0
	}
}

// 创建指定二维数组
func create(n int) [][]byte {
	arr := make([][]byte, n)
	for k := range arr {
		arr[k] = make([]byte, n)
	}
	return arr
}

// 打平二维数组
func flat(arr [][]byte) []string {
	res := make([]string, 0)
	for _, s := range arr {
		res = append(res, string(s))
	}
	return res
}

// 标识该位置不能放置皇后，如果标记成功返回true，否则返回false
func mark(res [][]byte, n int, i int, j int, l *[]int) bool {
	if res[i][j] == 'Q' {
		return false
	}
	if res[i][j] == 0 {
		res[i][j] = '.'
		*l = append(*l, i*n+j)
	}
	return true
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(solveNQueens(5))
}
