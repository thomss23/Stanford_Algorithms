import math

def read_cities_from_file(fileName) :
    
    cityList = []

    with open(fileName) as f:
        for line in f:
            line = line.split()
            if len(line) == 1:
                continue
            else:
                cityList.append((float(line[0]), float(line[1])))
    return cityList

def calculateEuclidianDistance(x,y,z,w):
    return math.sqrt((x-z)**2 + (y-w)**2)

def calculateDistanceBetweenCities(city1, city2):
    return calculateEuclidianDistance(city1[0], city1[1], city2[0], city2[1])

def createGraph(city_list):
    num_cities = len(city_list)
    graph = [[0] * num_cities for _ in range(num_cities)]

    for i in range(num_cities):
        for j in range(num_cities):
            if i != j:
                graph[i][j] = calculateEuclidianDistance(*city_list[i], *city_list[j])

    return graph

def tsp(graph, start):
    num_cities = len(graph)
    all_visited = (1 << num_cities) - 1
    memo = [[None] * num_cities for _ in range(1 << num_cities)]

    def tsp_helper(curr_city, visited):
        if memo[visited][curr_city] is not None:
            return memo[visited][curr_city]

        if visited == all_visited:
            return graph[curr_city][start]

        min_cost = float('inf')
        for next_city in range(num_cities):
            if visited & (1 << next_city) == 0:
                min_cost = min(min_cost, graph[curr_city][next_city] + tsp_helper(next_city, visited | (1 << next_city)))

        memo[visited][curr_city] = min_cost
        return min_cost

    return tsp_helper(start, 1 << start)

cities = read_cities_from_file('tsp')
graph = createGraph(cities)

print(tsp(graph, 0))


