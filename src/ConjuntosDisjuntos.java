import estructuras.NodoCD;


public class ConjuntosDisjuntos<E> {

	private int tama�o;
	private NodoCD<E>[] conjuntos;
	private int index;
	
	@SuppressWarnings("unchecked")
	public ConjuntosDisjuntos(int n) {
		this.tama�o=n;
		this.index=0;
		this.conjuntos=new NodoCD[tama�o];
	}
	
	public int makeSet(E elem) {
		NodoCD<E> conjunto=new NodoCD<E>(elem);
		this.conjuntos[this.index]=conjunto;
		this.index++;
		return index-1;
	}
	
	
	
}
