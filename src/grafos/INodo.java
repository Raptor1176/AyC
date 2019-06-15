package grafos;

import java.awt.Color;
import listas.Lista;

/**
 * Interfaz de la clase Nodo.
 */
public interface INodo {

	public Color getMarca();
	public void setMarca(Color marca);
	public int getID();
	public Lista<IArco> getArcos();
	public void addArco(IArco arc);
}
