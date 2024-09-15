package Queue;

public interface QueueInterface<E> {
    public void enqueue(E x);
    public E dequeue();
    public boolean isEmpty();
    public E front();
    public void dequeueAll();
}
