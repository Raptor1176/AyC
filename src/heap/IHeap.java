package heap;

import grafos.IArco;

public interface IHeap {
	
	/**
	 * Retorna la cantidad de elementos del heap
	 * @return Cantidad de arcos en el heap
	 */
	public int size();
	
	/**
	 * @return True si el heap esta vacio, False en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Retorna el elemento menor del heap
	 * @return IArco el arco del heap con menor peso  
	 */
	public IArco min();
	
	/**
	 * Elimina el elemento minimo del heap y lo retorna
	 * @return IArco el arco del heap con menor peso
	 */
	public IArco removeMin();
	
	/**
	 * Inserta el elemento a en el heap
	 * @param IArco a: El arco a insertar en el heap
	 */
	public boolean insert(IArco a);

}
