package List;
import IndexCondition.IndexCondition;
public interface ListInterface<E> {
    //int numItems = Integer.parseInt(null);
    // 확장할 점, IndexCondition 속성을 함수의 인자로만 이용
    // 이렇게 한다면 IndexCondition 인터페이스를 물려받는 클래스들 즉 조건문을 실체화시키는 클래스들을 각 함수에 맞게 커스텀 가능?
    //  ocp 원칙 고려한 확장 계획

    IndexCondition condition_open = (i, boundary) -> i >= 0 && i <= boundary;
    IndexCondition condition_closed = (i, boundary) -> i<0 || i>boundary;

    // Define the flexible guard lambda function
    //BiPredicate<Integer, IndexCondition> guard_Open = cond;

    public void add(int index, E x);
    public void append(E x);
    public E remove(int index);
    public boolean removeItem(E x);
    public E get(int index);
    public void set(int index, E x);
    public int indexOf(E x);
    public int len();
    public boolean isEmpty();
    public void clear();
}
