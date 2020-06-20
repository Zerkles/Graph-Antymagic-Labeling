package Solver;
import GraphStructures.*;
import GraphApp.GraphUtilities;

public class AntymagicLabelingSolverUsageExample {

    public static void main(String[] args) {
        Graph g = GraphUtilities
                .read_from_file("/home/zerkles/Documents/SemestrVI/Graph-Antymagic-Labeling/src/input_graph.txt");
        g.printGraph();
        AntymagicLabelingSolver.solve_graph(g, true);
        g.printGraph();
        GraphUtilities.write_to_file(g,
                "/home/zerkles/Documents/SemestrVI/Graph-Antymagic-Labeling/src/output_graph.txt");
    }
}