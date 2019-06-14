package listas;
import listas.ILista;

public class Lista<E> implements ILista<E>{

	protected class Nodo<N>{
		N e;
		Nodo<N> sig;
		
		private Nodo(N e){
			this.e=e;
			sig=null;
		}
	}
	
	protected Nodo<E> p;	
	protected Nodo<E> u;
	private Nodo<E> a;	
	protected int t;
	
	public Lista(){
		p=null;
		u=null;
		t=0;
	}
	
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

	public void start(){
		a=p;
	}
	
	public boolean hasNext(){
		return (a!=null);
	}
	
	public E next(){
		E elem= a.e;
		a=a.sig;
		return elem;
	}
	
}
