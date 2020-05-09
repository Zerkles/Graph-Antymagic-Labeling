package com.company;

public class Edge {
    public String Text;
    public int Id;

    public void setText(String text) {
        Text = text;
    }

    public String getText() {
        return Text;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString(){
        return "Edge " + this.Id + " connects vertices " + this.Text;
    }
}
