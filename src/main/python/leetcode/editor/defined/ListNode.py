# https://www.python.org/dev/peps/pep-0484/#the-problem-of-forward-declarations
# from __future__ import annotations


class ListNode(object):
    def __init__(self, val=0, next: 'ListNode' = None):
        self.val = val
        self.next = next

    def __str__(self):
        list_ = ["|"]
        current = self
        nodes_ = {}
        id_ = 1
        while current:
            list_.append("->")
            list_.append(current.val)
            if current in nodes_:
                list_.append("*(")
                list_.append(nodes_[current])
                list_.append(")")
                break
            else:
                nodes_[current] = id_
                id_ += 1
            current = current.next
        return "".join(str(i) for i in list_)

    @staticmethod
    def build(*values):
        h = ListNode(-1)
        p = h
        for value in values:
            p.next = ListNode(value)
            p = p.next
        return h.next


if __name__ == "__main__":
    n1 = ListNode(1)
    n2 = ListNode(2)
    n3 = ListNode(1)
    n4 = ListNode(4)
    n5 = ListNode(5)
    n1.next = n2
    n2.next = n3
    n3.next = n4
    n4.next = n5
    n5.next = n3
    print(n1)
