package com.company;

public class Vertex {
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
        return "Vertex " + this.Text + " has an ID: " + this.Id;
    }
}
