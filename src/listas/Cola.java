package listas;

public class Cola<E> extends Lista<E>{

	public Cola(){
		super();
	}
	
	public E tope(){
		return p.e;
	}
	
	public E deleteFirst(){
		E elm= p.e;
		p=p.sig;
		return elm;	
	}
	
	public void addLast(E e){
		this.add(e);
	}
	
	public boolean vacia(){
		return (p==null);
	}
}
