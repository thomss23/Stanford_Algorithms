import math


def read_cities_from_file(fileName):
    cityList = []

    with open(fileName) as f:
        for line in f:
            line = line.split()
            if len(line) == 1:
                continue
            else:
                cityList.append((float(line[1]), float(line[2])))
    return cityList


def calculateEuclidianDistance(x, y, z, w):
    return math.sqrt((x - z) ** 2 + (y - w) ** 2)


def calculateDistanceBetweenCities(city1, city2):
    return calculateEuclidianDistance(city1[0], city1[1], city2[0], city2[1])


def solve_tsp_with_heuristic(city_list, start_city=0):
    num_cities = len(city_list)
    visited = [0] * num_cities
    total_tour_distance = 0
    tour = []
    visited[start_city] = 1
    current_city = start_city

    while len(tour) < num_cities:
        min_distance = math.inf
        min_distance_city = -1
        all_visited = True

        for neighbor_city in range(num_cities):
            if visited[neighbor_city] == 0:
                all_visited = False
                distance = calculateDistanceBetweenCities(city_list[current_city], city_list[neighbor_city])
                if distance < min_distance:
                    min_distance = distance
                    min_distance_city = neighbor_city

        if all_visited:
            break

        total_tour_distance += min_distance
        tour.append(city_list[min_distance_city])
        visited[min_distance_city] = 1
        current_city = min_distance_city

    # Return to the starting city
    total_tour_distance += calculateDistanceBetweenCities(city_list[current_city], city_list[start_city])
    tour.append(city_list[start_city])

    return total_tour_distance, tour


city_list = read_cities_from_file('nn.txt')
total_distance, tour = solve_tsp_with_heuristic(city_list)
if math.isinf(total_distance):
    print("Error: No valid tour found.")
else:
    print("Total Tour Distance:", total_distance)
    print("Tour:", tour)
