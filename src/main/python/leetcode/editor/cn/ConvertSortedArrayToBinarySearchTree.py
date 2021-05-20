# 108.convert-sorted-array-to-binary-search-tree
# 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。 
# 
#  高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [-10,-3,0,5,9]
# 输出：[0,-3,9,-10,null,5]
# 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
# 
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [1,3]
# 输出：[3,1]
# 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 104 
#  -104 <= nums[i] <= 104 
#  nums 按 严格递增 顺序排列 
#  
#  Related Topics 树 深度优先搜索 
#  👍 765 👎 0


from leetcode.editor.cn.TypeDefined import TreeNode


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution(object):
    def sortedArrayToBST(self, nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        return self.sortedArrayToBST_(nums, 0, len(nums) - 1)

    def sortedArrayToBST_(self, nums, begin, end):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        print(begin, end)
        mid = (begin + end) // 2
        tree = TreeNode(nums[mid])
        # 中间位置作为根节点, 较小部分作为左子树,较大部分作为右子树
        if mid > begin:
            tree.left = self.sortedArrayToBST_(nums, begin, mid - 1)
        if mid < end:
            tree.right = self.sortedArrayToBST_(nums, mid + 1, end)
        return tree


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().sortedArrayToBST([-10, -3, 0, 5, 9]))
