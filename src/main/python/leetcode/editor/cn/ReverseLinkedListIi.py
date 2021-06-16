# 92.reverse-linked-list-ii
# 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
# 表节点，返回 反转后的链表 。
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,2,3,4,5], left = 2, right = 4
# 输出：[1,4,3,2,5]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [5], left = 1, right = 1
# 输出：[5]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表中节点数目为 n 
#  1 <= n <= 500 
#  -500 <= Node.val <= 500 
#  1 <= left <= right <= n 
#  
# 
#  
# 
#  进阶： 你可以使用一趟扫描完成反转吗？ 
#  Related Topics 链表 
#  👍 909 👎 0

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
def reverse_list(head: ListNode):
    # 也可以使用递归反转一个链表
    pre, cur = None, head
    while cur:
        next_ = cur.next
        cur.next = pre
        pre = cur
        cur = next_


class Solution:
    @staticmethod
    def reverseBetween(head: ListNode, left: int, right: int) -> ListNode:
        """
        :param head:
        :param left:
        :param right:
        :return:
        """
        # 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        dummy_ = ListNode(-1)
        dummy_.next = head
        pre = dummy_
        # 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        # 建议写在 for 循环里，语义清晰
        for _ in range(left - 1):
            pre = pre.next

        # 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        left_ = pre.next
        right_ = left_
        for _ in range(right - left):
            right_ = right_.next
        # 第 3 步：切断出一个子链表（截取链表）
        # cur_为切断后的第一个节点
        cur_ = right_.next
        pre.next = None
        right_.next = None

        # 第 4 步：反转链表的子区间
        reverse_list(left_)
        # 第 5 步：接回到原来的链表中
        pre.next = right_
        left_.next = cur_
        return dummy_.next


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().reverseBetween(ListNode.build(1, 2, 3, 4, 5), 2, 4))
