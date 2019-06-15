package listas;

/**
 * Cola de Datos implementada sobre una lista enlazada
 *
 * @param <E> Tipo de dato en la cola.
 */
public class Cola<E> extends Lista<E>{

	/**
	 * Constructor
	 */
	public Cola(){
		super();
	}
	
	/** 
	 * @return Dato en el tope de la cola
	 */
	public E tope(){
		if(p!=null)
			return p.e;
		return null;
	}
	
	/**
	 * Elimina el primer elemento de la cola
	 * @return Retorna el elemento eliminado
	 */
	public E deleteFirst(){
		if(p==u){
			u=null;
		}
		E elm= p.e;
		p=p.sig;
		t--;
		return elm;	
	}
	
	/**
	 * Añade un elemento al final de la cola
	 * @param e Elemento a agregar
	 */
	public void addLast(E e){
		this.add(e);
	}
	
	/** 
	 * @return Estado actual de la pila
	 * Verdadero si está vacia
	 * Falso caso contrario
	 */
	public boolean vacia(){
		return (u==null);
	}
}
