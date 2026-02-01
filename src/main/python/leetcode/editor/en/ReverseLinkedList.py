# 206.reverse-linked-list
"""
Given the head of a singly linked list, reverse the list, and return the 
reversed list. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
 

 Example 2: 
 
 
Input: head = [1,2]
Output: [2,1]
 

 Example 3: 

 
Input: head = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the list is the range [0, 5000]. 
 -5000 <= Node.val <= 5000 
 

 
 Follow up: A linked list can be reversed either iteratively or recursively. 
Could you implement both? 

 Related Topics Linked List Recursion 👍 24071 👎 564

"""
from typing import Optional

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return None
        pre = None
        cur = head
        while cur:
            # cur.next, pre, cur = pre, cur, cur.next
            tmp = cur.next
            cur.next = pre
            pre = cur
            cur = tmp
        return pre

    # recursively
    def reverseList_v0(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        new_head = self.reverseList_v0(head.next)
        head.next.next = head
        head.next = None
        return new_head


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution())
