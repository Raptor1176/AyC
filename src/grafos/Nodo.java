package grafos;

import java.awt.Color;
import listas.Lista;

public class Nodo implements INodo {
	
	private int ID; // Identiicador del nodo.
	private Lista<IArco> Arcos; // Lista de arcos que tocan al nodo.
	private Color Marca; // Marca del nodo.
	
	/**
	 * Constructor de la clase nodo.
	 * @param id Identidicador del nodo.
	 */
	public Nodo(int id) {
		ID = id;
		Arcos = new Lista<IArco>();
		Marca = null;
	}
	
	/**
	 * Metodo obtener la marca del nodo.
	 * @return Marca del nodo.
	 */
	public Color getMarca() {
		return Marca;
	}
	
	/**
	 * Metodo para marcar del nodo.
	 * @param Marca del nodo.
	 */
	public void setMarca(Color marca) {
		Marca = marca;
	}
	
	/**
	 * Metodo para obtener el identificador del nodo.
	 * @return Identificador del nodo.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Metodo para obtener la lista de arcos que tocan al nodo.
	 * @return Lista de arcos que tocan al nodo.
	 */
	public Lista<IArco> getArcos() {
		return Arcos;
	}
	
	/**
	 * Metodo para agregar un arco a la lista de arcos que tocan al nodo.
	 * @param arc Arco a agregar.
	 */
	public void addArco(IArco arc) {
		this.Arcos.add(arc);
	}
	
	/**
	 * Metodo para comparar el nodo con otro nodo pasado por parametro.
	 * @param nod Nodo a comparar con este.
	 * @return TRUE si ambos identificadores son iguales, FALSE en caso contrario.
	 */
	public boolean equals(INodo nod) {
		return this.ID == nod.getID();
	}
}
