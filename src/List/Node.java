package List;

public class Node<E> {
    public E item;
    public Node<E> next;
    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }
    public Node(E item) {
        this.item = item;
        this.next = null;
    }
}
