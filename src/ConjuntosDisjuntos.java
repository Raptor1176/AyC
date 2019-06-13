import estructuras.NodoCD;


public class ConjuntosDisjuntos<E> {

	private int tamaño;
	private NodoCD<E>[] conjuntos;
	private int index;
	
	@SuppressWarnings("unchecked")
	public ConjuntosDisjuntos(int n) {
		this.tamaño=n;
		this.index=0;
		this.conjuntos=new NodoCD[tamaño];
	}
	
	public int makeSet(E elem) {
		NodoCD<E> conjunto=new NodoCD<E>(elem);
		this.conjuntos[this.index]=conjunto;
		this.index++;
		return index-1;
	}
	
	
	
}
