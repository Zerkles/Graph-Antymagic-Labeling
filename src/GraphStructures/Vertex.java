package GraphStructures;

public class Vertex {
private String text;
private int id;

    public Vertex(int id, String name){
        this.id = id;
        this.text = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Vertex " + this.text + " has an ID: " + this.id;
    }
}
