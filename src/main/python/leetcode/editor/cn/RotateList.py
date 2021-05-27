# 61.rotate-list
# 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,2,3,4,5], k = 2
# 输出：[4,5,1,2,3]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [0,1,2], k = 4
# 输出：[2,0,1]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表中节点的数目在范围 [0, 500] 内 
#  -100 <= Node.val <= 100 
#  0 <= k <= 2 * 109 
#  
#  Related Topics 链表 双指针 
#  👍 568 👎 0

from leetcode.editor.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if k == 0 or not head or not head.next:
            return head
        tail = head
        len_ = 0
        while tail and tail.next:
            tail = tail.next
            len_ += 1
        len_ += (1 if tail else 0)
        k %= len_
        if k == 0:
            return head
        tail.next = head
        cur = tail
        k = len_ - k
        while k:
            cur = cur.next
            k -= 1
        head = cur.next
        cur.next = None
        return head


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # print(Solution().rotateRight(ListNode.build(1, 2), 1))
    print(Solution().rotateRight(ListNode.build(1, 2, 3, 4, 5), 2))
