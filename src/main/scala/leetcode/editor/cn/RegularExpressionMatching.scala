package leetcode.editor.cn


object RegularExpressionMatching extends App {

  //leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def isMatch(s: String, p: String): Boolean = {
      val m = s.length
      val n = p.length
      //表示s的前 i 个字符与 p 中的前 j 个字符是否能够匹配。
      val f = Array.ofDim[Boolean](m + 1, n + 1)
      //两个空字符串是可以匹配的
      f(0)(0) = true
      for (i <- 0 to m) {
        for (j <- 1 to n) {
          f(i)(j) = p.charAt(j - 1) match {
            case '*' =>
              j match {
                case 1 => false
                case _ => if (matches(s, p, i, j - 1)) f(i)(j - 2) || f(i - 1)(j) else f(i)(j - 2)
              }
            case _ => matches(s, p, i, j) && f(i - 1)(j - 1)
          }
        }
      }
      f(m)(n)

    }

    /**
     * 表示s的第 i 个字符与 p 中的第 j 个字符是否能够匹配,只有当 y 是 . 或者 x 和 y 本身相同时，这两个字符才会匹配。
     * 注意: 这里的下标从1开始
     *
     * @param s
     * @param p
     * @param i
     * @param j
     * @return
     */
    private def matches(s: String, p: String, i: Int, j: Int): Boolean = {
      if (i == 0) return false
      if (p.charAt(j - 1) == '.') return true
      s.charAt(i - 1) == p.charAt(j - 1)
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)
  //entry from here:
  println(Solution.isMatch("mississippi", "mis*is*p*."))
  println(Solution.isMatch("aab", "*a*b"))
  println(Solution.isMatch("aab", "c*a*b"))


}
