# 160.intersection-of-two-linked-lists
# 编写一个程序，找到两个单链表相交的起始节点。 
# 
#  如下面的两个链表： 
# 
#  
# 
#  在节点 c1 开始相交。 
# 
#  
# 
#  示例 1： 
# 
#  
# 
#  输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
# kipB = 3
# 输出：Reference of the node with value = 8
# 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
# ,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
#  
# 
#  
# 
#  示例 2： 
# 
#  
# 
#  输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
#  1
# 输出：Reference of the node with value = 2
# 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
# ]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
#  
# 
#  
# 
#  示例 3： 
# 
#  
# 
#  输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
# 输出：null
# 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
#  skipA 和 skipB 可以是任意值。
# 解释：这两个链表不相交，因此返回 null。
#  
# 
#  
# 
#  注意： 
# 
#  
#  如果两个链表没有交点，返回 null. 
#  在返回结果后，两个链表仍须保持原有的结构。 
#  可假定整个链表结构中没有循环。 
#  程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
#  
#  Related Topics 链表 
#  👍 1141 👎 0

from leetcode.editor.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

def get_len(head: ListNode) -> int:
    len_ = 0
    while head:
        head = head.next
        len_ += 1
    return len_


class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        # 如果相交,则相交部分长度相等
        # 因此可以将两个链表尾部对齐, 然后从短链表表头位置开始一一比较节点是否相等,第一个相等的节点即是交点
        la = get_len(headA)
        lb = get_len(headB)
        if la < lb:
            for i in range(lb - la):
                headB = headB.next
        else:
            for i in range(la - lb):
                headA = headA.next
        while headA != headB:
            headA = headA.next
            headB = headB.next
        return headA

    def getIntersectionNode2(self, headA: ListNode, headB: ListNode) -> ListNode:
        # 两个指针都移动a->b和b->a,如果相交,则一定会相遇
        # 若相交,则尾节点一致, 则往前推,必定同时遍历相交节点
        curr1, curr2 = headA, headB
        while curr1 != curr2:
            curr1 = curr1.next if curr1 else headB
            curr2 = curr2.next if curr2 else headA

        return curr1


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    la = ListNode.build(4, 1)
    lb = ListNode.build(5, 6, 1)
    lc = ListNode.build(8, 4, 5)
    tail = la
    while tail.next:
        tail = tail.next
    tail.next = lc
    print(la)
    tail = lb
    while tail.next:
        tail = tail.next
    tail.next = lc
    print(lb)
    node = Solution().getIntersectionNode2(la, lb)
    print(node)

