package heap;

import grafos.IArco;

public class Heap implements IHeap{

    private IArco[] heap;
    private int tamanio;
    private int capacidad;

	public Heap(int cap) {
    	this.heap = new IArco[cap+1];
    	this.tamanio = 0;
        this.capacidad = cap;
    }

	/**
	 * @return La cantidad de elementos del heap
	 */
    public int size() {
        return this.tamanio;
    }


    /**
     * @return True si el heap esta vacio. False en caso contrario
     */
    public boolean isEmpty() {
        return (this.tamanio == 0);
    }

    /**
     * Retorna el arco de peso minimo del heap
     * @see heap.IHeap#min()
     * @return El arco de menor peso
     */
    public IArco min(){
        if (isEmpty())
            return null;
        else
            return heap[1];
    }


    /**
     * Compara los pesos de los arcos, y retorna un entero que es la 
     * diferencia entre los pesos
     * Si >0, el arco y es el menor
     * Si <0, el arco x es el menor
     * Si =0, los arcos tienen el mismo peso
     * @param IArco x, IArco y: Los arcos a comparar 
     * @return Un entero que es la diferencia entre los pesos de los arcos
     */
    private int comparar(IArco x, IArco y) {
        return (x.getPeso() - y.getPeso());
    }

    
    /**
     * Inserta el arco a en el heap
     * @see heap.IHeap#insert(grafos.IArco)
     * @param IArco a: el arco a insertar
     * @return True si se pudo insertar en el heap, False en caso contrario
     */
    public boolean insert(IArco a) {
        if (this.tamanio == this.capacidad)
            return false;
        else{
            tamanio++;
            heap[tamanio] = a;
            burbujaHaciaArriba();
            return true;
        }       
    }


    /**
     * Elimina el arco minimo y lo retorna
     * @see heap.IHeap#removeMin()
     * @return El arco de menor peso del heap
     */
    public IArco removeMin() {
        if (isEmpty())
            return null;
        else {
            IArco min = min();
            heap[1] = heap[tamanio];
            tamanio--;
            burbujaHaciaAbajo();
            return min;
        }
    }
    
    /**
     * Este metodo se ejecuta luego de hacer un removeMin() y se encarga de mantener la propiedad de heap
     * Recorre al heap desde el inicio y realizando swaps hacia abajo
     */
    private void burbujaHaciaAbajo(){
    	int i=1; //Comienzo desde la raiz
    	int hijo=i*2;
        while ( (hijo<=tamanio) && (comparar(heap[i],heap[hijo])>=0) ){
        	// Mientras no llegue al final del arreglo y 
        	// el padre sea menor o igual al hijo    
        	
            if ((hijo+1)<=this.tamanio){
                // Si i tiene dos hijos, tomo el menor
            	// si son iguales tomo el izquierdo
            	hijo = hijoMenor(hijo, hijo+1);
            }            
            swap(i,hijo); //Hago swap de padre e hijo menor
            i = hijo;
            hijo = i*2;
        }
    }


    /**
     * Este metodo se ejecuta luego de insert() y se encarga de mantener la propiedad de heap
     * Recorre al heap desde el final y va realizando swaps hacia arriba
     */
    private void burbujaHaciaArriba(){
        int i=this.tamanio;
        int padre=i/2;
        while ((i > 1)&&(comparar(heap[i], heap[padre])<=0)){
        	// Mientras no llegue al inicio del heap y
        	// mientras el padre sea mayor al hijo
        	
            swap(i,padre); // hago swap
            i=padre;
            padre=i/2;
        }       
    }


    /**
     * Metodo auxiliar que hace swap de dos elementos del arreglo heap[]
     */
    private void swap(int i, int j) {
        IArco aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    /**
     * Metodo auxiliar que es usado por burbujaHaciaAbajo()
     * @param hizq: indice del hijo izquierdo en el arreglo heap[]
     * @param hder: indice del hijo derecho en el arreglo heap[]
     * @return el indice del hijo menor, si son iguales retorna el izquierdo     *
     */
    private int hijoMenor(int hizq, int hder) {
        if (comparar(heap[hizq], heap[hder]) <= 0)
            return hizq;
        else
            return hder;
    }
    
    /**
     * Retorna una string representando al arreglo heap
     * @return String: cadena que representa el estado del arreglo heap
     */
    public String toString() {
        String s = "[";
        for (int i = 1; i <= size(); i++) {
            s += heap[i].getPeso();
            if (i != tamanio)
                s += ",";
        }
        return s + "]";
    }

}