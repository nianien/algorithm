# 121.best-time-to-buy-and-sell-stock
# 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
# 
#  你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
# 
#  返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：[7,1,5,3,6,4]
# 输出：5
# 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
#      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
#  
# 
#  示例 2： 
# 
#  
# 输入：prices = [7,6,4,3,1]
# 输出：0
# 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= prices.length <= 105 
#  0 <= prices[i] <= 104 
#  
#  Related Topics 数组 动态规划 
#  👍 1632 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        sum_ = 0
        # 买入时机
        # idx = 0
        # for i in range(1, len(prices)):
        #     # 如果当前利润为负, 则从新买入一定比负利润更大
        #     # 如果当前利润为正, 则可以对后续利润产生增量
        #     cur = prices[i] - prices[idx]
        #     if cur < 0:
        #         idx = i
        #     elif cur > sum_:
        #         sum_ = cur
        # return sum_
        n = len(prices)
        buy1 = -prices[0]
        sell1 = 0
        for i in range(1, n):
            buy1 = max(buy1, -prices[i])
            sell1 = max(sell1, buy1 + prices[i])
        return sell1


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().maxProfit([7, 1, 5, 3, 6, 4]))
    print(Solution().maxProfit([7, 6, 4, 3, 1]))
