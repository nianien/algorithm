# 237.delete-node-in-a-linked-list
# 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。 
# 
#  
# 
#  现有一个链表 -- head = [4,5,1,9]，它可以表示为: 
# 
#  
# 
#  
# 
#  示例 1： 
# 
#  输入：head = [4,5,1,9], node = 5
# 输出：[4,1,9]
# 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
#  
# 
#  示例 2： 
# 
#  输入：head = [4,5,1,9], node = 1
# 输出：[4,5,9]
# 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表至少包含两个节点。 
#  链表中所有节点的值都是唯一的。 
#  给定的节点为非末尾节点并且一定是链表中的一个有效节点。 
#  不要从你的函数中返回任何结果。 
#  
#  Related Topics 链表 
#  👍 908 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteNode(self, node):
        """
        本题考察的是, 在不给头结点的情况下如何删除指定节点。
        这里，我们可以变通一下，把当前节点的下一节点内容复制到该节点上，然后删除下一个节点
        经典独白：复制另一个人的身份,再让那个人消失,你就能活下来
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        node.val = node.next.val
        node.next = node.next.next


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    Solution()
