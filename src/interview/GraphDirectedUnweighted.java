package interview;

/*
 This class implements BFS and DFS witha graph represented by adj list
 
 Complexity(s):
 Both are O(V);
 
 @HW
 */

import java.util.*;
public class GraphDirectedUnweighted{
    private int V;
    private LinkedList<Integer> adj[];
    private boolean[] visited;
    public GraphDirectedUnweighted(int v){
        V = v;
        adj = new LinkedList[v];
        visited = new boolean[V];
        for(int i = 0;i<v;i++){
            adj[i] = new LinkedList();
            //for conveience, if the map is sparse,
        }
    }
    public void addEdge(int v,int w) {
        adj[v].add(w);
    }
    
    public void DFS(int s){
        
        visited = new boolean[V];
        for(int i = s;i<V;i++){
            DFShelper(i);
        }
        for(int i = 0;i<s;i++){
            DFShelper(i);
        }
        
        System.out.println("\n---------- completed DFS(for whole graph) starting at "+s+" ----------");
    }
    private void DFShelper(int s) {
        if(visited[s]) return;
        visited[s] = true;
        System.out.print(s+" ");
        LinkedList<Integer> curr = adj[s];
        for(int  i: curr){
            DFShelper(i);
        }
    }
    
    public void BFS(int s){
        int root = s;
        visited= new boolean[V];
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
        System.out.println("\n---------- completed BFS(for connected part) starting at "+root+" ----------");
    }
    public void kahntop(){
        //sort each vertex according to its income edge
        //5, 4, 2, 3, 0, 1
        //pop first
        //for each vertex connected to it minus 1// use get and put
        
        //0 = vertex, 1 = income edge sort by [1]
        int[] count = new int[V];
        for(int i = 0;i<V;i++){
            LinkedList<Integer> curr = adj[i];
            for(int j: curr){
                count[j]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i<V;i++){
            if(count[i]==0) queue.offer(i);
        }
        int visitednum = 0;
        while(!queue.isEmpty()){
            int curr = queue.poll();
            System.out.print(curr+" ");
            LinkedList<Integer> currlist = adj[curr];
            for(int currend: currlist){
                count[currend]--;
                if(count[currend]==0){
                    queue.offer(currend);
                }
            }
            visitednum++;
        }
        //if it is possible to do topological sort then visitednum==V
        System.out.println("\n---------- completed topological sort ----------");
    }
    /*
    public void alltop(){
        int[] count = new int[V];
        for(int i = 0;i<V;i++){
            LinkedList<Integer> curr = adj[i];
            for(int j: curr){
                count[j]++;
            }
        }
        List<Integer> available = new ArrayList<>();
        for(int i = 0;i<V;i++){
            if(count[i]==0) {
                available.add(i);
            }
        }
        for(int i = 0;i<available.size();i++){
            int idx = i;
            int val = available.get(idx);
            available.remove(idx);
            List<Integer> list = new ArrayList<>();
            list.add(val);
            int[] newcount = count.clone();
            boolean[] vs = new boolean[V];
            vs[val] = true;
            alltophelper(val, vs, newcount, available, list);
            available.add(idx, val);
        }
        System.out.println("\n---------- completed all topological sort ----------");
    }
    private void alltophelper(int i, boolean[] vs, int[] count,
                              List<Integer> available, List<Integer> list){
       // System.out.println("list size: "+list.size());
        
        
        System.out.println("removing i: "+ i);
        System.out.println("visiting status: "+Arrays.toString(vs));
        System.out.println("count status: "+Arrays.toString(count));
        if(list.size()==count.length&&available.size()==0){
            for(int c: count){
                if(c!=0) return;
            }
            for(int vertex: list){
                System.out.print(vertex + " ");
            }
            System.out.println();
            return;
        }
        List<Integer> currlist = adj[i];
        for(int currend: currlist){
            count[currend]--;
        }
        boolean[] presented = new boolean[V];
        for(int j = 0;j<available.size();j++){
            int val = available.get(j);
            presented[val] = true;
        }
        for(int c = 0;c<V;c++){
            if(count[c]==0&&presented[c]==false&&vs[c]==false) available.add(c);
        }
        for(int j = 0;j<available.size();j++){
            int idx = j;
            int val = available.get(idx);
            available.remove(idx);
           // if(!vs[val]){
            List<Integer> sublist = new ArrayList<>(list);
            sublist.add(val);
            int[] newcount = count.clone();
            boolean[] vss = vs.clone();
            vss[val] = true;
            alltophelper(val, vss, newcount, available, sublist);
            available.add(idx, val);
        }
       // for(int currend: currlist){
        //    count[currend]++;
        //}
    }*/
}
