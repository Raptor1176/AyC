package heap;

public interface IHeap {
	
	/**
	 * Retorna la cantidad de elementos del heap
	 * @return Cantidad de arcos en el heap
	 */
	public int size();
	
	/**
	 * Retorna el elemento menor del heap
	 * @return ElemTest el arco del heap con menor peso  
	 */
	public ElemTest min();
	
	/**
	 * Elimina el elemento minimo del heap y lo retorna
	 * @return ElemTest el arco del heap con menor peso
	 */
	public ElemTest removeMin();
	
	/**
	 * Inserta el elemento a en el heap
	 * @param ElemTest a: El arco a insertar en el heap
	 */
	public boolean insert(ElemTest a);

}
