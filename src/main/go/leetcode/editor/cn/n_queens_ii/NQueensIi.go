//52.n-queens-ii
//n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
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
// Related Topics 回溯 👍 451 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)
func totalNQueens(n int) int {
	var res int
	for i := 0; i < n; i++ {
		arr := make([]byte, n*n)
		//传递指针数组
		totalNQueens_0(0, i, 0, n, arr, &res)
	}
	return res
}

func totalNQueens_0(x int, y int, k int, n int, board []byte, res *int) {
	//x,y位置不可放
	if x >= n || y >= n || board[x*n+y] != 0 {
		return
	}

	var fallback []int
	success := true
	for i := 0; i < n; i++ {
		if i != y {
			//NOTE: 指针传递
			success = success && check(board, n, x, i, &fallback)
		}
		if i != x {
			success = success && check(board, n, i, y, &fallback)
		}
		if i > 0 {
			if x+i < n && y+i < n {
				success = success && check(board, n, x+i, y+i, &fallback)
			}
			if x-i >= 0 && y-i >= 0 {
				success = success && check(board, n, x-i, y-i, &fallback)
			}
			if x-i >= 0 && y+i < n {
				success = success && check(board, n, x-i, y+i, &fallback)
			}
			if x+i < n && y-i >= 0 {
				success = success && check(board, n, x+i, y-i, &fallback)
			}
		}
		if !success {
			break
		}
	}

	if success {
		board[x*n+y] = 'Q'
		if k == n-1 {
			*res++
			//back-track
			board[x*n+y] = 0
			return
		}
		//递归寻找下一行的摆放位置
		for i := 0; i < n; i++ {
			totalNQueens_0(x+1, i, k+1, n, board, res)
		}
		board[x*n+y] = 0
	}
	//back-track
	for _, p := range fallback {
		board[p] = 0
	}
}

func check(res []byte, n int, i int, j int, l *[]int) bool {
	p := i*n + j
	if res[p] == 'Q' {
		return false
	}
	if res[p] == 0 {
		res[p] = '.'
		*l = append(*l, i*n+j)
	}
	return true
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(totalNQueens(5))
}
