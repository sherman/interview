def mergeSortedLists(list1, list2):
	l1 = len(list1)
	l2 = len(list2)
	i = 0;
	j = 0;
	k = 0;

	finalList = [None] * (l1 + l2)

	while l1 > i and l2 > j:
		if list1[i] < list2[j]:
			finalList[k] = list1[i]
			k = k + 1
			i = i + 1
		elif list1[i] > list2[j]:
			finalList[k] = list2[j]
			k = k + 1
			j = j + 1
		elif list1[i] == list2[j]:
			finalList[k] = list1[i]
			k = k + 1
			i = i + 1
			finalList[k] = list2[j]
			k = k + 1
			j = j + 1
	

	while l1 > i:
		finalList[k] = list1[i]
		k = k + 1
		i = i + 1

	while l2 > j:
		finalList[k] = list2[j]
		k = k + 1
		j = j + 1


	return finalList
	
	
print mergeSortedLists([1, 10, 11, 233], [1, 4, 5])
