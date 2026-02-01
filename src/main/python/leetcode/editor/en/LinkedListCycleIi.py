# 142.linked-list-cycle-ii
"""
Given the head of a linked list, return the node where the cycle begins. If 
there is no cycle, return null. 

 There is a cycle in a linked list if there is some node in the list that can 
be reached again by continuously following the next pointer. Internally, pos is 
used to denote the index of the node that tail's next pointer is connected to (0-
indexed). It is -1 if there is no cycle. Note that pos is not passed as a 
parameter. 

 Do not modify the linked list. 

 
 Example 1: 
 
 
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the 
second node.
 

 Example 2: 
 
 
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the 
first node.
 

 Example 3: 
 
 
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

 
 Constraints: 

 
 The number of the nodes in the list is in the range [0, 10⁴]. 
 -10⁵ <= Node.val <= 10⁵ 
 pos is -1 or a valid index in the linked-list. 
 

 
 Follow up: Can you solve it using O(1) (i.e. constant) memory? 

 Related Topics Hash Table Linked List Two Pointers 👍 14934 👎 1076

"""
from typing import *

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        """
        # let a=distance from head to entry, b=distance from entry to meeting, c=length of circle
        # d(s)=a+b+x*c, d(f)=2(a+b+x*c)
        # d(f-s)=a+b+x*c=kc =>a+b=(k-x)c
        # it means when s move from head to entry(a steps), they will meet at entry
        :param head:
        :return:
        """
        f, s = head, head
        # Phase 1: detect cycle
        while f and f.next:
            f = f.next.next
            s = s.next
            if f == s:
                found = True
                break
        else:
            return None
        # Phase 2: find entry
        s = head
        while f != s:
            f = f.next
            s = s.next
        return s


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    n1 = ListNode(1)

    print(Solution().detectCycle(n1))
