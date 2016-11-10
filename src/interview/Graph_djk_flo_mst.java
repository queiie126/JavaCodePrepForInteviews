package interview;
import java.util.*;
class Graph_djk_flo_mst{
    public static void main(String[] args){
  //  final
    //   djk();
        flo();
      //  mst();
        //none of djkstra, kruskal, prim or floydd need a argument
        //starting vetex (if needed) is 0
    }
    private static void mst(){
        GraphWeightedUndirected g2 = new GraphWeightedUndirected(5);
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
        GraphWeightedUndirected g = new GraphWeightedUndirected(9);
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
        int MAX = 9999;
        int graphm[][] = {
            {0,   5,  MAX, 10},
            {MAX, 0,   3, MAX},
            {MAX, MAX, 0,   1},
            {MAX, MAX, MAX, 0}
        };
        GraphWeightedUndirected g = new GraphWeightedUndirected(graphm);
        g.floyd();
    }
}

