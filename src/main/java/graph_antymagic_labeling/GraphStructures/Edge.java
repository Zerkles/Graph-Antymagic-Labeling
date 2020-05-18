package graph_antymagic_labeling.GraphStructures;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class Edge {

    private Vertex v1;
    private Vertex v2;
    private int value;
    private IntVar solver_var;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(Vertex v1, Vertex v2, Model model) {
        this.v1 = v1;
        this.v2 = v2;

        this.solver_var = model.intVar(v1.getId() + "_" + v2.getId(), 0, 1000);
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public IntVar getSolverVar() {
        return this.solver_var;
    }

    public void setSolverVar(IntVar int_var) {
        this.solver_var = int_var;
    }

    public void updateValue() {
        this.value = this.solver_var.getValue();
    }

    @Override
    public String toString() {
        return "Edge between " + this.v1 + " and " + this.v2 + " with value " + this.value;
    }
}
