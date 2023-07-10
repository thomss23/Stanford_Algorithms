import random
import copy

def min_cut(graph):
    num_vertices = len(graph)
    while num_vertices > 2:
        u = random.choice(list(graph.keys()))
        v = random.choice(graph[u])

        graph[u].extend(graph[v])

        for vertex in graph[v]:
            graph[vertex].remove(v)
            graph[vertex].append(u)
        
        # remove self loops
        graph[u] = [vertex for vertex in graph[u] if vertex != u]

        del graph[v]
        num_vertices -= 1
    
    return len(graph[list(graph.keys())[0]])

            


def read_graph_from_file(filename):
    graph = {}
    f = open(filename, "r")
    for line in f:
        line = line.split()
        vertex = int(line[0])
        edges = line[1:]
        edges = [int(x) for x in edges]
        graph[vertex] = edges
    return graph

graph = read_graph_from_file("Divide and Conquer, Sorting and Searching, and Randomized Algorithms/mincut.txt")

graph_copy = copy.deepcopy(graph)

min_cut_result = min_cut(graph_copy)
min = min_cut_result

for i in range(1, 100):
    graph_copy = copy.deepcopy(graph)
    min_cut_result = min_cut(graph_copy)
    if min_cut_result < min:
        min = min_cut_result

print("Mincut of this graph is: " + str(min))

