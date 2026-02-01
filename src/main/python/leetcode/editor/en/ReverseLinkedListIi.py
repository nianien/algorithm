# 92.reverse-linked-list-ii
"""
Given the head of a singly linked list and two integers left and right where 
left <= right, reverse the nodes of the list from position left to position right, 
and return the reversed list. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
 

 Example 2: 

 
Input: head = [5], left = 1, right = 1
Output: [5]
 

 
 Constraints: 

 
 The number of nodes in the list is n. 
 1 <= n <= 500 
 -500 <= Node.val <= 500 
 1 <= left <= right <= n 
 

 
Follow up: Could you do it in one pass?

 Related Topics Linked List 👍 12742 👎 772

"""
from typing import *

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
        if not head or left == right:
            return head

        dummy = ListNode(-1, head)
        left_pre = dummy
        cur = head
        rev_pre = None

        for i in range(1, right + 1):
            nxt = cur.next
            if i < left:
                left_pre = cur
            else:
                cur.next = rev_pre
                rev_pre = cur
            cur = nxt

        # at this point:
        # pre is right, cur is right+1
        # left_pre is left-1, left_pre.next is left
        left_pre.next.next = cur
        left_pre.next = rev_pre

        return dummy.next


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution())
