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

print "find n-th elt from the end of the list"

item1 = ListItem()
item2 = ListItem()
item3 = ListItem()
item4 = ListItem()
item5 = ListItem()
item6 = ListItem()


item1.next = item2
item2.next = item3
item3.next = item4
item4.next = item5
item5.next = item6

item1.val = 1
item2.val = 2
item3.val = 3
item4.val = 4
item5.val = 5
item6.val = 6


def findNthFromTheEnd(head, n):
	curr = head
	prev = None
	cnt = 0

	while curr != None:
		if cnt % n == 0:
			prev = curr

		curr = curr.next
		cnt = cnt + 1

	return prev

print findNthFromTheEnd(item1, 2).val

def findMiddleElt(head):
	slow = head
	fast = head

	middle = None

	if head.next == None:
		return head

	while slow != None:
		fast = fast.next
		if (fast != None and fast.next != None):
			fast = fast.next
		else:
			return slow

		slow = slow.next
		

print findMiddleElt(item1).val

	

