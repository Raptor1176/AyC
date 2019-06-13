import estructuras.NodoCD;


public class ConjuntosDisjuntos<E> {

	private int tamanio;
	// Necesito tambien un arreglo con los elementos para retornarlos en el findset
	private E[] elementos;
	private int[] raices;
	private int[] rangos;
	private boolean heuristicas;
	
	@SuppressWarnings("unchecked")
	public ConjuntosDisjuntos(int tamanio, boolean usarHeuristicas) {		
		
		this.tamanio=tamanio;
		this.heuristicas=usarHeuristicas;
		this.raices= new int[tamanio];
		this.rangos= new int[tamanio];
		this.elementos=(E[]) new Object[tamanio];
	}
	
	
	public void makeSet(E elem) {		
		int index=elem.getID();
		this.elementos[index]=elem;
		this.raices[index]=index;
		this.rangos[index]=0;		
	}
	
	public E findSet(E elem) {
		int index=elem.getID();
		if(this.raices[index]==index) {			
			// Si el nodo tiene como padre a si mismo
			// entonces el elemento identificador es el mismo nodo
			return elem;
		}
		else {
			// Si no llamo recursivamente
			return findSet(this.elementos[this.raices[index]]);
		}
	}
	
	public void union(E e1, E e2) {
		int index1= e1.getID();
		int index2= e2.getID();		
		if(this.raices[index1]==this.raices[index2]) {
			// Mismos padres, ambos pertenecen al mismo conjunto
			// Entonces hay que hacer nada
			return ;
		}
		else {
			// Sino, seteo como raiz de e2 a la raiz de e1
			this.raices[index2]=this.raices[index1];
		}
	}
	
}