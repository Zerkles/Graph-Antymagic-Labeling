package GraphApp;

import GraphApp.model.*;
import GraphStructures.Edge;
import GraphStructures.Graph;
import GraphStructures.Vertex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Solver.AntymagicLabelingSolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField randomNodes = new TextField();
    @FXML
    private TextField randomDegree = new TextField();
    @FXML
    private Pane drawingPane = new Pane();
    @FXML
    private Canvas drawingCanvas = new Canvas();
    @FXML
    private TextField edgeStart = new TextField();
    @FXML
    private TextField edgeEnd = new TextField();

    int cellsId = 0;
    protected GraphDraw graphDraw = new GraphDraw();

    @FXML
    public void initialize() {

    }

    @FXML
    public void showGraphCanvas(ActionEvent actionEvent) throws IOException {
        cellsId = 0;
        Stage graphStage = new Stage();
        BorderPane root = new BorderPane();
        graphDraw = new GraphDraw();
        root.setCenter(graphDraw.getScrollPane());
        Scene scene = new Scene(root, 1024, 768);
        graphStage.setScene(scene);
        graphStage.show();
    }

    @FXML
    public void addEdge() {
        Model model = graphDraw.getModel();
        graphDraw.beginUpdate();
        Cell c1 = new Cell(edgeStart.getText());
        Cell c2 = new Cell(edgeEnd.getText());
        if (graphDraw.getModel().getAllEdges().isEmpty()) {
            model.addEdge(edgeStart.getText(), edgeEnd.getText());
            graphDraw.endUpdate();
            return;
        }
        if (c1.getCellId().equals(c2.getCellId())) {
            return;
        }
        for (GraphApp.model.Edge e : graphDraw.getModel().getAllEdges()) {
            if (e.getSource().getCellId().equals(c1.getCellId())) {
                if (e.getTarget().getCellId().equals(c2.getCellId())) {
                    return;
                }
            }
            if (e.getTarget().getCellId().equals(c1.getCellId())) {
                if (e.getSource().getCellId().equals(c2.getCellId())) {
                    return;
                }
            }
        }
        model.addEdge(edgeStart.getText(), edgeEnd.getText());
        graphDraw.endUpdate();
    }

    @FXML
    public void addCell() {
        if (graphDraw.getModel().getAllCells().size() > 0) {
            cellsId = graphDraw.getModel().getAllCells().size();
        }
        Model model = graphDraw.getModel();
        graphDraw.beginUpdate();
        model.addCell("V" + cellsId, CellType.RECTANGLE);
        cellsId++;
        graphDraw.endUpdate();
    }

    @FXML
    private void loadFromFile() {
        Stage thirdStage = new Stage();
        thirdStage.setTitle("Load graph");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(thirdStage);
        Graph graph = GraphUtilities.read_from_file(file.getAbsolutePath());
        Stage graphStage = new Stage();
        BorderPane root = new BorderPane();
        graphDraw = mapGraphToGraphDraw(graph);
        root.setCenter(graphDraw.getScrollPane());
        Scene scene = new Scene(root, 1024, 768);
        graphStage.setScene(scene);
        graphStage.show();
        Layout layout = new RandomLayout(graphDraw);
        layout.execute();
    }

    @FXML
    private void saveToFile() {
        Graph graph = mapGraphDrawToGraph(graphDraw);
        Stage thirdStage = new Stage();
        thirdStage.setTitle("Save graph");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("output_graph.txt");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(thirdStage);
        GraphUtilities.write_to_file(graph, file.getAbsolutePath());
    }

    private Graph mapGraphDrawToGraph(GraphDraw toMapGraph) {
        Graph graph = new Graph();
        int i = 0;
        List<Edge> edgesListToAdd = new ArrayList<>();
        List<Vertex> vertexListToAdd = new ArrayList<>();
        for (Cell c : toMapGraph.getModel().getAllCells()) {
            vertexListToAdd.add(new Vertex(i, c.getCellId()));
            i++;
        }
        graph.setVertices(vertexListToAdd);

        for (GraphApp.model.Edge e : toMapGraph.getModel().getAllEdges()) {
            Vertex sourceVertex = null;
            Vertex targetVertex = null;

            for (Vertex v : graph.getVertices()) {
                if (e.getSource().getCellId().equals(v.getName())) {
                    sourceVertex = v;
                }
                if (e.getTarget().getCellId().equals(v.getName())) {
                    targetVertex = v;
                }
            }
            edgesListToAdd.add(new Edge(sourceVertex, targetVertex));
        }
        graph.setEdges(edgesListToAdd);
        return graph;
    }

    private GraphDraw mapGraphToGraphDraw(Graph graph) {
        List<Vertex> vertexList = graph.getVertices();
        List<Edge> edgeList = graph.getEdges();
        GraphDraw mappedGraph = new GraphDraw();
        Model model = mappedGraph.getModel();
        mappedGraph.beginUpdate();
        for (Vertex v : vertexList) {
            model.addCell(v.getName(), CellType.RECTANGLE);
        }
        for (Edge e : edgeList) {
            model.addEdge(e.getV1().getName(), e.getV2().getName());
        }
        mappedGraph.endUpdate();
        return mappedGraph;
    }

    private void labeling(Graph g, boolean hard_labeling) {
        g = AntymagicLabelingSolver.solve_graph(g, hard_labeling);

        for (Vertex v : g.getVertices()) {
            System.out.println("Vertex: " + v.getSolverVar());

            for (Cell c : graphDraw.getModel().getAllCells()) {
                if (c.getCellId().equals(v.getName())) {
                    c.setSolveLabel(Integer.toString(v.getSolverVar().getValue()));
                    c.setView(c.getView());
                }
            }
        }

        for (Edge e : g.getEdges()) {
            System.out.println("Edge: " + e.getSolverVar());
            Cell sourceCell = new Cell("");
            Cell targetCell = new Cell("");

            for (Cell c : graphDraw.getModel().getAllCells()) {
                if (e.getV1().getName().equals(c.getCellId())) {
                    sourceCell = new Cell(c.getCellId());
                }
                if (e.getV2().getName().equals(c.getCellId())) {
                    targetCell = new Cell(c.getCellId());
                }
            }

            for (GraphApp.model.Edge drawEdge : graphDraw.getModel().getAllEdges()) {
                if ((drawEdge.getSource().getCellId().equals(e.getV1().getName()) && drawEdge.getTarget().getCellId().equals(e.getV2().getName())) ||
                        (drawEdge.getTarget().getCellId().equals(e.getV1().getName()) && drawEdge.getSource().getCellId().equals(e.getV2().getName()))) {
                    drawEdge.setText(Integer.toString(e.getSolverVar().getValue()));
                    drawEdge.relocateText();
                }
            }
        }
    }

    @FXML
    private void hardSolving() {
        Graph g = mapGraphDrawToGraph(graphDraw);
        labeling(g, true);
    }

    @FXML
    private void softSolving() {
        Graph g = mapGraphDrawToGraph(graphDraw);
        labeling(g, false);
    }
}

