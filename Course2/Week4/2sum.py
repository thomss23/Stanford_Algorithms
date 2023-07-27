import threading

def read_from_file(filename):
    f = open(filename, "r")
    input = []
    for line in f:
        input.append(int(line))
    return input

def two_sum(input):
    hash_set = set(input)
    count = 0 

    for t in range(-10000, 10001):
        for element in input:
            if t - element in hash_set:
                count += 1
                break
    return count


input = read_from_file("Week4/input.txt")

count = two_sum(input)

print(count)

