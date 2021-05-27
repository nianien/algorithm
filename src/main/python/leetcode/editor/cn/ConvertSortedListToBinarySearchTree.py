# 109.convert-sorted-list-to-binary-search-tree
# 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 
# 
#  本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
# 
#  示例: 
# 
#  给定的有序链表： [-10, -3, 0, 5, 9],
# 
# 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
# 
#       0
#      / \
#    -3   9
#    /   /
#  -10  5
#  
#  Related Topics 深度优先搜索 链表 
#  👍 529 👎 0

from leetcode.editor.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        if not head:
            return None
        if not head.next:
            return TreeNode(head.val)
        # 找到中间节点的前一个节点,进行断链
        fast, slow = head, ListNode(next=head)
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        root = slow.next
        slow.next = None
        right = root.next
        root.next = None
        return TreeNode(root.val, self.sortedListToBST(head), self.sortedListToBST(right))


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    Solution()
