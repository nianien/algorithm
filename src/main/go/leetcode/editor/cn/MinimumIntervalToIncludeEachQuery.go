//1851.minimum-interval-to-include-each-query
//给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束
//于 righti（包含两侧取值，闭区间）。区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
//
// 再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度
// 。如果不存在这样的区间，那么答案是 -1 。
//
// 以数组形式返回对应查询的所有答案。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
//输出：[3,3,1,4]
//解释：查询处理如下：
//- Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
//- Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
//- Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
//- Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
//
//
// 示例 2：
//
//
//输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
//输出：[2,-1,4,6]
//解释：查询处理如下：
//- Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
//- Query = 19：不存在包含 19 的区间，答案为 -1 。
//- Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
//- Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
//
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 10⁵
// 1 <= queries.length <= 10⁵
// intervals[i].length == 2
// 1 <= lefti <= righti <= 10⁷
// 1 <= queries[j] <= 10⁷
//
//
// Related Topics 数组 二分查找 排序 扫描线 堆（优先队列） 👍 90 👎 0

package main

import (
	"container/heap"
	"fmt"
	"sort"
)

// leetcode submit region begin(Prohibit modification and deletion)

type Triple struct {
	length int
	begin  int
	end    int
}

type Pair struct {
	index int
	value int
}

// PriorityQueue 使用数组作为堆的存储结构，元素为Triple
type PriorityQueue []Triple

// Len 实现sort#Len接口方法
func (pq *PriorityQueue) Len() int {
	return len(*pq)
}

// Less 实现sort#Less接口方法
func (pq *PriorityQueue) Less(i, j int) bool {
	return (*pq)[i].length < (*pq)[j].length
}

// Swap 实现sort#Swap接口方法
func (pq *PriorityQueue) Swap(i, j int) {
	(*pq)[i], (*pq)[j] = (*pq)[j], (*pq)[i]
}

func (pq *PriorityQueue) Push(x any) {
	*pq = append(*pq, x.(Triple))
}

func (pq *PriorityQueue) Pop() any {
	n, old := len(*pq), *pq
	x := old[n-1]
	*pq = old[0 : n-1]
	return x
}

func (pq *PriorityQueue) Peek() Triple {
	return (*pq)[0]
}

func minInterval(intervals [][]int, queries []int) []int {
	//构建二元组，query和索引位置
	pairs := make([]Pair, len(queries))
	for i := 0; i < len(queries); i++ {
		pairs[i] = Pair{i, queries[i]}
	}
	//按照query排序
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i].value < pairs[j].value
	})

	//构建三元组，起始位置及区间长度
	triples := make([]Triple, len(intervals))
	for i, interval := range intervals {
		triples[i] = Triple{interval[1] - interval[0] + 1, interval[0], interval[1]}
	}
	//按照区间开始位置排序
	sort.Slice(triples, func(i, j int) bool {
		return triples[i].begin < triples[j].begin
	})
	return minInterval0(triples, pairs)
}

func minInterval0(triples []Triple, pairs []Pair) []int {
	pq := &PriorityQueue{}
	res, i := make([]int, len(pairs)), 0
	for _, p := range pairs {
		//这里i退出循环不会重置，相当于只遍历一般triples
		for ; i < len(triples) && triples[i].begin <= p.value; i++ {
			//堆元素
			heap.Push(pq, triples[i])
		}
		for pq.Len() > 0 && pq.Peek().end < p.value {
			heap.Pop(pq)
		}
		if pq.Len() > 0 {
			res[p.index] = pq.Peek().length
		} else {
			res[p.index] = -1
		}
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println(minInterval([][]int{{1, 4}, {2, 4}, {3, 6}, {4, 4}}, []int{2, 7, 4, 1}))
}
