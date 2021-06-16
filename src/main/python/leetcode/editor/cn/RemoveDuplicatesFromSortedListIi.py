# 82.remove-duplicates-from-sorted-list-ii
# 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。 
# 
#  返回同样按升序排列的结果链表。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,2,3,3,4,4,5]
# 输出：[1,2,5]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [1,1,1,2,3]
# 输出：[2,3]
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
#  👍 617 👎 0

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        dummy_ = ListNode(next=head)
        pre = dummy_
        cur = pre.next
        duplicate = 0
        while cur and cur.next:
            if cur.val == cur.next.val:
                duplicate += 1
            else:
                if duplicate == 0:
                    pre.next = cur
                    pre = cur
                duplicate = 0
            cur = cur.next
        pre.next = cur if duplicate == 0 else None
        return dummy_.next


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution().deleteDuplicates(ListNode.build(1, 1, 1)))
    print(Solution().deleteDuplicates(ListNode.build(1, 1, 1, 2, 3)))
    print(Solution().deleteDuplicates(ListNode.build(1, 2, 3, 3, 4, 4, 5)))
