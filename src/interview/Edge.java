package interview;
public class Edge{
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
