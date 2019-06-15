package listas;
import listas.ILista;

/**
 * @param <E> Tipo de dato contenido en la lista
 */
public class Lista<E> implements ILista<E>{

	/**
	 * @param <N> Tipo de dato contenido en el nodo
	 */
	protected class Nodo<N>{
		N e;	//Elemento
		Nodo<N> sig; 	//Nodo siguiente
		
		/** 
		 * @param e Elemento a guardar en el nodo
		 */
		private Nodo(N e){
			this.e=e;
			sig=null;
		}
	}
	
	protected Nodo<E> p;	//Primer nodo de la lista
	protected Nodo<E> u;	//Ultimo nodo de la lista
	protected int t;	//Tamaño actual de la lista
	
	private Nodo<E> a;		//Puntero actual para iterador	
	
	/**
	 * Constructor
	 */
	public Lista(){
		p=null;
		u=null;
		t=0;
	}
	
	/**
	 * Añade un elemento al final de la lista
	 */
	public void add(E e){
		if(p!=null){
			u.sig=new Nodo<E>(e);
			u=u.sig;
		}else{
			p= new Nodo<E>(e);
			u=p;
		}
		t++;
	}
	
	/**
	 * Retorna tamaño de la lista
	 */
	public int size(){
		return t;
	}
	
	/**
	 * Retorna el primer elemento de la lista 
	 */
	public E first() {		
		return p.e;
	}

	/**
	 * Retorna el último elemento de la lista
	 */
	public E last() {		
		return u.e;
	}

	/**
	 * Marca el inicio de un recorrido iterativo sobre la lista
	 */
	public void start(){
		a=p;
	}
	
	/**
	 * Comprueba que exista un siguiente elemento en el recorrido
	 */
	public boolean hasNext(){
		return (a!=null);
	}
	
	/**
	 * Retorna el siguiente elemento en el recorrido y se posiciona en el siguiente a este
	 */
	public E next(){
		E elem= a.e;
		a=a.sig;
		return elem;
	}
	
}
