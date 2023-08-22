package com.networkflow.fordfulkerson;

class Edge {
    private int from;
    private int to;
    private int capacity;
    private int flow;
    private Edge residualEdge;
    private int bottleNeck;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
        this.residualEdge = null;
        this.bottleNeck = 0;
    }

    public Edge(int edgeData[]) {
        this.from = edgeData[0];
        this.to = edgeData[1];
        this.capacity = edgeData[2];
        this.flow = 0;
        this.residualEdge = null;
    }

    void setFrom(int from) {
        this.from = from;
    }

    void setTo(int to) {
        this.to = to;
    }

    void setResidualEdge(Edge e) {
        residualEdge = e;
    }

    public boolean isResidual() {
        return capacity == 0;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public void setResidual(Edge residual) {
        residualEdge = residual;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingCapacity() {
        return capacity - flow;
    }

    public int getFlow() {
        return flow;
    }

    public int getBottleNeck() {
        return bottleNeck;
    }

    public void augment(int bottleNeck) {
        this.bottleNeck = bottleNeck;
        flow = flow + this.bottleNeck;
        residualEdge.flow = residualEdge.flow - this.bottleNeck;
    }
}
