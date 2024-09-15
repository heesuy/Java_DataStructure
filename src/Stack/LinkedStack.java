package Stack;
import List.Node;

public class LinkedStack<E> implements StackInterface<E> {

    private Node<E> topNode;
    private final E ERROR = null;

    private LinkedStack() {
        topNode = null;
    }

    @Override
    public void push(E newItem) {
    // push가 간단한 이유는 topnode의 레퍼런스만 변경해주고 새로운 topNode가 가르키는 대상만 이전 topNode로 설정하면 되니깐
        topNode = new Node<> (newItem, topNode);
    }

    @Override
    public E pop() {
        if(isEmpty())return ERROR;
        else{
            Node<E> temp = topNode;
            topNode = topNode.next;
            return temp.item;
        }
    }

    @Override
    public E top() {
        if(isEmpty())return ERROR;
        else return topNode.item;
    }

    @Override
    public boolean isEmpty() {
        return (topNode == null);
    }

    @Override
    public void popAll() {
    topNode = null;
    }
}
