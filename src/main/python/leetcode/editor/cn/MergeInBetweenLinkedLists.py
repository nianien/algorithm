# 1669.merge-in-between-linked-lists
# 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。 
# 
#  请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。 
# 
#  下图中蓝色边和节点展示了操作后的结果： 
# 
#  请你返回结果链表的头指针。 
# 
#  
# 
#  示例 1： 
# 
#  
# 
#  
# 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
# 输出：[0,1,2,1000000,1000001,1000002,5]
# 解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
#  
# 
#  示例 2： 
# 
#  
# 输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,100
# 0003,1000004]
# 输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
# 解释：上图中蓝色的边和节点为答案链表。
#  
# 
#  
# 
#  提示： 
# 
#  
#  3 <= list1.length <= 104 
#  1 <= a <= b < list1.length - 1 
#  1 <= list2.length <= 104 
#  
#  Related Topics 链表 
#  👍 12 👎 0

from leetcode.editor.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeInBetween(self, list1: ListNode, a: int, b: int, list2: ListNode) -> ListNode:
        dummy_ = ListNode(-1, list1)
        p = dummy_
        pa, pb = None, None
        for i in range(max(a, b) + 1):
            if a == i:
                pa = p
            if b == i:
                pb = p
            if pa and pb:
                break
            if p:
                p = p.next
            else:
                break
        # 取截止节点下一个节点,提前断链,保持后续节点不变
        pb = pb.next.next
        pa.next = list2
        while pa.next:
            pa = pa.next
        pa.next = pb
        return dummy_.next


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    l1 = ListNode.build(0, 1, 2)
    l2 = ListNode.build(1000000, 1000001, 1000002, 1000003)
    print(Solution().mergeInBetween(l1, 1, 1, l2))
