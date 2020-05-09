package com.company;

public class Edge {
    public Vertex v1;
    public Vertex v2;
    public int weight;

    Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }
    @Override
    public String toString(){
        return "Edge connects vertices " + this.v1 + " and " + this.v2 + ". Its weight is: " + this.weight + ".";
    }
}
