//30.substring-with-concatenation-of-all-words
//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 496 👎 0

package leetcode.editor.cn


object SubstringWithConcatenationOfAllWords extends App {
  //leetcode submit region begin(Prohibit modification and deletion)

  import scala.collection.mutable._

  object Solution {
    def findSubstring(s: String, words: Array[String]): List[Int] = {
      if (words.length == 0) {
        return null
      }
      val ans = ListBuffer[Int]()
      val wordCount = words.groupBy(s => s).map(s => (s._1, s._2.length)).toSeq
      val len = words(0).length
      val count = words.length
      for (i <- 0.to(s.length - len * count)) {
        val map = Map(wordCount: _*)
        import util.control.Breaks._
        breakable {
          for (j <- 0.until(count)) {
            val word = s.substring(i + j * len, i + j * len + len)
            map.updateWith(word)(v => {
              v match {
                case None | Some(0) => break
                //切分的单词不在words中
                case Some(_) => Some(v.get - 1)
              }
            })
          }
          //循环正常结束,说明切分的单词都在words中
          ans.+=(i)
        }
      }
      ans.toList

    }


  }
  //leetcode submit region end(Prohibit modification and deletion)

  //test from here
  println(Solution.findSubstring("barfoothefoobarman", Array("foo", "bar")))
  println(Solution.findSubstring("barfoofoobarthefoobarman", Array("foo", "bar", "the")))
  println(Solution.findSubstring("wordgoodgoodgoodbestword", Array("word", "good", "best", "good")))
  //  println(Solution.countArray(Array("word", "good", "best", "good")))
}