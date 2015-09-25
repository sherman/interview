import math

def qSort(arr, left, right):
	index = partition(arr, left, right)

	if left < index - 1:
		qSort(arr, left, index - 1)
	if index < right:
		qSort(arr, index, right)

def partition(arr, left, right):
	mid = arr[(left + right) / 2]

	i = left
	j = right
	
	while i <= j:
		while arr[i] < mid:
			i = i + 1
		while arr[j] > mid:
			j = j - 1

		if i <= j:
			tmp = arr[i]
			arr[i] = arr[j]
			arr[j] = tmp
			i = i + 1
			j = j - 1
	
	return i


l = [3, 2, 6, 5, 1]
qSort(l, 0, 4)
print l
