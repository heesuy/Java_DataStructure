package Stack;

public class ArrayStack<E> implements StackInterface<E> {

    private E stack[];
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 64;
    private final E ERROR = null;

    public ArrayStack() {
        stack = (E[]) new Object[DEFAULT_CAPACITY];
        topIndex = -1;
    }
    public ArrayStack(int n) {
        stack = (E[]) new Object[n];
        // topIndex는 여기서도 -1
        topIndex = -1;
    }


    @Override
    public void push(E newItem) {
    if(ifFull()){
        throw new StackOverflowError();
    }
    // topIndex값을 먼저 증가시켜야 해서 전위 연산자를 사용했어
    else stack[++topIndex] = newItem;
    }

    @Override
    public E pop() {
        if(isEmpty()) return ERROR;
        else return stack[topIndex--];

    }

    @Override
    public E top() {
        if(isEmpty()) return ERROR;
        else return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return (topIndex<0);
    }

    @Override
    public void popAll() {
        stack = (E[]) new Object[stack.length];
        topIndex = -1;

    }
    public boolean ifFull(){
        return(topIndex == (stack.length - 1));
    }
}
