//10.regular-expression-matching
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 字符串 动态规划 回溯算法 
// 👍 2179 👎 0

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
