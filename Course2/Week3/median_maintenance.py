import heapq

def read_input(filename):
    f = open(filename, "r")
    input = []
    for line in f:
        input.append(int(line))
    return input

def median_maintenance(input):
    h_low = [] #extract-max
    h_high = [] #extract-min

    heapq.heapify(h_low)
    heapq.heapify(h_high)

    medians = []
    for element in input:
        if len(h_low) == 0:
             heapq.heappush(h_low, -element)
        elif element < -h_low[0]:
            heapq.heappush(h_low, -element)
        else:
            heapq.heappush(h_high, element)
        #rebalancing step
        if len(h_low) - len(h_high) > 1:
            heapq.heappush(h_high, -heapq.heappop(h_low))
        elif len(h_high) - len(h_low) > 1:
            heapq.heappush(h_low, -heapq.heappop(h_high))
        
        if len(h_low) > len(h_high):
            medians.append(-h_low[0])
        elif len(h_high) > len(h_low):
            medians.append(h_high[0])
        else:
            medians.append(-h_low[0])
            
    sum = 0
    for median in medians:
        sum += median

    return sum % 10000

input = read_input("Course2\Week3\median.txt")

print(median_maintenance(input))
