//7.reverse-integer
//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2863 👎 0

package leetcode.editor.cn

object ReverseInteger extends App{
  //leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def reverse(x: Int): Int = {
      val flag = if (x > 0) 1 else -1
      var (n, ret) = (x * flag, 0)
      while (n > 0) {
        if (ret > Int.MaxValue / 10 || ret == Int.MaxValue / 10 && n % 10 > Int.MaxValue % 10) {
          return 0
        }
        ret = ret * 10 + (n % 10)
        n = n / 10
      }
      ret * flag
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

  //test from here:
  println(Solution.reverse(-123))
  println(Solution.reverse(1534236469))
}