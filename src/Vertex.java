public class Vertex {
private String text;
private int id;

    Vertex(int id, String name){
        this.id = id;
        this.text = name;
    }

    public void setText(String text) {
        text = text;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Vertex " + this.text + " has an ID: " + this.id;
    }
}
