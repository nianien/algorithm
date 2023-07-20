//79.word-search
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"SEE"
//输出：true
//
//
// 示例 3：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCB"
//输出：false
//
//
//
//
// 提示：
//
//
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
//
// Related Topics 数组 回溯 矩阵 👍 1626 👎 0

package main

import "fmt"

// leetcode submit region begin(Prohibit modification and deletion)

func exist(board [][]byte, word string) bool {
	m, n, l := len(board), len(board[0]), len(word)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			flag := make([]bool, m*n)
			if exist1(board, []byte(word), m, n, l, flag, i, j, 0) {
				return true
			}
		}
	}
	return false
}

func exist1(board [][]byte, words []byte, m int, n int, l int, flag []bool, i int, j int, k int) bool {
	//fmt.Println("x=", i, "y=", j, string(board[i][j]), string(words[k]), board[i][j] == words[k])
	if board[i][j] != words[k] {
		return false
	}
	flag[i*n+j] = true
	if k == l-1 {
		return true
	}
	if j < n-1 && !flag[i*n+j+1] && exist1(board, words, m, n, l, flag, i, j+1, k+1) {
		return true
	}
	if i < m-1 && !flag[(i+1)*n+j] && exist1(board, words, m, n, l, flag, i+1, j, k+1) {
		return true
	}
	if j > 0 && !flag[i*n+j-1] && exist1(board, words, m, n, l, flag, i, j-1, k+1) {
		return true
	}
	if i > 0 && !flag[(i-1)*n+j] && exist1(board, words, m, n, l, flag, i-1, j, k+1) {
		return true
	}
	flag[i*n+j] = false
	return false

}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	//fmt.Println(exist([][]byte{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB"))
	//fmt.Println(exist([][]byte{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCESEEEFS"))
	//fmt.Println(exist([][]byte{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCEFSADEESE"))
	fmt.Println(exist([][]byte{{'a', 'z'}}, "ba"))
	fmt.Println('z' - 'A')
	fmt.Println('a' - 'A')
}
