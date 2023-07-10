import sys
import threading
from collections import deque

def read_graph_from_file(filename):
    graph = {}
    with open(filename, "r") as f:
        for line in f:
            line = line.split()
            vertex = int(line[0])
            end_vertex = int(line[1])
            if vertex in graph:
                graph[vertex].append(end_vertex)
            else:
                graph[vertex] = [end_vertex]
    return graph


def transpose(graph):
    transposed_graph = {}

    for vertex in graph:
        for edge in graph[vertex]:
            if edge in transposed_graph:
                transposed_graph[edge].append(vertex)
            else:
                transposed_graph[edge] = [vertex]
    return transposed_graph

def kosoraju(graph):
    
    visited = set()
    stack = deque()
    result = []

    for vertex in graph:
        if vertex not in visited:
            dfs1(graph, visited, vertex, stack)

    transposed_graph = transpose(graph)
    visited  = set()
    while len(stack) > 0:
        top = stack.pop()
        if top not in visited:
            count = [0]
            dfs2(transposed_graph, top, visited, count)
            result.append(count[0])
    
    result.sort(reverse=True)
    return result[:5]

def dfs1(graph, visited, start, stack):
    visited.add(start)

    if start in graph:
        for edge in graph[start]:
            if edge not in visited:
                dfs1(graph, visited, edge, stack)
    
    stack.append(start)

def dfs2(graph, start, visited, count):
    visited.add(start)
    count[0] += 1

    if start in graph:
        for edge in graph[start]:
            if edge not in visited:
                dfs2(graph, edge, visited, count)

def main():
    sys.setrecursionlimit(800000)
    threading.stack_size(67108864)
    thread = threading.Thread(target=main_thread)
    thread.start()

def main_thread():
    graph = read_graph_from_file("/Users/thomasborbeli/Documents/Programming/Algorithms - Tim Roughgarden/Course2/Week1/examinput_correct.txt")
    result = kosoraju(graph)
    print(result)

if __name__ == "__main__":
    main()
