# 146.lru-cache
"""
Design a data structure that follows the constraints of a Least Recently Used (
LRU) cache. 

 Implement the LRUCache class: 

 
 LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
 int get(int key) Return the value of the key if the key exists, otherwise 
return -1. 
 void put(int key, int value) Update the value of the key if the key exists. 
Otherwise, add the key-value pair to the cache. If the number of keys exceeds the 
capacity from this operation, evict the least recently used key. 
 

 The functions get and put must each run in O(1) average time complexity. 

 
 Example 1: 

 
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

 
 Constraints: 

 
 1 <= capacity <= 3000 
 0 <= key <= 10⁴ 
 0 <= value <= 10⁵ 
 At most 2 * 10⁵ calls will be made to get and put. 
 

 Related Topics Hash Table Linked List Design Doubly-Linked List 👍 22757 👎 120
6

"""


# leetcode submit region begin(Prohibit modification and deletion)
class LRUCache:
    map: dict[int, "LRUCache.Node"]
    __slots__ = ("cap", "map", "list")

    class Node:
        pre: "LRUCache.Node"
        next: "LRUCache.Node"
        __slots__ = ("key", "val", "pre", "next")

        def __init__(self, key: int, val: int):
            self.key = key
            self.val = val
            self.pre = None
            self.next = None

        def detach(self):
            self.pre.next = self.next
            self.next.pre = self.pre

    class LinkedList:
        head: "LRUCache.Node"
        tail: "LRUCache.Node"
        __slots__ = ("head", "tail")

        def __init__(self):
            self.head = LRUCache.Node(-1, -1)
            self.tail = LRUCache.Node(-1, -1)
            self.head.next = self.tail
            self.tail.pre = self.head

        def move_to_front(self, node: "LRUCache.Node"):
            node.detach()
            self.add_to_front(node)

        def add_to_front(self, node: "LRUCache.Node"):
            first = self.head.next
            self.head.next = node
            node.pre = self.head
            node.next = first
            first.pre = node

        def remove_from_tail(self) -> "LRUCache.Node":
            last = self.tail.pre
            last.detach()
            return last

    def __init__(self, capacity: int):
        self.cap = capacity
        self.map = {}  # key -> Node
        self.list = self.LinkedList()

    def get(self, key: int) -> int:
        node = self.map.get(key)
        if node:
            self.list.move_to_front(node)
            return node.val
        return -1

    def put(self, key: int, value: int) -> None:
        if self.cap == 0:
            return
        node = self.map.get(key)
        if node:
            node.val = value
            self.list.move_to_front(node)
            return
        if len(self.map) >= self.cap:
            r = self.list.remove_from_tail()
            del self.map[r.key]
        node = LRUCache.Node(key, value)
        self.map[key] = node
        self.list.add_to_front(node)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    lru = LRUCache(2)
    lru.put(1, 1)
    lru.put(2, 2)
    lru.put(3, 3)
    print(lru.get(1))
