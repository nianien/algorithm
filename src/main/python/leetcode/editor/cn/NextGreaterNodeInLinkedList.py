# 1019.next-greater-node-in-linked-list
# 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。 
# 
#  每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.
# val，那么就有 j > i 且 node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0
#  。 
# 
#  返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。 
# 
#  注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。 
# 
#  
# 
#  示例 1： 
# 
#  输入：[2,1,5]
# 输出：[5,5,0]
#  
# 
#  示例 2： 
# 
#  输入：[2,7,4,3,5]
# 输出：[7,0,5,5,0]
#  
# 
#  示例 3： 
# 
#  输入：[1,7,5,1,9,2,5,1]
# 输出：[7,9,9,9,0,5,0,0]
#  
# 
#  
# 
#  提示： 
# 
#  
#  对于链表中的每个节点，1 <= node.val <= 10^9 
#  给定列表的长度在 [0, 10000] 范围内 
#  
#  Related Topics 栈 链表 
#  👍 152 👎 0
from typing import List

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def nextLargerNodes(self, head: ListNode) -> List[int]:
        if not head:
            return []
        res = []
        unrest = []
        index = 0
        # 如果当前元素比下一个元素大,则标记该元素未匹配, 由此可知所有未匹配的元素一定是降序排列的
        # 所以判断当前元素是否是前面未匹配元素的更大值,只需与未匹配的元素逆序进行比较
        # 如果某一个元素比当前元素大,则前面的元素必然也大于当前元素
        while head:
            res.append(0)
            if not head.next:
                break
            if head.val < head.next.val:
                res[index] = head.next.val
                while len(unrest):
                    if unrest[-1][1] < head.next.val:
                        res[unrest[-1][0]] = head.next.val
                        del unrest[-1]
                    else:
                        break
            else:
                unrest.append((index, head.val))
            head = head.next
            index += 1
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().nextLargerNodes(ListNode.build(9, 7, 6, 7, 9)))
    print(Solution().nextLargerNodes(ListNode.build(2, 7, 4, 3, 5)))
    print(Solution().nextLargerNodes(ListNode.build(1, 7, 5, 1, 9, 2, 5, 1)))
