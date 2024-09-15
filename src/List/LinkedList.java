package List;
import IndexCondition.IndexCondition;
import java.util.function.BiPredicate;
public class LinkedList<E> implements ListInterface<E> {
    // 인터페이스 옆에 <E> 부착 안하고 하고 차이 생각해보기
    private Node<E> head;
    private int numItems;
    private final int NOT_FOUND = -12334;
    private BiPredicate<Integer, IndexCondition> guard = (index, condition) -> condition.guard(index, numItems);
    private BiPredicate<Integer, IndexCondition> guard_minus = (index, condition) -> condition.guard(index, numItems-1);

    public LinkedList(){
        // 더미 헤드 만들기
        head = new Node<>(null,null);
        numItems = 0;
    }
    @Override
    public void add(int index, Object x) {
        // swift guard 같은 느낌?
//        if (index < 0 || index > numItems) {
//            throw new IndexOutOfBoundsException();
//        }
        if(guard.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();

        else{
            Node<E> prevNode = getNode(index-1);
            Node<E> newNode = new Node(x, prevNode.next);
            prevNode.next = newNode;
            numItems++;

        }
    }

    @Override
    public void append(E item) {
        Node<E> prevNode = head;
            while(prevNode.next != null){
                prevNode = prevNode.next;
            }
            Node<E> newNode = new Node<>(item,null);
            prevNode.next = newNode;
            numItems++;
    }

    @Override
    public E remove(int index) {
        if(guard_minus.test(index, ListInterface.condition_closed))throw new IndexOutOfBoundsException();
        else{
            Node<E> prevNode = getNode(index-1);
            Node<E> currNode = prevNode.next;
            prevNode.next = currNode.next;
            numItems--;
            return currNode.item;
        }
    }

    @Override
    public boolean removeItem(E x) {
        Node<E> currNode = head;
        Node<E> prevNode;
        for(int i = 0; i<numItems; i++){
            prevNode = currNode;
            currNode = currNode.next;
            if(((Comparable)(currNode.item)).compareTo(x)==0){
                prevNode.next = currNode.next;
                numItems--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    public Node<E> getNode(int index){
        // swift 스위프트 guard
        if(guard_minus.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();

//        if (index < 0 || index > numItems) {
//            return null;
//        }
        // 노드의 헤드 즉 더미노드부터 탐색 시작
        // 더미노드부터 시작하기에 i<= 부등호 이용
        else{
            Node<E> currNode = head;
            for (int i = 0; i <= index; i++) {
                    currNode = currNode.next;
            }
            return currNode;
        }

    }

    @Override
    public void set(int index, E x) {
        if(guard_minus.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();
        else{
            getNode(index).item = x;
        }
    }

    @Override
    public int indexOf(E x) {

        Node<E> currNode = head;
        int i;
        // next가 아닌 item을 비교하는 것이기에 여기서 부등호는 i< 를 사용해.
        for(i = 0; i <= numItems; i++){

            currNode = currNode.next;

           if(((Comparable)(currNode.item)).compareTo(x) == 0){
               return i;
           }

        }
        return NOT_FOUND;
    }

    @Override
    public int len() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems==0;
    }

    @Override
    public void clear() {
    numItems = 0;
    head = new Node<>(null,null);
    }


}



class CircularLinkedList<E> implements ListInterface<E> {
    private Node<E> tail;
    private int numItems = 0;

    // guard 함수를 부모 클래스에 두고 싶은데 numItems가 문제네..?
    private BiPredicate<Integer, IndexCondition> guard = (index, condition) -> condition.guard(index, numItems);
    private BiPredicate<Integer, IndexCondition> guard_minus = (index, condition) -> condition.guard(index, numItems-1);


    public CircularLinkedList(){
        numItems = 0;
        tail = new Node(-1);
        tail.next = tail;
        // 아무것도 없는 경우
    }


    public Node<E> getNode(int index){
        // swift 스위프트 guard
        if(guard_minus.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();

//        if (index < 0 || index > numItems) {
//            return null;
//        }
            // 노드의 헤드 즉 더미노드부터 탐색 시작
            // 더미노드부터 시작하기에 i<= 부등호 이용
        else{
            Node<E> currNode = tail.next;
            for (int i = 0; i <= index; i++) {
                currNode = currNode.next;
            }
            return currNode;
        }

    }

    @Override
    public void add(int index, E x) {
        if(guard.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();
        else{
            Node<E> prevNode = getNode(index-1);
            Node<E> newNode = new Node(x, prevNode.next);
            prevNode.next = newNode;
            if(index == numItems )
                tail.next = newNode;
            numItems++;
        }

    }

    @Override
    public void append(E x) {
        Node<E> prevNode = tail;
        Node<E> newNode = new Node(x,tail.next);
        prevNode.next = newNode;
        tail = newNode;
        numItems++;
    }

    @Override
    public E remove(int index) {
        if(guard_minus.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();
        else{
            Node<E> prevNode = getNode(index - 1);
            E rItem = prevNode.next.item;
            prevNode.next = prevNode.next.next;
            // 맨끝을 삭제하는 경우 if문
            if(index == numItems )
                tail = prevNode;
            numItems--;
            return rItem;
        }

       //return null;
    }

    @Override
    public boolean removeItem(E x) {
        Node<E> currNode = tail.next;
        Node<E> prevNode;
        for(int i = 0; i<numItems; i++){
            prevNode = currNode;
            currNode = currNode.next;
            if(((Comparable)(currNode.item)).compareTo(x) == 0){
                prevNode.next = currNode.next;
                numItems--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if(guard_minus.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();
        else{
            return getNode(index).item;
        }
    }

    @Override
    public void set(int index, E x) {
        if(guard_minus.test(index, ListInterface.condition_closed)) throw new IndexOutOfBoundsException();
        else{
            // call by reference??
            getNode(index).item = x;
        }
    }

    public final int NOT_FOUND = -12345;

    @Override
    public int indexOf(E x) {
        Node<E> currNode = tail.next;
        for(int i = 0; i<numItems; i++){
            currNode = currNode.next;
            if(((Comparable)(currNode.item)).compareTo(x) == 0){
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int len() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems==0;
    }

    @Override
    public void clear() {
        numItems = 0;
        tail = new Node(-1);
        tail.next = tail;

    }
}


class CircularDoublyLinkedList<E> implements ListInterface<E> {
    @Override
    public void add(int index, E x) {

    }

    @Override
    public void append(E x) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean removeItem(E x) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E x) {

    }

    @Override
    public int indexOf(E x) {
        return 0;
    }

    @Override
    public int len() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}