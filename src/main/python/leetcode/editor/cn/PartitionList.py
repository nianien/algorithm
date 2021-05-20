# 86.partition-list
# 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。 
# 
#  你应当 保留 两个分区中每个节点的初始相对位置。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,4,3,2,5,2], x = 3
# 输出：[1,2,2,4,3,5]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [2,1], x = 2
# 输出：[1,2]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表中节点的数目在范围 [0, 200] 内 
#  -100 <= Node.val <= 100 
#  -200 <= x <= 200 
#  
#  Related Topics 链表 双指针 
#  👍 400 👎 0


from leetcode.editor.cn.TypeDefined import ListNode


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution(object):
    def partition(self, head, x):
        """
         单链表拼接
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        dummy = ListNode(-1, head)
        # 找到第一个大于等于x的节点, 注意是大于等于不是等于
        # tp为第一个大于等于x的前置节点,即tp.next.val>=x
        tp = dummy
        while tp.next:
            if tp.next.val >= x:
                break
            else:
                tp = tp.next
        if tp.next:
            p = tp.next
            while p.next:
                if p.next.val < x:
                    # p的下一个节点小于x,则需要将该节点插入tp之后,随后移动tp指针
                    p_next = p.next
                    # 断链,移除节点p_next
                    p.next = p.next.next
                    # 续链, 将p_next挂在节点tp之后
                    p_next.next = tp.next
                    tp.next = p_next
                    # 按顺序插入,需要移动tp尾指针
                    tp = tp.next
                else:
                    p = p.next
        return dummy.next

    @staticmethod
    def partition1(head, x):
        """
            双链表拼接
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        left, right = ListNode(), ListNode()
        lp, rp, p = left, right, head
        while p:
            if p.val < x:
                lp.next = p
                lp = lp.next
            else:
                rp.next = p
                rp = rp.next
            p = p.next
        rp.next = None
        lp.next = right.next
        return left.next


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution().partition(ListNode.build(1, 4, 3, 2, 5, 2), 3))
    print(Solution().partition(ListNode.build(1, 4, 3, 0, 2, 5, 2), 3))
