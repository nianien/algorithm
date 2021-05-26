class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        list_ = []
        current = self
        is_first = True
        while current:
            if is_first:
                is_first = False
            else:
                list_.append("->")
            list_.append(current.val)
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
