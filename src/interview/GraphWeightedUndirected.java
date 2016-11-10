package interview;

/*
 This class implements Djkstra, and Floyd Warshall algorithm as well
 as Prim's and Kruskal's algorithm to find the mst in a graph.
 
 Classes: Graph with adj matrix representation;
          Edge to store data of an edge
 
 Assumption(s): all input trees are connected
 
 Complexity(s):
    Djkstra: O(V^2)
    Floyd: O(V^3)
    Kruskal: O(ElogE)
    Prim: O(V^2)
 
 @HW
 */

import java.util.*;
public class GraphWeightedUndirected{
    
    final private int MAX = 9999;
    private int V;
    private int[][] adj;
    private boolean[] visited;
    public GraphWeightedUndirected(int v){
        V = v;
        adj = new int[v][v];
        for(int i = 0;i<v;i++){
            for(int j = 0;j<v;j++){
                adj[i][j] = MAX;
            }
        }
    }
    public GraphWeightedUndirected(int[][] m){
        V = m.length;
        adj = m;
    }
    //TODO randomely generate a graph
    public void addEdge(Edge e) {
        adj[e.v][e.w] = e.weight;
        adj[e.w][e.v] = e.weight;
    }
    public void djkstra(){
        int root = 0;
        int[] costs = new int[V];
        costs[root] = 0;
        visited = new boolean[V];
        visited[root] = true;
        int visitedcount = 1;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)->(e1.weight-e2.weight));
        for(int i = 0;i<V;i++){
            if(adj[root][i] !=MAX) {
                pq.add(new Edge(root, i, adj[root][i]));
            }
        }
        while(visitedcount!=V){
            Edge curredge = pq.poll();
            while(visited[curredge.w]){
                curredge = pq.poll();
            }
            // curredge.p();
            System.out.println("distance to "+curredge.w+" from root " + root+" is "+curredge.weight);
            int head = curredge.w;
            for(int i = 0;i<V;i++){
                if(adj[head][i]!=MAX) pq.add(new Edge(head, i, adj[head][i]+curredge.weight));
            }
            visited[head]=true;
            visitedcount++;
        }
        
        System.out.println("---------- djkstra completed ----------");
    }
    public void floyd(){
        int[][] dist = new int[V][V];
        for(int i = 0;i<V;i++){
            for(int j = 0;j<V;j++){
                dist[i][j] = adj[i][j];//initialize
            }
        }
        for(int i = 0;i<V;i++){
            for(int j = 0;j<V;j++){
                for(int k = 0;k<V;k++){
                    if(dist[j][k] >dist[j][i]+dist[i][k])
                        dist[j][k] = dist[j][i]+dist[i][k];
                }
            }
        }
        for(int i = 0;i<V;i++){
            System.out.println(Arrays.toString(dist[i]));
        }
        System.out.println("---------- floyd completed ----------");
    }
    private int[] id;//for krus
    public void krus(){
        visited = new boolean[V];
        id = new int[V];//for union find
        for(int i = 0;i<V;i++){
            id[i] = i;//initialize
        }
        int edgecount = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)->(e1.weight-e2.weight));
        for(int i = 0;i<V;i++){
            for(int j = i+1;j<V;j++){
                if(adj[i][j]!=MAX)
                    pq.add(new Edge(i, j, adj[i][j]));
            }
        }
        while(edgecount!=V-1){
            Edge curredge = pq.poll();
            while(isConnected(curredge.v, curredge.w)){
                curredge = pq.poll();
            }
            union(curredge.v, curredge.w);
            System.out.println("done union v:"+ curredge.v+" &w:" +curredge.w+" with weight: "+curredge.weight);
            edgecount++;
        }
        System.out.println("---------- krus completed ----------");
    }
    /*
     the following three methods are for un-ranked union-found
     */
    private boolean isConnected(int i, int j){
        return root(i)==root(j);
    }
    private void union(int i, int j){
        int rooti = root(i);
        int rootj = root(j);
        id[rooti] = rootj;
    }
    private int root(int i){
        while(i!=id[i]){
            //compressed
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    public void prim(){
        int root = 0;
        visited = new boolean[V];
        visited[root] = true;
        int visitedcount = 1;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)->(e1.weight-e2.weight));
        for(int i = 0;i<V;i++){
            if(adj[root][i] !=MAX) {
                pq.add(new Edge(root, i, adj[root][i]));
            }
        }
        while(visitedcount!=V){
            Edge curredge = pq.poll();
            while(visited[curredge.w]){
                curredge = pq.poll();
            }
            curredge.p();
            int head = curredge.w;
            for(int i = 0;i<V;i++){
                if(adj[head][i]!=MAX) pq.add(new Edge(head, i, adj[head][i]));
            }
            visited[head]=true;
            visitedcount++;
        }
        
        System.out.println("---------- prim completed ----------");
    }
}
