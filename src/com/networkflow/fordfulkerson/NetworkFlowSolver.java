package com.networkflow.fordfulkerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

class NetworkFlowSolver {
    static final int INF = Integer.MAX_VALUE / 2;
    static final int EMPTY_FLOW = 0;

    final int source;
    final int target;

    int visitedToken = 1;
    int[] visited;

    int maxFlow;

    final Graph graph;
    List<Edge> augmentedPaths;

    public NetworkFlowSolver(Graph graph, int source, int target) {
        this.source = source;
        this.target = target;
        this.graph = graph;
        visited = new int[graph.getNodeCount()];
        augmentedPaths = new ArrayList<>();
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Edge> getAugmentedPaths() {
        if (augmentedPaths.size() <= 0) {
            calculateMaxFlow();
        }
        Collections.reverse(augmentedPaths);
        return augmentedPaths;
    }

    public int calculateMaxFlow() {
        for (int f = dfs(source, INF); f != 0; f = dfs(source, INF)) {
            printAugmentedPath();
            augmentedPaths.clear();
            visitedToken++;
            maxFlow += f;
        }
        return maxFlow;
    }

    private int dfs(int node, int flow) {
        if (node == target) return flow;

        visited[node] = visitedToken;

        List<Edge> edges = graph.getData()[node];
        for (Edge edge : edges) {
            if (edge.getRemainingCapacity() > 0 && visited[edge.getTo()] != visitedToken) {
                int bottleNeck = dfs(edge.getTo()
                        , min(flow, edge.getRemainingCapacity()));

                if (bottleNeck > 0) {
//                    System.out.printf("Edge: (%d, %d) Flow: %d Capacity: %d Bottleneck: %d\n", edge.getFrom(), edge.getTo(), edge.getFlow(), edge.getCapacity(), bottleNeck);
                    edge.augment(bottleNeck);
                    augmentedPaths.add(edge);
                    return bottleNeck;
                }
            }
        }
        return EMPTY_FLOW;
    }

    public void printAugmentedPath() {
        System.out.println("======================");
        System.out.println("Augmented Path: " + visitedToken);
        System.out.println("======================");
        Collections.reverse(augmentedPaths);
        for (Edge edge :
                augmentedPaths) {
            System.out.printf("Edge: (%d, %d) Flow: %d Capacity: %d Bottleneck: %d\n", edge.getFrom(), edge.getTo(), edge.getFlow(), edge.getCapacity(), edge.getBottleNeck());
        }
        System.out.println("======================\n");
    }
}