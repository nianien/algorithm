//146.lru-cache
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
//
//
//
// 实现 LRUCache 类：
//
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
//
//
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
// 示例：
//
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
//
//
//
//
// 提示：
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 3000
// 0 <= value <= 104
// 最多调用 3 * 104 次 get 和 put
//
// Related Topics 设计
// 👍 1438 👎 0

package main

//leetcode submit region begin(Prohibit modification and deletion)
import (
	. "container/list"
	"fmt"
)

type LRUCache struct {
	//value为节点对象
	m map[int]*Element
	l *List
	c int
}
type KV struct {
	k int
	v int
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		c: capacity,
		l: New(),
		m: make(map[int]*Element),
	}
}

func (this *LRUCache) Get(key int) int {
	var v, ok = this.m[key]
	if ok {
		//move the value to first position
		this.l.MoveToFront(v)
		return v.Value.(KV).v
	} else {
		return -1
	}
}

func (this *LRUCache) Put(key int, value int) {
	var v, ok = this.m[key]
	if ok { //key exists
		//update the value at first position
		v.Value = KV{
			k: key,
			v: value,
		}
		this.l.MoveToFront(v)
	} else { //key not exists
		//not full
		if this.l.Len() >= this.c {
			// remove the last visited from map
			delete(this.m, this.l.Back().Value.(KV).k)
			// remove the last visited from list
			this.l.Remove(this.l.Back())
		}
		//insert new key at first position
		this.l.PushFront(KV{
			k: key,
			v: value,
		})
		this.m[key] = this.l.Front()
	}

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	var lru = Constructor(2)
	lru.Put(1, 1)
	lru.Put(2, 2)
	fmt.Println(lru.Get(1), lru.Get(1) == 1)
	lru.Put(3, 3)
	fmt.Println(lru.Get(2), lru.Get(2) == -1)
	lru.Put(4, 4)
	fmt.Println(lru.Get(1), lru.Get(1) == -1)
	fmt.Println(lru.Get(3), lru.Get(3) == 3)
	fmt.Println(lru.Get(4), lru.Get(4), lru.Get(4) == 4)

}
