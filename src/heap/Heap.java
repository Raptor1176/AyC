package heap;

import conjuntosDisjuntos.Elemento;

public class Heap<E> {

    private Elemento<E>[] heap;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
	public Heap(int cap) {
    	this.heap = new Elemento[cap+1];
    	this.size = 0;
        this.capacity = cap;
    }

    //
    // returns the number of elements in the heap
    //
    public int size() {
        return this.size;
    }


    //
    // is the heap empty?
    //
    public boolean isEmpty() {
        return (this.size == 0);
    }


    //
    // returns element with smallest key, without removal
    //
    public Elemento<E> min(){
        if (isEmpty())
            return null;
        else
            return heap[1];
    }



    private int compare(Elemento<E> x, Elemento<E> y) {
        return (x.getID() - y.getID());
    }

    
    //
    // inserts e into the heap
    //
    public boolean insert(Elemento<E> e) {
        if (this.size == this.capacity)
            return false;
        else{
            size++;
            heap[size] = e;
            upHeapBubble();
            return true;
        }       
    }


    //
    // removes and returns smallest element of the heap
    //
    public E removeMin() {
        if (isEmpty())
            return null;
        else {
            Elemento<E> min = min();
            heap[1] = heap[size];
            size--;
            downHeapBubble();
            return min.getElemento();
        }
    }



    /**
     * downHeapBubble() method is used after the removeMin() method to reorder the elements
     * in order to preserve the Heap properties
     */
    private void downHeapBubble(){
        int index = 1;
        while (true){
            int child = index*2;
            if (child > size())
                break;
            if (child + 1 <= size()){
                //if there are two children -> take the smalles or
                //if they are equal take the left one
                child = findMin(child, child + 1);
            }
            if (compare(heap[index],heap[child]) <= 0 )
                break;
            swap(index,child);
            index = child;
        }
    }

    /**
     * upHeapBubble() method is used after the insert(E e) method to reorder the elements
     * in order to preserve the Heap properties 
     */
    private void upHeapBubble(){
        int index = size();
        while (index > 1){
            int parent = index / 2;
            if (compare(heap[index], heap[parent]) >= 0)
                //break if the parent is greater or equal to the current element
                break;
            swap(index,parent);
            index = parent;
        }       
    }

    /**
     * Swaps two integers i and j
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        Elemento<E> aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    /**
     * the method is used in the downHeapBubble() method
     * @param leftChild
     * @param rightChild
     * @return min of left and right child, if they are equal return the left
     */
    private int findMin(int leftChild, int rightChild) {
        if (compare(heap[leftChild], heap[rightChild]) <= 0)
            return leftChild;
        else
            return rightChild;
    }
    
    //
    // outputs the entries in heap in the order heap[1] to heap[last]
    // in same style as used in ArrayQueue
    //
    public String toString() {
        String s = "[";
        for (int i = 1; i <= size(); i++) {
            s += heap[i].getID();
            if (i != size)
                s += ",";
        }
        return s + "]";
    }


}