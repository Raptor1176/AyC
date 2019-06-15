package grafos;

public class Arco implements IArco {

	private Nodo NodoIzquierdo; // Nodo del extremo izquierdo del arco.
	private Nodo NodoDerecho; // Nodo del extremo derecho del arco.
	private int Peso; // Peso del arco.
	private int Marca; // Marca del arco.
	
	/**
	 * Constructor de la clase Arco.
	 * @param nodoDerecho Nodo del extremo izquierdo del arco.
	 * @param nodoIzquierdo Nodo del extremo derecho del arco.
	 * @param peso Peso del arco.
	 */
	public Arco(Nodo nodoIzquierdo, Nodo nodoDerecho, int peso) {
		NodoDerecho = nodoDerecho;
		NodoIzquierdo = nodoIzquierdo;
		Peso = peso;
		Marca = 0;
	}
	
	/**
	 * Metodo para obtener el nodo del extremo derecho del arco.
	 * @return Nodo del extremo derecho del arco.
	 */
	public Nodo getNodoDerecho() {
		return NodoDerecho;
	}
	
	/**
	 * Metodo para setear el nodo del extremo derecho del arco.
	 * @param nodoDerecho Nodo a setear como extremo derecho del arco.
	 */
	public void setNodoDerecho(Nodo nodoDerecho) {
		NodoDerecho = nodoDerecho;
	}
	
	/**
	 * Metodo para obtener el nodo del extremo izquierdo del arco.
	 * @return Nodo del extremo izquierdo del arco.
	 */
	public Nodo getNodoIzquierdo() {
		return NodoIzquierdo;
	}
	
	/**
	 * Metodo para setear el nodo del extremo izquierdo del arco.
	 * @param nodoIzquierdo Nodo a setear como extremo izquierdo del arco.
	 */
	public void setNodoIzquierdo(Nodo nodoIzquierdo) {
		NodoIzquierdo = nodoIzquierdo;
	}
	
	/**
	 * Metodo para obtener el peso del arco.
	 * @return Peso del arco.
	 */
	public int getPeso() {
		return Peso;
	}
	
	/**
	 * Metodo para setear el peso del arco.
	 * @param peso Peso del arco a setear.
	 */
	public void setPeso(int peso) {
		Peso = peso;
	}
	
	/**
	 * Metodo para obtener la marca del arco.
	 * @return Marca del arco.
	 */
	public int getMarca() {
		return Marca;
	}
	
	/**
	 * Metodo para marcar el arco.
	 * @param Marca del arco.
	 */
	public void setMarca(int marca) {
		Marca = marca;
	}
	
	/**
	 * Metodo para comparar el arco con un arco pasado como parametro.
	 * @param Arco para comparar con este.
	 * @return TRUE si el id de ambos extremos son iguales y el peso de ambos es igual, FALSE en caso contrario.
	 */
	public boolean equals(IArco arc) {
		boolean ni = this.NodoIzquierdo.getID() == arc.getNodoIzquierdo().getID();
		boolean nd = this.NodoDerecho.getID() == arc.getNodoDerecho().getID();
		boolean ps = this.Peso == arc.getPeso();
		return ni & nd & ps;
	}
	
	/*
	 * Metodo para imprimir el arco por consola.
	 */
	public String toString(){
		return "(" + NodoIzquierdo.getID() + ")--" +this.Peso+  "--("  + NodoDerecho.getID() + ") ";
	}
}
