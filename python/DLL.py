class DLL_Node:
    def __init__(self, data):
        self.previous = self.next = None
        self.data = data
class DLL:
    def __init__(self):
        self.head = self.tail = None
        self.count = 0
    def remove(self, node: DLL_Node):
        self.count -= 1
        if self.head == node:
            self.head = self.head.next
            if self.head:
                self.head.previous = None
        elif self.tail == node:
            self.tail = self.tail.previous
            if self.tail:
                self.tail.next = None
        else:
            nxt = node.next
            pre = node.previous
            pre.next = nxt
            nxt.previous = pre
    def append(self, node: DLL_Node):
        node.previous = node.next = None
        if self.count == 0:
            self.head = self.tail = node
            self.count = 1
        else:
            self.insert_after(self.tail, node)
    def insert_after(self, node: DLL_Node, newnode: DLL_Node):
        # Insert newnode after node
        if node == None:
            self.head = self.tail = newnode
            self.count = 1
            return
        newnode.next = node.next
        newnode.previous = node
        node.next = newnode
        if newnode.next:
            newnode.next.previous = newnode
        else:
            self.tail = newnode
        self.count += 1
