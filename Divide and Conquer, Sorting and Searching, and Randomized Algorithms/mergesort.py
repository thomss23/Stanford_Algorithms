def merge3(a, b):
    c = []
    while len(a) > 0 and len(b) > 0:
        if a[0] < b[0]:
            c.append(a[0])
            a.pop(0)
        else:
            c.append(b[0])
            b.pop(0)
    if len(a) == 0:
        c.extend(b)
    else:
        c.extend(a)
    return c

def merge2(a, b):
    c = []
    na = len(a)
    nb = len(b)
    i = 0
    j = 0

    while i < na and j < nb:
        if a[i] < b[j]:
            c.append(a[i])
            i += 1
        else:
            c.append(b[j])
            j += 1
    while i < na:
        c.append(a[i])
        i += 1  
    
    while j < nb:
        c.append(b[j])
        j += 1

    return c

def mergeSort(arr):
	if len(arr) > 1:

		# Finding the mid of the array
		mid = len(arr)//2

		# Dividing the array elements
		L = arr[:mid]

		# Into 2 halves
		R = arr[mid:]

		# Sorting the first half
		mergeSort(L)

		# Sorting the second half
		mergeSort(R)
                
		merge(arr, L, R)

def merge(arr, L, R):
    i = j = k = 0
    while i < len(L) and j < len(R):
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

	# Checking if any element was left
    while i < len(L):
     arr[k] = L[i]
     i += 1
     k += 1

    while j < len(R):
     arr[k] = R[j]
     j += 1
     k += 1


# Code to print the list
def printList(arr):
	for i in range(len(arr)):
		print(arr[i], end=" ")
	print()


# Driver Code
if __name__ == '__main__':
	arr = [12, 11, 13, 5, 6, 7]
	print("Given array is")
	printList(arr)
	mergeSort(arr)
	print("\nSorted array is ")
	printList(arr)
