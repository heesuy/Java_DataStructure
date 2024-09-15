
package heap;

public interface PQInterface<E> {
    public void insert(E newItem) throws Exception;
    public E deleteMax() throws Exception;
    public void clear();
    public boolean isEmpty();
}