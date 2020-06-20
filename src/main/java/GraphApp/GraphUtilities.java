package GraphApp;

import GraphStructures.Edge;
import GraphStructures.Graph;
import GraphStructures.Vertex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphUtilities {
    public static Graph read_from_file(String file_path) {

        Graph g = new Graph();
        List<Vertex> list_of_verticies = new ArrayList<>();
        List<Edge> list_of_edges = new ArrayList<>();

        try {
            File myObj = new File(file_path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String vertex_line = myReader.nextLine();

                if (vertex_line.equals("#")) {
                    while (myReader.hasNextLine()) {
                        String edge_line = myReader.nextLine();

                        int id1 = Integer.parseInt(edge_line.substring(0, edge_line.indexOf(" ")));
                        int id2 = Integer.parseInt(edge_line.substring(edge_line.indexOf(" ") + 1, edge_line.length()));
                        Vertex v1 = null, v2 = null;

                        for (Vertex v : list_of_verticies) {
                            if (v.getId() == id1) {
                                v1 = v;
                            }
                            if (v.getId() == id2) {
                                v2 = v;
                            }
                        }

                        list_of_edges.add(new Edge(v1, v2));
                    }
                    break;
                }

                int id = Integer.parseInt(vertex_line.substring(0, vertex_line.indexOf(" ")));
                String name = vertex_line.substring(vertex_line.indexOf(" ") + 1, vertex_line.length());

                list_of_verticies.add(new Vertex(id, name));
            }
            myReader.close();

            g.setEdges(list_of_edges);
            g.setVertices(list_of_verticies);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return g;
    }

    public static void write_to_file(Graph g, String file_path) {
        try {
            FileWriter myWriter = new FileWriter(file_path);

            for (Vertex v : g.getVertices()) {
                myWriter.write(v.getId() + " " + v.getName() + "\n");
            }

            myWriter.write("#\n");

            for (Edge e : g.getEdges()) {
                if (g.getEdges().get(g.getEdges().size() - 1) == e) {
                    myWriter.write(e.getV1().getId() + " " + e.getV2().getId());
                } else {
                    myWriter.write(e.getV1().getId() + " " + e.getV2().getId() + "\n");
                }

            }


            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
