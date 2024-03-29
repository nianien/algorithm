//36.valid-sudoku
//请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
//
//
// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
//
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。
//
// 注意：
//
//
// 一个有效的数独（部分已被填充）不一定是可解的。
// 只需要根据以上规则，验证已经填入的数字是否有效即可。
//
//
//
//
// 示例 1：
//
//
//输入：board =
//[['5','3','.','.','7','.','.','.','.']
//,['6','.','.','1','9','5','.','.','.']
//,['.','9','8','.','.','.','.','6','.']
//,['8','.','.','.','6','.','.','.','3']
//,['4','.','.','8','.','3','.','.','1']
//,['7','.','.','.','2','.','.','.','6']
//,['.','6','.','.','.','.','2','8','.']
//,['.','.','.','4','1','9','.','.','5']
//,['.','.','.','.','8','.','.','7','9']]
//输出：true
//
//
// 示例 2：
//
//
//输入：board =
//[['8','3','.','.','7','.','.','.','.']
//,['6','.','.','1','9','5','.','.','.']
//,['.','9','8','.','.','.','.','6','.']
//,['8','.','.','.','6','.','.','.','3']
//,['4','.','.','8','.','3','.','.','1']
//,['7','.','.','.','2','.','.','.','6']
//,['.','6','.','.','.','.','2','8','.']
//,['.','.','.','4','1','9','.','.','5']
//,['.','.','.','.','8','.','.','7','9']]
//输出：false
//解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无
//效的。
//
//
//
// 提示：
//
//
// board.length == 9
// board[i].length == 9
// board[i][j] 是一位数字或者 '.'
//
// Related Topics 数组 哈希表 矩阵
// 👍 542 👎 0

package main

import "fmt"

// leetcode submit region begin(Prohibit modification and deletion)
func isValidSudoku(board [][]byte) bool {
	var m = map[string]*Position{}
	for i := range board {
		for j := range board[i] {
			if c := string(board[i][j]); c != "." {
				var v, ok = m[c]
				if !ok {
					/**
					  这里指针引用,保证属性变化可见
					*/
					v = &Position{
						Xs: [9]bool{},
						Ys: [9]bool{},
						Rs: [9]bool{},
					}
					m[c] = v
				}
				if !v.Check(i, j) {
					return false
				}
			}
		}
	}
	return true
}

type Position struct {
	/**
	  横坐标集合
	*/
	Xs [9]bool
	/**
	  纵坐标集合
	*/
	Ys [9]bool
	/**
	  区域集合
	*/
	Rs [9]bool
}

/*
*
检查数组是否存在K, 0<=k<=9, 如果不存在返回true,否则返回false
*/
func putIfAbsent(arr *[9]bool, k int) bool {
	if arr[k] {
		return false
	} else {
		arr[k] = true
		return true
	}

}

// Check /**
/**
判断x,y坐标以及所在区域块是否重复
*/
func (p *Position) Check(x int, y int) bool {
	return putIfAbsent(&p.Xs, x) && putIfAbsent(&p.Ys, y) && putIfAbsent(&p.Rs, x/3*3+y/3)
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	var sd = [][]byte{
		{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
		{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
		{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
		{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
		{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
		{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
		{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
		{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
		{'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
	fmt.Println(isValidSudoku(sd))
	var sd2 = [][]byte{
		{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
		{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
		{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
		{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
		{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
		{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
		{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
		{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
		{'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
	fmt.Println(isValidSudoku(sd2))

}
