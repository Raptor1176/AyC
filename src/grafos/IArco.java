package grafos;

/**
 * Interfaz de la clase Arco.
 */
public interface IArco {

	public Nodo getNodoDerecho();
	public void setNodoDerecho(Nodo nodoDerecho);
	public Nodo getNodoIzquierdo();
	public void setNodoIzquierdo(Nodo nodoIzquierdo);
	public int getPeso();
	public void setPeso(int peso);
	public int getMarca();
	public void setMarca(int marca);
}
