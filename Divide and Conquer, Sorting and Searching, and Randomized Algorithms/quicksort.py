def swap(A, i, j):
    tmp = A[i]
    A[i] = A[j]
    A[j] = tmp

def quicksort(A, l, r, count):
    if l >= r:
        return
    
    # selectedPivot = choosePivotAsFirstElement(A)
    # selectedPivot = choosePivotAsMedianOfThree(A,l,r)
    # selectedPivot = r 
    count[0] += (r - l)

    # swap(A, l, selectedPivot) # swap only if not already first element, comment if using choosePivotAsFirstElement
    newlyPlacedPivotPosition = partition(A, l, r)

    quicksort(A, l, newlyPlacedPivotPosition - 1, count)
    quicksort(A, newlyPlacedPivotPosition + 1, r, count)


def partition(A, l, r):
    pivot = A[l]
    i = l + 1
    for j in range(l + 1, r + 1):
        if A[j] < pivot:
            swap(A, i, j)
            i += 1
    swap(A, l, i - 1)
    return i - 1

def choosePivotAsFirstElement(A):
    return 0

def choosePivotAsMedianOfThree(A, l, r):
    if r - l < 2:
        return l
    mid = (l + r) // 2
    if A[l] < A[mid]:
        if A[mid] < A[r]:
            return mid
        elif A[l] < A[r]:
            return r
        else:
            return l
    else:
        if A[l] < A[r]:
            return l
        elif A[mid] < A[r]:
            return r
        else:
            return mid



f = open("Divide and Conquer, Sorting and Searching, and Randomized Algorithms/quicksort.txt", "r")
input = []
for line in f:
    input.append(int(line))
count = [0]
quicksort(input, 0, len(input) - 1, count)
print(count[0])
