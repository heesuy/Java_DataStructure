package heap;

public class Heap<E extends Comparable> implements  PQInterface<E>{
    private E[] A;
    private int numItems;


    public Heap(int arraySize){
        A = (E[]) new Comparable[arraySize];
        numItems = 0;
    }
    public Heap(E[] B, int numElements){
        A = B;
        numItems = numElements;
    }


    @Override
    public void insert(E newItem) throws PQException {

        if(numItems < A.length){
            A[numItems] = newItem;
            percolateup(numItems);
            numItems++;
        } else throw new PQException("Heap is full overflow");
    }


    @Override
    public E deleteMax() throws PQException {
        // 힙에서 최댓값 삭제 후 리턴
        if(!isEmpty()){
            E max = A[0];
            A[0] = A[numItems-1];
            numItems--;
            percolatedown(0);
            return max;
        }else throw new PQException("Heap is empty underflow");

    }

    @Override
    public void clear() {
    A = (E[]) new Object[A.length];
    numItems = 0;
    }

    @Override
    public boolean isEmpty() { return numItems == 0;}

    private void percolateup(int i){
// A[0...i-1]은 힙 성질 만족
        int parent = (i - 1) / 2;
        if(parent >= 0 && A[i].compareTo(A[parent]) > 0){
            E tmp = A[i];
            A[i] = A[parent];
            A[parent] = tmp;
            percolateup(parent);

        }
    }
    private void percolatedown(int i){
        int child = 2*i+1;
        int rightChild = 2*i+2;
        if(child <= numItems - 1 && A[child].compareTo(A[rightChild]) < 0){
            child = rightChild; // 더 큰것
        }
        if(A[i].compareTo(A[child]) < 0){
            E tmp = A[i];
            A[i] = A[child];
            A[child] = tmp;
            percolatedown(child);
        }
    }

    public void buildHeap(){
        if(numItems >= 2)
            for(int i = (numItems - 2)/2; i >= 0; i--){
                percolatedown(i);
            }
    }
    public E max() throws PQException {
        if(!isEmpty()){
            return A[0];
        }else throw new PQException("MAX Heap is empty underflow");
    }

}
