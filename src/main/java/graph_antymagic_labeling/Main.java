package graph_antymagic_labeling;

import graph_antymagic_labeling.GraphStructures.Graph;

public class Main {

    public static void main(String[] args) {

        Graph g = GraphUtilities.read_from_file(
                System.getProperty("user.dir") + "/src/main/java/graph_antymagic_labeling/input_graph.txt");
        GraphUtilities.write_to_file(g, "output_graph.txt");

    }
}