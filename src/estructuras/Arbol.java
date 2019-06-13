package estructuras;
import estructuras.NodoA;

public class Arbol<E> implements IArbol<E> {

	protected NodoA<E> root;
	protected int size;
	
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
	
	/**
	 * @return 0 si logró insertar correctamente, 1 si falla
	 */
	public int addChildren(NodoA<E> n, E elem) {
		NodoA<E> nuevo=new NodoA<E>(elem);
		nuevo.setFather(n);
		n.incrementRank();
		n.getChildren().add(nuevo);
		return 0;
	}

	public int size() {
		return this.size;
	}	
}
