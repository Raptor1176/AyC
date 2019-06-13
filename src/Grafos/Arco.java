package Grafos;

public class Arco implements IArco {

	private int ID;
	private Nodo NodoIzquierdo;
	private Nodo NodoDerecho;
	private int Peso;
	private int Marca;
	
	/**
	 * @param iD
	 * @param nodoDerecho
	 * @param nodoIzquierdo
	 * @param peso
	 * @param marca
	 */
	public Arco(Nodo nodoIzquierdo, Nodo nodoDerecho, int peso) {
		ID = nodoDerecho.getID() + nodoIzquierdo.getID();
		NodoDerecho = nodoDerecho;
		NodoIzquierdo = nodoIzquierdo;
		Peso = peso;
		Marca = 0;
	}
	
	/**
	 * @return the nodoDerecho
	 */
	public Nodo getNodoDerecho() {
		return NodoDerecho;
	}
	
	/**
	 * @param nodoDerecho the nodoDerecho to set
	 */
	public void setNodoDerecho(Nodo nodoDerecho) {
		NodoDerecho = nodoDerecho;
	}
	
	/**
	 * @return the nodoIzquierdo
	 */
	public Nodo getNodoIzquierdo() {
		return NodoIzquierdo;
	}
	
	/**
	 * @param nodoIzquierdo the nodoIzquierdo to set
	 */
	public void setNodoIzquierdo(Nodo nodoIzquierdo) {
		NodoIzquierdo = nodoIzquierdo;
	}
	
	/**
	 * @return the peso
	 */
	public int getPeso() {
		return Peso;
	}
	
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		Peso = peso;
	}
	
	/**
	 * @return the marca
	 */
	public int getMarca() {
		return Marca;
	}
	
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(int marca) {
		Marca = marca;
	}
	
	
}
