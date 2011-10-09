def binSearch(arr, elt, min, max):
	if min > max:
		return -1

	middle = (min + max) / 2

	if arr[middle] > elt:
		return binSearch(arr, elt, min, middle - 1);
	if arr[middle] < elt:
		return binSearch(arr, elt, middle + 1, max);
	if arr[middle] == elt:
		return middle

print binSearch([1, 2, 4, 7], 3, 0, 3)
