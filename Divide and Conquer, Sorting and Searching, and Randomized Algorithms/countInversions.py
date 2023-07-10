def countInversions(A):
    n = len(A)
    if n == 1:
        return A, 0
    else:
        B, x = countInversions(A[:n//2])
        C, y = countInversions(A[n//2:])
        D, z = mergeAndCountSplitInv(B, C, n)
        return D, x + y + z

def mergeAndCountSplitInv(B, C, n):
    D = []
    i = j = 0
    count = 0
    for k in range(n):
        if i < len(B) and j < len(C):
            if B[i] <= C[j]:
                D.append(B[i])
                i += 1
            else:
                D.append(C[j])
                j += 1
                count += len(B) - i
        elif i < len(B):
            D.append(B[i])
            i += 1
        else:
            D.append(C[j])
            j += 1
    return D, count

sortedArray, numberOfInversions = countInversions([1, 20, 6, 4, 5])
print("Number of inversions: " + str(numberOfInversions))

f = open("Divide and Conquer, Sorting and Searching, and Randomized Algorithms/countInversions.txt", "r")
input = []
for line in f:
    input.append(int(line))

sortedArray, numberOfInversions = countInversions(input)
print("Number of inversions: " + str(numberOfInversions))