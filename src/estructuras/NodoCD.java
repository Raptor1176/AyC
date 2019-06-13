package estructuras;

public class NodoCD<E>{
	
	private E element;
	private Lista<NodoCD<E>> children;
	private int rank;
	private NodoCD<E> father;
	
	public NodoCD(E elem){
		this.element=elem;
		this.children=new Lista<NodoCD<E>>();
		this.rank=0;
		this.father=this;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public Lista<NodoCD<E>> getChildren() {
		return children;
	}
	
	public void addChildren(E elem) {
		NodoCD<E> nuevo=new NodoCD<E>(elem);
		nuevo.setFather(this);
		this.rank++;
		this.children.add(nuevo);
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

	public NodoCD<E> getFather() {
		return father;
	}

	public void setFather(NodoCD<E> father) {
		this.father = father;
	}
	
	public boolean equals(NodoCD<E> n) {
		return this.element.equals(n.getElement());
	}
	
		
}