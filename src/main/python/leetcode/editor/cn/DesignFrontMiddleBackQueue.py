# 1670.design-front-middle-back-queue
# 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。 
# 
#  请你完成 FrontMiddleBack 类： 
# 
#  
#  FrontMiddleBack() 初始化队列。 
#  void pushFront(int val) 将 val 添加到队列的 最前面 。 
#  void pushMiddle(int val) 将 val 添加到队列的 正中间 。 
#  void pushBack(int val) 将 val 添加到队里的 最后面 。 
#  int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 
#  int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 
#  int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 
#  
# 
#  请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说： 
# 
#  
#  将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。 
#  从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：
# ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", 
# "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
# [[], [1], [2], [3], [4], [], [], [], [], []]
# 输出：
# [null, null, null, null, null, 1, 3, 4, 2, -1]
# 
# 解释：
# FrontMiddleBackQueue q = new FrontMiddleBackQueue();
# q.pushFront(1);   // [1]
# q.pushBack(2);    // [1, 2]
# q.pushMiddle(3);  // [1, 3, 2]
# q.pushMiddle(4);  // [1, 4, 3, 2]
# q.popFront();     // 返回 1 -> [4, 3, 2]
# q.popMiddle();    // 返回 3 -> [4, 2]
# q.popMiddle();    // 返回 4 -> [2]
# q.popBack();      // 返回 2 -> []
# q.popFront();     // 返回 -1 -> [] （队列为空）
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= val <= 109 
#  最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。 
# 
#  
#  Related Topics 设计 链表 
#  👍 9 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class FrontMiddleBackQueue:

    def __init__(self):
        self.Q = []

    def pushFront(self, val: int) -> None:
        self.Q.insert(0, val)

    def pushMiddle(self, val: int) -> None:
        self.Q.insert(len(self.Q) // 2, val)

    def pushBack(self, val: int) -> None:
        self.Q.append(val)

    def popFront(self) -> int:
        return self.Q.pop(0) if self.Q else -1

    def popMiddle(self) -> int:
        return self.Q.pop((len(self.Q) - 1) // 2) if self.Q else -1

    def popBack(self) -> int:
        return self.Q.pop(-1) if self.Q else -1


# Your FrontMiddleBackQueue object will be instantiated and called as such:

# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    obj = FrontMiddleBackQueue()
    obj.pushFront(1)
    obj.pushMiddle(2)
    obj.pushBack(3)
    obj.popFront()
    obj.popMiddle()
    obj.popBack()
    obj.popFront()
    obj.popMiddle()
    obj.popBack()
