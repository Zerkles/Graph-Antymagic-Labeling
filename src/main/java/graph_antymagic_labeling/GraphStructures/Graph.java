package graph_antymagic_labeling.GraphStructures;

import java.util.List;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}
