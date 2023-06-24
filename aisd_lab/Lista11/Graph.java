package Lista11;

import java.util.LinkedList;

public class Graph{
    private LinkedList<Node>[] graph;
    private String[] cities = {"Wrocław","Oława","Brzeg","Nysa","Opole"};
    private int[] colors = new int[5];

    public Graph(int amountOfNodes){
        graph = new LinkedList[amountOfNodes];
        for(int x = 0; x< amountOfNodes; x++){
            graph[x] = new LinkedList<>();
        }
    }

    public void addNode(int start, int destination, int lenght ){
        graph[start].add(new Node(destination,lenght));
    }
    
    public void showCities(){
        for(int x = 0; x < graph.length; x++){
            System.out.println("Drogi z miasta " + cities[x]+":");
            for(Node node: graph[x]){
                System.out.println("-Miasto: " + cities[node.getVertex()] + " Odległość: " + node.getEdge());
            }
        }   
    }

    public void dijkstra(){
        int distances[] = new int[graph.length];
        boolean visited[] = new boolean[graph.length];
        for(int i = 0; i <graph.length; i++ ){
            visited[i]= false;
            distances[i] = Integer.MAX_VALUE;
        }

        int city = 0;

        for(Node node: graph[city]){
            distances[node.getVertex()] = node.getEdge();    
        }
        distances[city] = 0;
        visited[city] = true;

        for(int x = 0; x < graph.length ;x++){
            int next = getMinDistance(distances, visited);
            if(next == -1){
                break;
            }
            visited[next] = true;

            for(Node node: graph[next]){
                if(distances[next] + node.getEdge() < distances[node.getVertex()]){
                    distances[node.getVertex()] = distances[next] + node.getEdge();
                }
            }
        } 
        System.out.println("Najkrótsze drogi z " + cities[city] + " do:");
        for(int x = 0; x < graph.length; x++){
            System.out.println(cities[x] + " wynosi: " + distances[x]);
        }   
    }



    public void dfs(){
        System.out.println("Przeszukiwanie DFS:");
        for(int x = 0; x < colors.length; x++){
            colors[x] = 0;
        }
        for(int i = 0; i < colors.length; i++){
            if(colors[i] == 0){
                dfs(i);
            }
        }
    }

    private void dfs(int i){
        System.out.println("Odwiedzono miasto: " + cities[i]);
        colors[i] = 1;
        for(Node node: graph[i]){
            if(colors[node.getVertex()] == 0){
                dfs(node.getVertex());
            }
        }
        colors[i] = 2;
    }

    public int getMinDistance(int[] distances, boolean[] visited){
        int tmp = Integer.MAX_VALUE;
        int index = -1;

        for(int x = 0; x<distances.length; x++){
            if(distances[x]<tmp && !visited[x] ){
                tmp = distances[x];
                index = x;
            }
        }
        return index; 
    }
}
