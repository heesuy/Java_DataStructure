package List;

public class ArrayList<E> implements ListInterface<E>, BinarySearch<E> {

    //검색 삽입 삭제의 복잡도는 n
//            throw new ArrayIndexOutOfBoundsException("Index out of bounds" + "your input : "+index);
    // 계속 new로 해야하는지?
    private E item[];
    private int numItems;
    private static final int DEFAULT_CAPACITY = 64;
    private final int NOT_FOUND = -1232;
    public ArrayList() {
        item = (E[]) new Object[DEFAULT_CAPACITY];
        numItems = 0;


    }

    public ArrayList(int capacity) {
        item = (E[]) new Object[capacity];
        numItems = 0;
    }

    @Override
    public void add(int index, E x) {
        // 확률로 따지면 합사건?? P(AUBUC)
        //index>=numItems 이거 굳이 있어야 해?? 어 디폴트 넘버때문에 있어야 해
        if(numItems >= item.length || index<0 || index>=numItems) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds" + "your input : "+index);
        }
        else{
            for(int i = numItems - 1; i >= index; i--) {
                item[i+1] = item[i];
            }
            item[index] = x;
            numItems++;

        }

    }

    @Override
    public void append(E x) {
        if(numItems >= item.length) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds" + "your input : "+numItems);
        }
        else{
            item[numItems++] = x;
        }
    }

    // remove 함수는 pop처럼 사용자가 삭제한 요소를 확인 할 수 있어
    @Override
    public E remove(int index) {
        if(isEmpty() || index<0 || index>=numItems) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds" + "your input : "+index);
        }
        else{
            // 삭제의 복잡도는 n
            E tmp = item[index];
            //   E tmp는 for문 돌기 전에 미리 뽑아놔야해
            //numItems-2 로 설정한 이유는 i+1이 있어서
            for(int i = index+1; i<numItems-2; i++) {
                item[i]=item[i+1];
            }
            numItems--;
            return tmp;

        }
    }
// removeItem 함수는 사용자가 삭제한 요소를 확인 못 해
    @Override
    public boolean removeItem(E x) {
        int k = 0;
        // 인덱스는 k 그리고 k번째 원소에 있다면 그 k는 진짜 인덱스??
        // compareTo는 뭐지??
        while(k < numItems && ((Comparable)item[k]).compareTo(x)!=0) {
            k++;
        }
        if(k == numItems) {
            return false;
        }
        // 삭제할때 검색을 하게 됨 그래서 복잡도는 n
        else {
            for (int i = k; i <= numItems - 2; i++) {
                item[i] = item[i + 1];
            }
            numItems--;
            return true;
        }
    }

    @Override
    public E get(int index) {
        // 확률에서 P(A|B)??
        if(index >= 0 && index <= numItems-1)
            return item[index];
        else return null;
    }

    @Override
    public void set(int index, E x) {
        // 설정의 복잡도는 1
        if(index >= 0 && index < numItems){
            item[index]=x;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Index out of bounds" + "your input : "+index);
        }
    }

    @Override
    public int indexOf(E x) {
        int i = 0;
        for(i = 0; i<numItems; i++) {
            if(((Comparable)item[i]).compareTo(x)==0) {
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
        return numItems == 0;
    }

    @Override
    public void clear() {
    item = (E[]) new Object[item.length];
    numItems = 0;
    }

    @Override
    public E binarySearch(E a[], int p, int r, E x) {
        if(p>r){
            return null;
        }
        else{
            int q = (p+r)/2;
            if(((Comparable)a[q]).compareTo(x)==0){
                return a[q];
            } else if(((Comparable)a[q]).compareTo(x)<0){
                return binarySearch(a,p,q-1,x);
            }else{
                return binarySearch(a,q+1,r,x);
            }
        }
    }
}
