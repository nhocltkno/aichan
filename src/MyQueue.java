import java.util.LinkedList;

public class MyQueue<E> extends LinkedList<E> {
    public MyQueue() {
        super();
    }

    public void enqueue (E x) {
        this.addLast(x);
    }

    public E dequeue() {
        return this.poll();
    }
}
