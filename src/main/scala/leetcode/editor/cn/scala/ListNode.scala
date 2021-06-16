package leetcode.editor.cn.scala

import scala.collection.mutable.Map
import scala.util.control.Breaks

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var x = _x
  var next = _next

  /**
   * 打印链表
   *
   * @return
   */
  override def toString: String = {
    val sb = new StringBuilder("|")
    var current = this
    val nodes = Map[ListNode, Int]()
    var id = 1
    val loop = new Breaks;
    loop.breakable {
      while (current != null) {
        sb.append("->").append(current.x)
        if (nodes.contains(current)) {
          sb.append("*(").append(nodes.getOrElse(current, null)).append(")")
          loop.break()
        } else {
          nodes += ((current, {
            id += 1
            id
          }))
        }
        current = current.next
      }
    }
    sb.toString
  }

}


object ListNode {

  def apply(_x: Int = 0, _next: ListNode = null): ListNode = {
    new ListNode(_x, _next)
  }

  /**
   * 构建链表
   *
   * @param values
   * @return
   */
  def build(values: Int*): ListNode = {
    val h = new ListNode(-1)
    var p = h
    for (value <- values) {
      p.next = new ListNode(value)
      p = p.next
    }
    h.next
  }
}

object Test extends App {
  var n1 = ListNode(1)
  var n2 = ListNode(2)
  var n3 = ListNode(1)
  var n4 = ListNode(4)
  var n5 = ListNode(5)
  n1.next = n2
  n2.next = n3
  n3.next = n4
  n4.next = n5
  n5.next = n3
  println(n1)
}