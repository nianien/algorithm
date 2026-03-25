//295.find-median-from-data-stream
/**
 * The median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value, and the median is the mean of the two
 * middle values.
 * <p>
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Implement the MedianFinder class:
 * <p>
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data
 * structure.
 * double findMedian() returns the median of all elements so far. Answers within 1
 * 0⁻⁵ of the actual answer will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * -10⁵ <= num <= 10⁵
 * There will be at least one element in the data structure before calling
 * findMedian.
 * At most 5 * 10⁴ calls will be made to addNum and findMedian.
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * <p>
 * If all integer numbers from the stream are in the range [0, 100], how would
 * you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how
 * would you optimize your solution?
 * <p>
 * <p>
 * Related Topics Two Pointers Design Sorting Heap (Priority Queue) Data Stream 👍
 * 13150 👎 283
 */

package leetcode.editor.en;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    public static void main(String[] args) {
//       Solution solution = new FindMedianFromDataStream().new Solution();
    }

    /**
     * 解题思路：对顶堆（大顶堆 + 小顶堆）
     *
     * 核心思想：
     *   用两个堆将数据流"一分为二"，使得中位数始终可以从堆顶 O(1) 获取。
     *
     *   将有序序列从中间切开：
     *     左半部分（较小的一半）-> 用大顶堆 low 维护，堆顶 = 左半部分的最大值
     *     右半部分（较大的一半）-> 用小顶堆 high 维护，堆顶 = 右半部分的最小值
     *
     *   示意图：
     *     low (大顶堆)          high (小顶堆)
     *     ┌───────────┐        ┌───────────┐
     *     │ ... 3 5 [7]│  ≤   │[8] 10 ... │
     *     └───────────┘        └───────────┘
     *       max(low)=7    ≤    min(high)=8
     *
     * 两个不变量（invariant）：
     *   1. max(low) ≤ min(high)  —— 左半部分所有元素 ≤ 右半部分所有元素
     *   2. size(low) ≥ size(high) 且 size(low) - size(high) ≤ 1
     *      —— 元素总数为奇数时 low 多存一个，中位数就是 low.peek()
     *
     * addNum 流程（巧妙的两步交换保证不变量）：
     *   1. 先将 num 放入 low，再将 low 的堆顶（最大值）移到 high
     *      -> 保证 max(low) ≤ min(high)
     *   2. 如果 low.size < high.size，将 high 的堆顶移回 low
     *      -> 保证 size(low) ≥ size(high)
     *
     * findMedian：
     *   奇数个元素：low 多一个，中位数 = low.peek()
     *   偶数个元素：两堆大小相等，中位数 = (low.peek() + high.peek()) / 2.0
     *
     * 推演示例 addNum(1), addNum(2), addNum(3)：
     *   add(1): low=[1], high=[]  -> low→high: low=[], high=[1]
     *           low.size < high.size -> high→low: low=[1], high=[]
     *           median = 1
     *   add(2): low=[1,2], high=[] -> low→high: low=[1], high=[2]
     *           low.size == high.size, ok
     *           median = (1+2)/2 = 1.5
     *   add(3): low=[1,3], high=[2] -> low→high: low=[1], high=[2,3]
     *           low.size < high.size -> high→low: low=[1,2], high=[3]
     *           median = 2
     *
     * 时间复杂度：addNum O(log n)，findMedian O(1)
     * 空间复杂度：O(n)
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {

        // 小顶堆：存较大的一半，堆顶 = 右半部分最小值
        private PriorityQueue<Integer> high = new PriorityQueue<>();
        // 大顶堆：存较小的一半，堆顶 = 左半部分最大值
        private PriorityQueue<Integer> low = new PriorityQueue<>((x, y) -> y - x);

        public MedianFinder() {

        }

        public void addNum(int num) {
            // step1: num 先入 low，再把 low 最大值移到 high，保证 max(low) ≤ min(high)
            low.offer(num);
            high.offer(low.poll());
            // step2: 如果 high 比 low 多了，把 high 最小值移回 low，保证 size(low) ≥ size(high)
            if (low.size() < high.size()) {
                low.offer(high.poll());
            }
        }

        public double findMedian() {
            // 奇数个元素：low 多一个，中位数直接取 low 堆顶
            if (low.size() > high.size()) {
                return low.peek();
            }
            // 偶数个元素：取两个堆顶的平均值
            return (low.peek() + high.peek()) / 2.0;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}