class ListItem:
	next = None
	val = 0

item1 = ListItem()
item2 = ListItem()
item3 = ListItem()


item1.next = item2
item2.next = item3

item1.val = 1
item2.val = 2
item3.val = 3

def printList(head):
	while head != None:
		print head.val
		head = head.next


def reverse(head):
	curr = head
	prev = None

	while curr != None:
		tmp = curr.next
		curr.next = prev
		prev = curr
		curr = tmp
		
	return prev

printList(reverse(item1))



	

