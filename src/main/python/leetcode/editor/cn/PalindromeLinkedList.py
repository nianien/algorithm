# 234.palindrome-linked-list
# 请判断一个链表是否为回文链表。 
# 
#  示例 1: 
# 
#  输入: 1->2
# 输出: false 
# 
#  示例 2: 
# 
#  输入: 1->2->2->1
# 输出: true
#  
# 
#  进阶： 
# 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
#  Related Topics 链表 双指针 
#  👍 989 👎 0

from leetcode.editor.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def isPalindrome_by_arr(self, head: ListNode) -> bool:
        vals = []
        p = head
        while not p:
            vals.append(p.val)
            p = p.next
        return vals == vals[::-1]

    def isPalindrome(self, head: ListNode) -> bool:
        """
        将链表等分成两部分,后半部分翻转,然后比较两部分链表节点是否相等
        注意:判断之后将后半部分再次翻转进行还原
        :param head:
        :return:
        """
        if not head or not head.next:
            return True
        # 前半部分尾节点
        half_ = self.left_half(head)
        # 将后半部分翻转
        reverse_ = self.reverse_list(half_.next)
        p1 = head
        p2 = reverse_
        palindrome = True
        while p1 and p2:
            if p1.val != p2.val:
                palindrome = False
                break
            p1 = p1.next
            p2 = p2.next
        # 恢复后半部分翻转的链表
        self.reverse_list(reverse_)
        return palindrome

    def left_half(self, head: ListNode) -> ListNode:
        """
         左半部分中间节点
        :param head:
        :return:
        """
        fast, slow = head, head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        return slow

    def reverse_list(self, head: ListNode) -> ListNode:
        """
        翻转链表
        :param head:
        :return:
        """
        pre, cur = None, head
        while cur:
            next_ = cur.next
            cur.next = pre
            pre = cur
            cur = next_
        return pre


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    s = Solution()
    l = ListNode.build(1, 2)
    print(l)
    print(s.isPalindrome(l))
    print(l)
