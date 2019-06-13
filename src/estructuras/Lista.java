package estructuras;
import estructuras.ILista;

public class Lista<E> implements ILista<E>{

	private class Nodo<N>{
		N elemento;
		Nodo<N> siguiente;
		
		private Nodo(N elem){
			this.elemento=elem;
			this.siguiente=null;
		}
	}
	
	private Nodo<E> primero;	
	private Nodo<E> ultimo;	
	private int tamaño;
	
	public Lista(){
		this.primero=null;
		this.ultimo=null;
		this.tamaño=0;
	}
	
	public void add(E e){
		if(this.primero!=null){
			Nodo<E> nuevo= new Nodo<E>(e);
			this.ultimo.siguiente=nuevo;
			this.ultimo=this.ultimo.siguiente;
		}else{
			this.primero= new Nodo<E>(e);
			this.ultimo=primero;
		}
		tamaño++;
	}
	
	
	public int size(){
		return tamaño;
	}

	public E first() {		
		return primero.elemento;
	}


	public E last() {		
		return ultimo.elemento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] all() {
		E[] arr= null;
		if(tamaño>0){			
			arr= (E[]) new Object[t];
			Nodo<E> a=p;
			int i=0;
			while(a!=null){
				arr[i]=a.e;
				i++;
				a=a.sig;
			}
		}
		return arr;	
	}

	
}
