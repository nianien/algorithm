# 83.remove-duplicates-from-sorted-list
# 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。 
# 
#  返回同样按升序排列的结果链表。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,1,2]
# 输出：[1,2]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [1,1,2,3,3]
# 输出：[1,2,3]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表中节点数目在范围 [0, 300] 内 
#  -100 <= Node.val <= 100 
#  题目数据保证链表已经按升序排列 
#  
#  Related Topics 链表 
#  👍 575 👎 0
from sre_parse import Tokenizer

from requests import Response

from leetcode.editor.cn.TypeDefined import ListNode


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next


class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if not head:
            return head
        dummy = ListNode(next=head)
        pre = dummy.next
        p = pre.next
        while p:
            if pre.val != p.val:
                pre.next = p
                pre = p
            p = p.next
        pre.next = p
        return dummy.next


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().deleteDuplicates(ListNode.build(1, 2, 3, 3, 4, 4)))
    print(Solution().deleteDuplicates(ListNode.build()))