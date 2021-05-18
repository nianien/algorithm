package leetcode.editor.cn

object ReverseInteger extends App {
  
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
