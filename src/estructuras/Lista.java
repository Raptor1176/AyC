package estructuras;
import estructuras.ILista;

public class Lista<E> implements ILista<E>{

	private class Nodo<N>{
		N e;
		Nodo<N> sig;
		
		private Nodo(N e){
			this.e=e;
			sig=null;
		}
	}
	
	private Nodo<E> p;	
	private Nodo<E> u;	
	private int t;
	
	public Lista(){
		p=null;
		u=null;
		t=0;
	}
	
	public void add(E e){
		if(p!=null){
			Nodo<E> aux= new Nodo<E>(e);
			u.sig=aux;
			u=u.sig;
		}else{
			p= new Nodo<E>(e);
			u=p;
		}
		t++;
	}
	
	
	public int size(){
		return t;
	}
	@Override
	public E first() {		
		return p.e;
	}

	@Override
	public E last() {		
		return u.e;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] all() {
		E[] arr= null;
		if(t>0){			
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
