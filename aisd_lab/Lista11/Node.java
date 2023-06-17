package Lista11;

public class Node {
    //dlugosc krawedzi i koncowy wezel
    private int vertex;
    private int edge;

    public Node(int vertex, int edge){
        this.edge = edge;
        this.vertex = vertex;
    }

    public int getVertex(){
        return vertex;
    }

    public int getEdge(){
        return edge;
    }
}
