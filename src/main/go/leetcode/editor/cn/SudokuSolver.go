//37.sudoku-solver
//编写一个程序,通过填充空格来解决数独问题。
//
// 数独的解法需 遵循如下规则：
//
//
// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
//
//
// 数独部分空格内已填入了数字,空白格用 '.' 表示。
//
//
//
//
//
//
// 示例：
//
//
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示,唯一有效的解决方案如下所示：
//
//
//
//
//
//
// 提示：
//
//
// board.length == 9
// board[i].length == 9
// board[i][j] 是一位数字或者 '.'
// 题目数据 保证 输入数独仅有一个解
//
//
//
//
// Related Topics 数组 回溯 矩阵
// 👍 876 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)

func solveSudoku(board [][]byte) {
	//统计每行/列/块已经使用的数字,使用长度为10的二进制存储
	var rows, cols, zones = make([]int, 9), make([]int, 9), make([]int, 9)
	for i := range board {
		for j := range board[i] {
			if board[i][j] != '.' {
				//第board[i][j]位置位1
				var bit = 1 << (board[i][j] - '0')
				//board[i][j]位置的被占用,即行和列第board[i][j]位为1
				rows[i] |= bit
				cols[j] |= bit
				zones[i/3*3+j/3] |= bit
			}
		}
	}
	solveSudoku_(board, 0, rows, cols, zones)
}

//将二维数组按行打平为一维数组, 按一维索引位置递归填充数字, 这里之所以打平为一维数组,避免递归时重复检查之前的元素
//n: 按行打平为一维数组时的索引位置
//rows: 遍历第n个位置时,各行已使用的数字集合
//cols: 遍历第n个位置时,各列已使用的数字集合
//zones: 遍历第n个位置时,各列已使用的数字集合
func solveSudoku_(board [][]byte, n int, rows []int, cols []int, zones []int) bool {
	//第n个位置填充完成,递归结束
	if n == len(board)*len(board) {
		return true
	}
	//定位行与列
	var i, j = n / len(board), n % len(board)
	//当前位置已填数字,直接判断下一个位置
	if board[i][j] != '.' {
		return solveSudoku_(board, n+1, rows, cols, zones)
	}
	//当前位置未填充数字,则尝试填充数字1~9
	for k := 1; k <= 9; k++ {
		var bit = 1 << k
		//判断所在行/列/块是否使用该数字
		if unused := rows[i]&bit == 0 && cols[j]&bit == 0 && zones[i/3*3+j/3]&bit == 0; unused {
			//对应行/列/块标记该数字被占用
			rows[i] |= bit
			cols[j] |= bit
			zones[i/3*3+j/3] |= bit
			//继续填充下一个位置
			if solveSudoku_(board, n+1, rows, cols, zones) {
				//这里递归返回true,表示后续位置都已经正确填充,此时可以填充当前位置
				board[i][j] = uint8(k + '0')
				return true
			} else {
				//这里递归返回false,表示后续位置填充失败,因此当前数字无效,我们需要尝试下一个数字
				//在尝试下一个数字前,我们要移除对应行/列/块所标记的当前数字
				rows[i] &= ^bit
				cols[j] &= ^bit
				zones[i/3*3+j/3] &= ^bit
			}
		}
	}
	//此处表示该位置1~9均不可填充, 数独无解
	return false
}

//type Sudoku struct {
//    /**
//      横坐标
//    */
//    X int
//    /**
//      纵坐标
//    */
//    Y int
//    /**
//      区域
//    */
//    Z int
//    /**
//      候选集合
//    */
//
//    Nums []byte
//}
//func solveSudoku(board [][]byte) {
//    var sudokus []*Sudoku
//    for i := range board {
//       for j := range board[i] {
//           if board[i][j] == '.' {
//               sudokus = append(sudokus, findUnSolved(board, i, j))
//           }
//       }
//    }
//    var rows = prepare(9)
//    var cols = prepare(9)
//    var regions = prepare(9)
//    fill(sudokus, 0, 0, rows, cols, regions)
//    for _, r := range rows {
//       for n, s := range r {
//           board[s.X][s.Y] = n
//       }
//    }
//}
//
///**
//创建检查数组
//*/
//func prepare(size int) []map[byte]*Sudoku {
//    var arr = make([]map[byte]*Sudoku, 9)
//    for i := 0; i < size; i++ {
//        arr[i] = map[byte]*Sudoku{}
//    }
//    return arr
//}
//
///**
//找出未填数字的位置以及候选数字
//*/
//func findUnSolved(board [][]byte, r int, c int) *Sudoku {
//    //默认可填1~9
//    var m = map[byte]int{'1': 0, '2': 0, '3': 0, '4': 0, '5': 0, '6': 0, '7': 0, '8': 0, '9': 0}
//    //排除同行已填数字
//    for j := 0; j < len(board[r]); j++ {
//        delete(m, board[r][j])
//    }
//    //排除同列已填数字
//    for i := 0; i < len(board); i++ {
//        delete(m, board[i][c])
//    }
//    //排除同区域
//    for i := r / 3 * 3; i < r/3*3+3; i++ {
//        for j := c / 3 * 3; j < c/3*3+3; j++ {
//            delete(m, board[i][j])
//        }
//    }
//    var arr = make([]byte, len(m))
//    var i = 0
//    for k := range m {
//        arr[i] = k
//        i += 1
//    }
//    return &Sudoku{
//        X:    r,
//        Y:    c,
//        Z:    r/3*3 + c/3,
//        Nums: arr,
//    }
//}
//
///**
//填充数独
//i: 检查第i个数独
//j: 检查第j个后续数字
//*/
//func fill(board []*Sudoku, i int, j int, rows []map[byte]*Sudoku, cols []map[byte]*Sudoku, zones []map[byte]*Sudoku) bool {
//    if i == len(board) {
//        return true
//    }
//    if j == len(board[i].Nums) {
//        return false
//    }
//    var sudoku = board[i]
//    var e = sudoku.Nums[j]
//    if _, ok := rows[sudoku.X][e]; ok {
//        return fill(board, i, j+1, rows, cols, zones)
//    }
//    if _, ok := cols[sudoku.Y][e]; ok {
//        return fill(board, i, j+1, rows, cols, zones)
//    }
//    if _, ok := zones[sudoku.Z][e]; ok {
//        return fill(board, i, j+1, rows, cols, zones)
//    }
//    //尝试填充第i个位置的第j个候选元素
//    rows[sudoku.X][e] = sudoku
//    cols[sudoku.Y][e] = sudoku
//    zones[sudoku.Z][e] = sudoku
//    //继续尝试填充下一个位置
//    if fill(board, i+1, 0, rows, cols, zones) {
//        return true
//    } else {
//        //下一个位置填充失败, 回溯当前位置的下一个候选集合
//        delete(rows[sudoku.X], e)
//        delete(cols[sudoku.Y], e)
//        delete(zones[sudoku.Z], e)
//        return fill(board, i, j+1, rows, cols, zones)
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)

//test from here

func main() {
	var print = func(board [][]byte) {
		for _, a := range board {
			for _, b := range a {
				fmt.Print(string(b), "   ")
			}
			fmt.Println()
		}
	}
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
	solveSudoku(sd)
	print(sd)

}
