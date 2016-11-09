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
 
 @author HW
 */
import java.util.*;
class Graph_djk_flo_mst{
    final private static int MAX = 9999;
    public static class Graph{
        private int V;
        private int[][] adj;
        private boolean[] visited;
        public Graph(int v){
            V = v;
            adj = new int[v][v];
            for(int i = 0;i<v;i++){
                for(int j = 0;j<v;j++){
                    adj[i][j] = MAX;
                }
            }
        }
        public Graph(int[][] m){
            V = m.length;
            adj = m;
        }
        //TODO randomeless generate a graph
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
    public static class Edge{
        int v;//start
        int w;//end
        int weight;
        public Edge(int a, int b, int c){
            v = a;
            w = b;
            weight = c;
        }
        public void p(){
            System.out.println("starting v: "+v+"; ending: "+w+"; weight: "+weight);
        }
    }
    public static void main(String[] args){
     //   djk();
        flo();
      //  mst();
        //none of djkstra, kruskal, prim or floydd need a argument
        //starting vetex (if needed) is 0
    }
    private static void mst(){
        Graph g2 = new Graph(5);
        g2.addEdge(new Edge(0,1,2));
        g2.addEdge(new Edge(0,3,6));
        g2.addEdge(new Edge(1,2,3));
        g2.addEdge(new Edge(1,3,8));
        g2.addEdge(new Edge(1,4,5));
        g2.addEdge(new Edge(2,4,7));
        g2.addEdge(new Edge(3,4,9));
        g2.prim();
        g2.krus();
        //note that this 2 could yield different resulting tree, but the sum
        //of weight should be the same
        
     /*   Graph g = new Graph(9);
        g.addEdge(new Edge(0, 1, 4));
        g.addEdge(new Edge(0, 7, 8));
        g.addEdge(new Edge(1, 2, 8));
        g.addEdge(new Edge(1, 7, 11));
        g.addEdge(new Edge(2, 3, 7));
        g.addEdge(new Edge(2, 5, 4));
        g.addEdge(new Edge(2, 8, 2));
        g.addEdge(new Edge(3, 4, 9));
        g.addEdge(new Edge(3, 5, 14));
        g.addEdge(new Edge(4, 5, 10));
        g.addEdge(new Edge(5, 6, 2));
        g.addEdge(new Edge(6, 7, 1));
        g.addEdge(new Edge(6, 8, 6));
        g.addEdge(new Edge(7, 8, 7));
        g.krus();
        g.prim();*/
    }
    private static void djk(){
        Graph g = new Graph(9);
        g.addEdge(new Edge(0, 1, 4));
        g.addEdge(new Edge(0, 7, 8));
        g.addEdge(new Edge(1, 2, 8));
        g.addEdge(new Edge(1, 7, 11));
        g.addEdge(new Edge(2, 3, 7));
        g.addEdge(new Edge(2, 5, 4));
        g.addEdge(new Edge(2, 8, 2));
        g.addEdge(new Edge(3, 4, 9));
        g.addEdge(new Edge(3, 5, 14));
        g.addEdge(new Edge(4, 5, 10));
        g.addEdge(new Edge(5, 6, 2));
        g.addEdge(new Edge(6, 7, 1));
        g.addEdge(new Edge(6, 8, 6));
        g.addEdge(new Edge(7, 8, 7));
        g.djkstra();
    }
    private static void flo(){
        int graphm[][] = {
            {0,   5,  MAX, 10},
            {MAX, 0,   3, MAX},
            {MAX, MAX, 0,   1},
            {MAX, MAX, MAX, 0}
        };
        Graph g = new Graph(graphm);
        g.floyd();
    }
}

