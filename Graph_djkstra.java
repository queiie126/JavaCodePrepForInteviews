import java.util.*;
class Graph_djkstra{
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
        //TODO randomeless generate a graph?
        public void addEdge(Edge e) {
            adj[e.v][e.w] = e.weight;
            adj[e.w][e.v] = e.weight;
        }
        public void minpath(int root){
            visited = new boolean[V];
            visited[root] = true;//assume input graph is connectecd
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
            
            System.out.println("completed");
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
            System.out.println("starting v: "+v+"; ending: "+w+"; weight"+weight);
        }
    }
    public static void main(String[] args){
        Graph g = new Graph(5);
        g.addEdge(new Edge(0, 1, 9));
        g.addEdge(new Edge(2, 3, 8));
        g.addEdge(new Edge(3, 4, 2));
        g.addEdge(new Edge(0, 2, 1));
        g.addEdge(new Edge(0, 3, 2));
        g.minpath(0);
    }
}

