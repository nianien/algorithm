//54.spiral-matrix
//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 
// 👍 802 👎 0

package leetcode.editor.cn


object SpiralMatrix extends App {
  //leetcode submit region begin(Prohibit modification and deletion)

  import scala.collection.mutable.ListBuffer

  object Solution {
    def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
      spiralOrder0(matrix, (0, 0), (matrix.length - 1, matrix(0).length - 1)).toList
    }

    def spiralOrder0(matrix: Array[Array[Int]], p1: (Int, Int), p2: (Int, Int)): ListBuffer[Int] = {
      val list = ListBuffer[Int]()
      var (x1, y1) = (p1._1, p1._2)
      var (x2, y2) = (p2._1, p2._2)
      //一次循环,(x1,y1)->(x1,y2)->(x2,y2)->(x2,y1)->(x1,y1)
      while (x1 <= x2 && y1 <= y2) {
        //println(s"($x1,$y1),($x2,$y2)")
        //(x1,y1)->(x1,y2)
        y1.to(y2).foreach(i => list += matrix(x1)(i))
        //(x1+1,y2)->(x2,y2)
        (x1 + 1).to(x2).foreach(i => list += matrix(i)(y2))
        //(x2,y2-1)->(x2,y1)
        (y2 - 1).to(y1, -1).filter(_ => x1 < x2).foreach(i => list += matrix(x2)(i))
        //(x2-1,y1)->(x1+1,y1)
        (x2 - 1).to(x1 + 1, -1).filter(_ => y1 < y2).foreach(i => list += matrix(i)(y1))
        x1 += 1
        y1 += 1
        x2 -= 1
        y2 -= 1
      }
      list
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

  //test from here
  println(Solution.spiralOrder(Array(
    Array(1, 2, 3, 4),
    Array(5, 6, 7, 8),
    Array(9, 10, 11, 12)
  )))
}