import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Graph extends TreeSet<Vertex> {
    public Graph(){
        super();
    }

    public void addVertex(Vertex v) {
        this.add(v);
    }

    public Vertex get(String vertexName){
        for ( Vertex v : this ) {
            if ( v.name.equals(vertexName) ) return v;
        }
        return null;
    }

    public boolean addEdge(String nameFrom, String nameTo){
        Vertex u = this.get(nameFrom);
        Vertex v= this.get(nameTo);
        if (u == null || v == null) return false;
        Edge edge = new Edge(u,v,1);
        u.adjList.add(edge);
        return true;
    }

    public boolean addEdge(Vertex u, Vertex v){
        if (u == null || v == null) return false;
        Edge edge = new Edge(u,v,1);
        u.adjList.add(edge);
        return true;
    }

    public boolean addEdge(String nameFrom, String nameTo, double w){
        Vertex u = this.get(nameFrom);
        Vertex v= this.get(nameTo);
        if (u == null || v == null) return false;
        Edge edge = new Edge(u,v,w);
        u.adjList.add(edge);
        return true;
    }

    public boolean addEdge(Vertex u, Vertex v, double w){
        if (u == null || v == null) return false;
        Edge edge = new Edge(u,v,w);
        u.adjList.add(edge);
        return true;
    }

    public Graph loadFromFile1(String filename){
        Graph g = null;
        File file = new File(filename);
        if (!file.exists()){
            System.out.println("File not found!");
            System.exit(10);
            return g;
        }
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String line = null;
            StringTokenizer stk =null;
            line = bf.readLine();
            if (line!=null){
                g = new Graph();
                stk = new StringTokenizer(line," ");
                while (stk.hasMoreTokens()){
                    String name = stk.nextToken();
                    Vertex v = new Vertex(name);
                    g.add(v);
                }
                while ((line= bf.readLine())!=null){
                    stk = new StringTokenizer(line," ");
                    String nameFrom = stk.nextToken();
                    Vertex u = g.get(nameFrom);
                    while (stk.hasMoreTokens()){
                        String nameTo = stk.nextToken();
                        Vertex v = g.get(nameTo);
                        g.addEdge(u,v);
                    }
                }
            }
            bf.close();
            fr.close();
        } catch (Exception e){
            g=null;
        }
        return g;
    }

    public String toString(){
        String s = "Empty Graph";
        if ( this.size() == 0 ) return s;
        s = "Graph:\n";
        for ( Vertex u : this ) {
            s += u.getName() + "\t";
            for ( Edge edge : u.getAdjList() ) {
                s += edge.toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    protected int DFS( Vertex u, int order, ArrayList<Edge> edges ){
        int newOrder = order+1;
        u.setNum(order);
        for (Edge edge : u.getAdjList()){
            Vertex v = edge.getV();
            if (v.num ==0){
                edges.add(edge);
                newOrder = DFS(v,newOrder,edges);
            }
        }
        return newOrder;
    }

    public ArrayList<Edge> depthFirstSearch(){
        ArrayList<Edge> result = new ArrayList<>();
        Object[] vertices = this.toArray();
        for ( Object vertex : vertices ) {
            Vertex v = (Vertex) vertex;
            v.num = 0;
        }

        int order = 1;
        for (Object vertex: vertices){
            Vertex v = (Vertex)vertex;
            if (v.num==0) order=DFS(v,order,result);
        }
        return result.size()>0?result:null;
    }

    public ArrayList<Edge> breadthFirstSearch(){
        ArrayList<Edge> result = new ArrayList<>();
        MyQueue<Vertex> queue = new MyQueue<>();
        Object[] vertices = this.toArray();
        for (Object vertex: vertices){
            Vertex v = (Vertex) vertex;
            v.num = 0;
        }

        int order = 1;
        for (Object vertex: vertices){
            Vertex u = (Vertex) vertex;
            if (u.num==0){
                u.num = order++;
                queue.enqueue(u);
                while (!queue.isEmpty()){
                    u = queue.dequeue();
                    for (Edge edge: u.adjList){
                        Vertex v = edge.getV();
                        if (v.num ==0){
                            v.num = order++;
                            queue.enqueue(v);
                            result.add(edge);
                        }
                    }
                }
            }
        }
        return result.size()>0?result:null;
    }

    public void printEdges( ArrayList<Edge> edges, PrintStream pw ){
        if (edges == null || edges.isEmpty()){
            pw.println("No edge.");
        } else {
            pw.println("Set of edges: ");
            pw.println(edges);
        }
    }
}
