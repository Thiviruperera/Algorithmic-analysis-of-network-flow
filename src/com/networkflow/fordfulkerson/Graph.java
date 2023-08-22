package com.networkflow.fordfulkerson;

import java.util.ArrayList;
import java.util.List;

class Graph {
    private int nodeCount;
    private List<Edge>[] data;

    @SuppressWarnings("unchecked")
    public Graph(int nodeCount) {
        setNodeCount(nodeCount);
        setData(new List[getNodeCount()]);
        for (int i = 0; i < nodeCount; ++i) {
            getData()[i] = new ArrayList<Edge>();
        }
    }

    public void insertEdge(Edge edge) {
        Edge tempEdge1 = new Edge(edge.getFrom(), edge.getTo(), edge.getCapacity());
        Edge tempEdge2 = new Edge(edge.getTo(), edge.getFrom(), 0);
        tempEdge1.setResidualEdge(tempEdge2);
        tempEdge2.setResidualEdge(tempEdge1);
        getData()[edge.getFrom()].add(tempEdge1);
        getData()[edge.getTo()].add(tempEdge2);
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int iNodeCount) {
        this.nodeCount = iNodeCount;
    }

    public List<Edge>[] getData() {
        return data;
    }

    public void setData(List<Edge>[] data) {
        this.data = data;
    }
}