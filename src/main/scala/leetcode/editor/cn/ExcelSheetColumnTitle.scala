//168.excel-sheet-column-title
//给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。 
//
// 例如： 
//
// 
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28 
//...
// 
//
// 
//
// 示例 1： 
//
// 
//输入：columnNumber = 1
//输出："A"
// 
//
// 示例 2： 
//
// 
//输入：columnNumber = 28
//输出："AB"
// 
//
// 示例 3： 
//
// 
//输入：columnNumber = 701
//输出："ZY"
// 
//
// 示例 4： 
//
// 
//输入：columnNumber = 2147483647
//输出："FXSHRXW"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= columnNumber <= 231 - 1 
// 
// Related Topics 数学 字符串 
// 👍 407 👎 0

package leetcode.editor.cn

object ExcelSheetColumnTitle extends App {
  //leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    /**
     *
     *
     * @param columnNumber
     * @return
     */
    def convertToTitle(columnNumber: Int): String = {
      var ans = ""
      var div = columnNumber
      while (div > 0) {
        //相当于没有0的26进制,高位减1作为低位的26(Z)
        div-=1
        ans = ('A'  + div % 26).toChar + ans
        div /= 26
      }
      ans
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

  //FXSHRXW
  println(Solution.convertToTitle(26))
  println(Solution.convertToTitle(2147483647))
  println(Solution.convertToTitle(701))
  //test from here
}