package estructuras;
import estructuras.NodoA;

public class Arbol<E> implements IArbol<E> {

	private NodoA<E> root;
	private int size;
	
	public Arbol(){
		this.root=null;
		this.size=0;
	}
	
	public E root() {
		return this.root.getElement();
	}
	
	public void setRoot(E elem) {
		NodoA<E> n=new NodoA<E>(elem);
		this.root=n;
		this.size++;
	}
	
	public NodoA<E> getFather(NodoA<E> n) {
		return n.getFather();
	}
	
	public void addChildren(NodoA<E> n, E elem) {
		NodoA<E> nuevo=new NodoA<E>(elem);
		nuevo.setFather(n);
		n.incrementRank();
		n.getChildren().add(nuevo);
	}

	public int size() {
		return this.size;
	}	
}
