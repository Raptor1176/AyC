package Grafos;

import Listas.Lista;

public interface INodo {

	public int getMarca();
	public void setMarca(int marca);
	public int getID();
	public Lista<IArco> getArcos();
	public void addArco(IArco arc);
}
