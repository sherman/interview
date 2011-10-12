class ListItem:
	next = None
	val = 0

class Stack:
	top = None

	def push(self, val):
		elt = ListItem()
		elt.val = val
		elt.next = self.top
		self.top = elt

	def pop(self):
		if self.top != None:
			tmp = self.top
			self.top = tmp.next
			return tmp
		else:
			return None


stack = Stack()
stack.push(1)
print stack.pop().val
print stack.pop()

stack.push(2)
stack.push(3)

print stack.pop().val
print stack.pop().val
