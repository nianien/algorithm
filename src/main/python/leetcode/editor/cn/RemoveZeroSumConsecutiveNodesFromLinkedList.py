# 1171.remove-zero-sum-consecutive-nodes-from-linked-list
# 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。 
# 
#  删除完毕后，请你返回最终结果链表的头节点。 
# 
#  
# 
#  你可以返回任何满足题目要求的答案。 
# 
#  （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。） 
# 
#  示例 1： 
# 
#  输入：head = [1,2,-3,3,1]
# 输出：[3,1]
# 提示：答案 [1,2,1] 也是正确的。
#  
# 
#  示例 2： 
# 
#  输入：head = [1,2,3,-3,4]
# 输出：[1,2,4]
#  
# 
#  示例 3： 
# 
#  输入：head = [1,2,3,-3,-2]
# 输出：[1]
#  
# 
#  
# 
#  提示： 
# 
#  
#  给你的链表中可能有 1 到 1000 个节点。 
#  对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000. 
#  
#  Related Topics 链表 
#  👍 121 👎 0

from collections import defaultdict

from leetcode.editor.cn.defined import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeZeroSumSublists(self, head: ListNode) -> ListNode:
        """
        前缀和，pre_sum[i]=sum[0:i], sum[i:j] = pre_sum[j] - pre_sum[i]；
        我们使用dict存储前缀和与尾节点的对应关系,则有node[i]=dict[pre_sum[i]]

        假设pre_sum[i-1]≠0,sum[i:j]=0, 则pre_sum[i-1]=pre_sum[j+1]
        因此 node[i-1].next=dict[pre_sum[i]].next=dict[pre_sum[j+1]].next=node[j+1].next
        由于相同前缀和的尾结点被覆盖, 利用字典，第二次遍历的时候, 相当于从node[i:j]被删除了
        presum[j]=sum[0:j]
        sum[i:j] = presum[j] - presum[i]
        :param head:
        :return:
        """
        pre_sum = defaultdict()
        dummy = ListNode(next=head)
        p = dummy
        sum_ = 0
        while p:
            sum_ += p.val
            pre_sum[sum_] = p  # 前缀和的尾结点
            p = p.next
        sum_ = 0
        p = dummy
        while p:
            sum_ += p.val
            p.next = pre_sum[sum_].next  # 2个结点中间，都直接删除了 要么就只有自己1个结点
            p = p.next
        return dummy.next


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().removeZeroSumSublists(ListNode.build(1, 2, 3, -3, 4)))
