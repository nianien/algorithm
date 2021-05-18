package leetcode.editor.cn

/**
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
object ValidParentheses extends App {
  //leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def isValid(s: String): Boolean = {
      val stack = scala.collection.mutable.Stack[Char]()
      val map = Map('}' -> '{', ')' -> '(', ']' -> '[')
      s.foreach(
        c => c match {
          case '[' | '(' | '{' => stack.push(c)
          case ')' | ']' | '}' => if (stack.nonEmpty && map.get(c).contains(stack.top)) stack.pop else return false
        })
      stack.isEmpty
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

  //test from here:
  println(Solution.isValid("([)]"))
  println(Solution.isValid("{[]}"))

}
