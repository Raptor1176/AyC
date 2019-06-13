package estructuras;

public class NodoA<E>{
	
	private E element;
	private Lista<NodoA<E>> children;
	private int rank;
	private NodoA<E> father;
	
	public NodoA(E elem){
		this.element=elem;
		this.children=new Lista<NodoA<E>>();
		this.rank=0;
		this.father=null;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public Lista<NodoA<E>> getChildren() {
		return children;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void incrementRank() {
		this.rank++;
	}

	public NodoA<E> getFather() {
		return father;
	}

	public void setFather(NodoA<E> father) {
		this.father = father;
	}
	
		
}