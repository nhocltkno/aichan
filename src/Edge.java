public class Edge implements Comparable{
    Vertex u;
    Vertex v;
    double weight;
    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
        this.weight = 1;
    }

    public Edge( Vertex u, Vertex v, double weight ) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public Vertex getU() {
        return u;
    }

    public void setU( Vertex u ) {
        this.u = u;
    }

    public Vertex getV() {
        return v;
    }

    public void setV( Vertex v ) {
        this.v = v;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight( double weight ) {
        this.weight = weight;
    }

    public int compareTo(Object o){
        Edge edge = ((Edge) o);
        return (u.name+v.name).compareTo(edge.u.name+edge.v.name);
    }

    public String toString(){
        return "(" +u.name+", "+v.name+", "+weight+")";
    }
}
