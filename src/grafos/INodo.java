package grafos;

import java.awt.Color;

import listas.Lista;

public interface INodo {

	public Color getMarca();
	public void setMarca(Color marca);
	public int getID();
	public Lista<IArco> getArcos();
	public void addArco(IArco arc);
}
