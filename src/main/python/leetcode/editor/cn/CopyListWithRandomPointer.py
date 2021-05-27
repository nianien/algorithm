# 138.copy-list-with-random-pointer
# 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。 
# 
#  构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 
# 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。 
# 
#  例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random 
# --> y 。 
# 
#  返回复制链表的头节点。 
# 
#  用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示： 
# 
#  
#  val：一个表示 Node.val 的整数。 
#  random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
#  
# 
#  你的代码 只 接受原链表的头节点 head 作为传入参数。 
# 
#  
# 
#  示例 1： 
# 
#  
# 
#  
# 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
# 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
#  
# 
#  示例 2： 
# 
#  
# 
#  
# 输入：head = [[1,1],[2,1]]
# 输出：[[1,1],[2,1]]
#  
# 
#  示例 3： 
# 
#  
# 
#  
# 输入：head = [[3,null],[3,0],[3,null]]
# 输出：[[3,null],[3,0],[3,null]]
#  
# 
#  示例 4： 
# 
#  
# 输入：head = []
# 输出：[]
# 解释：给定的链表为空（空指针），因此返回 null。
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= n <= 1000 
#  -10000 <= Node.val <= 10000 
#  Node.random 为空（null）或指向链表中的节点。 
#  
#  Related Topics 哈希表 链表 
#  👍 570 👎 0


class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random


# leetcode submit region begin(Prohibit modification and deletion)
"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return None
        node2idx = {}
        nodes_old = []
        nodes_new = []
        p = head
        id_ = 0
        # 只构建节点,不设置引用关系
        while p:
            nodes_old.append(p)
            nodes_new.append(Node(p.val))
            node2idx[p] = id_
            id_ += 1
            p = p.next
        # 根据原节点设置新节点的next和random引用
        for i in range(len(nodes_new)):
            old_: Node = nodes_old[i]
            new_: Node = nodes_new[i]
            if old_.next:
                idx_nxt = node2idx[old_.next]
                new_.next = nodes_new[idx_nxt]
            if old_.random:
                idx_random = node2idx[old_.random]
                new_.random = nodes_new[idx_random]
        return nodes_new[0]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # [[7,null],[13,0],[11,4],[10,2],[1,0]]
    node1 = Node(7)
    node2 = Node(13)
    node3 = Node(11, 4)
    node4 = Node(10, 2)
    node5 = Node(1, 0)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5
    node2.random = node1
    node3.random = node5
    node4.random = node3
    node5.random = node1
    print(Solution().copyRandomList(node1))
