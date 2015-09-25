class ListItem:
	val = 0
	next = None

class List:
	head = None

	# add to begin for O(1)
	def add(self, val):
		elt = ListItem()
		elt.val = val
		elt.next = self.head
		self.head = elt


	def addToTail(self, val):
		tmp = self.head
		while tmp != None and tmp.next != None:
			tmp = tmp.next
		
		elt = ListItem()
		elt.val = val
	
		if tmp == None:
			self.head = elt
		else:
			tmp.next = elt


	def printList(self):
		tmp = self.head
		while tmp != None:
			print tmp.val
			tmp = tmp.next

list = List()
list.add(1)
list.add(2)

list.addToTail(3)

list.printList()

