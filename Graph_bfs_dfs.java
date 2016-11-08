import java.util.*;

class Graph_bfs_dfs{
    public static class Graph{
        private int V;
        private LinkedList<Integer> adj[];
        private boolean[] vtd;
        public Graph(int v){
            V = v;
            adj = new LinkedList[v];
            vtd = new boolean[V];
            for(int i = 0;i<v;i++){
                adj[i] = new LinkedList();
                //for conveience, if the map is sparse,
            }
        }
        public void addEdge(int v,int w) {
            adj[v].add(w);
        }
        
        public void DFS(int s){
            if(vtd[s]) return;
            vtd[s] = true;
            System.out.print(s+" ");
            LinkedList<Integer> curr = adj[s];
            for(int  i: curr){
                DFS(i);
            }
        }
        //private void dfshelper() dfs can only called once,
        //a helper(to initiate vtd array) has to be set if more calls of it
        
        public void BFS(int s){
            boolean visited[] = new boolean[V];
            LinkedList<Integer> queue = new LinkedList<Integer>();
            visited[s]=true;
            queue.add(s);
            while (!queue.isEmpty()){
                s = queue.poll();
                System.out.print(s+" ");
                LinkedList<Integer> curr = adj[s];
                for(int  i: curr){
                    if(!visited[i]) {
                        visited[i]= true;
                        queue.offer(i);
                    }
                }
            }
            System.out.println();
        }
    }
    
    public static void main (String[] args){
        Graph g = new Graph(7);
        
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(3, 1);
        g.addEdge(3, 5);
        
        System.out.println("Graph BFS, starting from 1");
        
        g.BFS(3);
        g.BFS(1);
        g.BFS(6);
        
        System.out.println("Graph DFS, starting from 1");
        g.DFS(1);
        System.out.println();
    }
}

