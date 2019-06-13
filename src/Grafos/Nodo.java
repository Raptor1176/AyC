package Grafos;

import estructuras.Lista;

public class Nodo implements INodo {
	
	private int ID;
	private Lista<IArco> Arcos;
	private int Marca;
	
	/**
	 * @param iD
	 * @param arcos
	 * @param marca
	 */
	public Nodo(int id) {
		ID = id;
		Arcos = new Lista<IArco>();
		Marca = 0;
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
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * @return the arcos
	 */
	public Lista<IArco> getArcos() {
		return Arcos;
	}
	
	public void addArco(Arco arc) {
		this.Arcos.add(arc);
	}
	
	
}
