# 143.reorder-list
# 给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
# 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
# 
#  你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
# 
#  示例 1: 
# 
#  给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
# 
#  示例 2: 
# 
#  给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
#  Related Topics 链表 
#  👍 587 👎 0

from leetcode.editor.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if not head or not head.next or not head.next.next:
            return
        p = head
        nodes = []
        while p:
            nodes.append(p)
            p = p.next
        i, j = 0, len(nodes) - 1
        while j - i > 1:
            nodes[i].next = nodes[j]
            nodes[j].next = nodes[i + 1]
            i += 1
            j -= 1
        # j为重排最后一个节点,尾指针置空
        # j-i=0或j-i=1时都不调整顺序
        nodes[j].next = None


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    l = ListNode.build(1, 2, 3, 4, 5)
    Solution().reorderList(l)
    print(l)
