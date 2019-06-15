package listas;

public class Cola<E> extends Lista<E>{

	public Cola(){
		super();
	}
	
	public E tope(){
		if(p!=null)
			return p.e;
		return null;
	}
	
	public E deleteFirst(){
		if(p==u){
			u=null;
		}
		E elm= p.e;
		p=p.sig;
		t--;
		return elm;	
	}
	
	public void addLast(E e){
		this.add(e);
	}
	
	public boolean vacia(){
		return (u==null);
	}
}
