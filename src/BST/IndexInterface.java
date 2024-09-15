package BST;
// TreeNode AVLNode등 T가 가변적으로 수용할 수 있도록 설계함
public interface IndexInterface<T> {
    public T search(Comparable x);
    public void insert(Comparable x);
    public void delete(Comparable x);
    public boolean isEmpty();
    public void clear();


}
