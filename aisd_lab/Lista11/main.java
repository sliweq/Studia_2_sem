package Lista11;

public class main {
    public static Graph g = new Graph(5);
    
    public static void createGraph(){
        g.addNode(0, 1, 10);
        g.addNode(0, 3, 30);
        g.addNode(0, 4, 100);

        g.addNode(1, 2, 50);

        g.addNode(2, 4, 10);

        g.addNode(3, 2, 20);
        g.addNode(3, 4, 60);
    }

    public static void main(String[] args) {
        createGraph();
        g.showCities();
        System.out.println("-----------------------------------------");
        g.dijkstra();
        System.out.println("-----------------------------------------");
        g.dfs();
    }
    
}
