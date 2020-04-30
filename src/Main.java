
public class Main {

    public static void main(String[] args){

        Graph g = GraphUtilities.read_from_file("/home/zerkles/Documents/SemestrVI/Graph-Antymagic-Labeling/src/graph.txt");
        GraphUtilities.write_to_file(g, "dupa.txt");
    }
}