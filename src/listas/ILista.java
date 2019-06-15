package listas;

/**
 * Interface de Lista
 *
 * @param <E> Tipo de dato contenido en la lista
 */
public interface ILista<E>{

	
	public void add(E e);
	public E first();
	public E last();
	public int size();
	public void start();
	public boolean hasNext();
	public E next();
}
