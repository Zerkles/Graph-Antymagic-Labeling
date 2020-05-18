package graph_antymagic_labeling;

import graph_antymagic_labeling.Graph.Graph;
import graph_antymagic_labeling.Graph.GraphUtilities;
import graph_antymagic_labeling.Solver.AntymagicLabelingSolver;

public class Main {

    public static void main(String[] args) {

        Graph g = GraphUtilities.read_from_file(System.getProperty("user.dir") + "/src/main/java/graph_antymagic_labeling/input_graph.txt");
        
        AntymagicLabelingSolver.solve_graph(g, true);

        System.out.println("Solved graph:");
        g.printGraph();

        GraphUtilities.write_to_file(g, "output_graph.txt");

    }
}