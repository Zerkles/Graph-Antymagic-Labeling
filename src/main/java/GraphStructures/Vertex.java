package GraphStructures;


import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class Vertex {
    private int id;
    private String name;
    private IntVar solver_var;

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(int id, Model model) {
        this.id = id;

        this.solver_var = model.intVar(String.valueOf(this.id), 0, 1000);
    }

    public void setInitSolver_var(Model model) {
        this.solver_var = model.intVar(String.valueOf(this.id), 0, 1000);
    }

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public IntVar getSolverVar() {
        return this.solver_var;
    }

    public void setSolverVar(IntVar int_var) {
        this.solver_var = int_var;
    }

    public int getValue() {
        if (this.solver_var == null) {
            throw new NullPointerException("Vertex is not a part of any resolved graph!");
        }
        return this.solver_var.getValue();
    }

    @Override
    public String toString() {
        if (this.solver_var == null) {
            return "Vertex with: id " + this.id + ", named " + this.name + ", value " + "Null";
        }
        return "Vertex with: id " + this.id + ", named " + this.name + ", value " + this.getValue();
    }
}