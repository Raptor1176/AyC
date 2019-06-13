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
	private int tama�o;
	
	public Lista(){
		this.primero=null;
		this.ultimo=null;
		this.tama�o=0;
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
		tama�o++;
	}
	
	
	public int size(){
		return tama�o;
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
		if(tama�o>0){			
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
