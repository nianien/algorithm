package leetcode.editor.scala

class ListNode(_x: Int = 0, _next: ListNode = null) {
    var x = _x
    var next = _next

    /**
     * 打印链表
     *
     * @return
     */
    override def toString: String = {
      val sb = new StringBuilder
      var current = this
      var isFirst = true
      while (current != null) {
        if (isFirst) isFirst = false
        else sb.append("->")
        sb.append(current.x)
        current = current.next
      }
      sb.toString
    }

  }


  object ListNode {
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