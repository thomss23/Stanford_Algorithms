import heapq

#read tuples as (weight, vertex) to be sorted correctly when inserted into the heap as tuples
def read_graph_from_file(filename):
    graph = {}
    f = open(filename, "r")
    for line in f:
        arr_of_tuples = []
        line = line.split()
        vertex = int(line[0])
        edges_and_weights = line[1:]
        for edge_and_weight in edges_and_weights:
            split = edge_and_weight.split(',')
            tpl = tuple((int(split[1]), int(split[0])))
            arr_of_tuples.append(tpl)
        graph[vertex] = arr_of_tuples
    return graph

# Dijkstra's algorithm
# graph is a dictionary of vertices and their edges and weights
# returns an array of distances from the starting node to each node
def dijkstra(graph):
    s = 1 # starting node
    n = len(graph.keys())
    heap = []

    dist = (n + 1) * [float('inf')]
    vis = (n + 1) * [False]
    
    dist[s] = 0
    heapq.heapify(heap)
    heapq.heappush(heap, (0, s))

    while len(heap) != 0:
        minValueWeight, index = heapq.heappop(heap)
        vis[index] = True

        if dist[index] < minValueWeight: #ignoring stale node optimization
            continue

        for tpl in graph[index]:

            if vis[tpl[1]]: 
                continue

            newDistance = dist[index] + tpl[0]
            if newDistance < dist[tpl[1]]:
                dist[tpl[1]] = newDistance
                heapq.heappush(heap, (newDistance, tpl[1]))
    
    return dist


graph = read_graph_from_file("Course2/Week2/dijkData.txt")
distances = dijkstra(graph)
print(distances[7], distances[37], distances[59], distances[82], distances[99], distances[115], distances[133], distances[165], distances[188], distances[197])


