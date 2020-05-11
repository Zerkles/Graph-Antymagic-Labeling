package GraphStructures;

public class Edge {

    private Vertex v1;
    private Vertex v2;
    private String text;

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV1(){
        return v1;
    }

    public Vertex getV2(){
        return v2;
    }

    @Override
    public String toString(){
        return "Edge connects vertices " + this.v1 + " and " + this.v2 + " with text " + this.text;
    }
}
// public class Edge {
//     public String Text;
//     public int Id;

//     public void setText(String text) {
//         Text = text;
//     }

//     public String getText() {
//         return Text;
//     }

//     public void setId(int id) {
//         Id = id;
//     }

//     public int getId() {
//         return Id;
//     }

//     @Override
//     public String toString(){
//         return "Edge " + this.Id + " connects vertices " + this.Text;
//     }
// }
