# 123.best-time-to-buy-and-sell-stock-iii
"""
You are given an array prices where prices[i] is the price of a given stock on 
the iᵗʰ day. 

 Find the maximum profit you can achieve. You may complete at most two 
transactions. 

 Note: You may not engage in multiple transactions simultaneously (i.e., you 
must sell the stock before you buy again). 

 
 Example 1: 

 
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-
0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3. 

 Example 2: 

 
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-
1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are 
engaging multiple transactions at the same time. You must sell before buying 
again.
 

 Example 3: 

 
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

 
 Constraints: 

 
 1 <= prices.length <= 10⁵ 
 0 <= prices[i] <= 10⁵ 
 

 Related Topics Array Dynamic Programming 👍 10471 👎 219

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        K = 2
        NEG = -10 ** 18
        # 不持股(0)     持股(1)
        # k = 0       dp[0][0]     dp[0][1]
        # k = 1       dp[1][0]     dp[1][1]
        # k = 2       dp[2][0]     dp[2][1]
        dp = [[0, NEG] for _ in range(K + 1)]

        for price in prices:
            for k in range(K, 0, -1):
                #如果第 k 天不持股, 则须卖掉股票,即dp[k][1] + price, 表示当前持股利润+价格
                dp[k][0] = max(dp[k][0], dp[k][1] + price)  # sell
                #如果第 k 天持股, 则前一天买股票,即dp[k - 1][0] - price
                dp[k][1] = max(dp[k][1], dp[k - 1][0] - price) # buy


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution())
