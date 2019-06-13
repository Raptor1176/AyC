import estructuras.NodoCD;


public class ConjuntosDisjuntos<E> {

	private int tama�o;
	private NodoCD<E>[] conjuntos;
	private int index;
	private boolean heuristicas;
	
	@SuppressWarnings("unchecked")
	public ConjuntosDisjuntos(int tama�o, boolean usarHeuristicas) {
		this.tama�o=tama�o;
		this.heuristicas=usarHeuristicas;
		this.index=0;
		this.conjuntos=new NodoCD[tama�o];
	}
	
	
	public NodoCD<E> makeSet(E elem) {
		NodoCD<E> conjunto=new NodoCD<E>(elem);
		this.conjuntos[this.index]=conjunto;
		this.index++;
		return conjunto;
	}
	
	public E findSet(NodoCD<E> n) {
		if(n.equals(n.getFather())) {
			// Si el nodo tiene como padre a si mismo
			// entonces el elemento identificador es el mismo nodo
			return n.getElement();
		}
		else {
			// Si no llamo recursivamente
			return findSet(n.getFather());
		}
	}
	
	public NodoCD<E> union(NodoCD<E> e1, NodoCD<E> e2) {
		
	}
{
	
}