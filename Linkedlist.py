class Node:
    def __init__(self,data):
        self.data = data
        self.next  = None

class Linkedlist:
    def __init__(self):
        self.head = None

    def insert_front(self,data):
        new_node = Node(data)
        new_node.next = self.head
        self.head = new_node

    def insert_end(self,data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return
        curr_node = self.head
        while curr_node.next is not None:
            curr_node = curr_node.next
        curr_node.next = new_node

    def delte_front(self):
        if self.head is None:
            return
        self.head = self.head.next

    def delete_end(self):
        if self.head is None:
            return
        if self.head.next is None:
            self.head = None
            return
        curr_node = self.head
        while curr_node.next.next is not None:
            curr_node = curr_node.next
        curr_node.next = None


if __name__ == "__main__":
    linked_list = Linkedlist()

    # Insert at front
    linked_list.insert_front(30)
    linked_list.insert_front(20)
    linked_list.insert_front(10)

    # Insert at end
    linked_list.insert_end(40)
    linked_list.insert_end(50)

    # Display list
    curr_node = linked_list.head
    while curr_node is not None:
        print(curr_node.data, end=" -> ")
        curr_node = curr_node.next

    print("None")

    # Delete front
    linked_list.delte_front()

    # Delete end
    linked_list.delete_end()

    # Display after deletion
    curr_node = linked_list.head
    while curr_node is not None:
        print(curr_node.data, end=" -> ")
        curr_node = curr_node.next

    print("None")