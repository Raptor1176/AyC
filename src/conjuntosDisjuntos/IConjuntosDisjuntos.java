package conjuntosDisjuntos;

public interface IConjuntosDisjuntos {
	
	/**
	 * Crea un conjunto de un elemento
	 * @param ElementoConjunto e: El elemento
	 */
	public void makeSet(ElementoConjunto e);
	
	/**
	 * Une los dos conjuntos de los cuales x e y son parte, 
	 * si x e y ya pertenecen al mismo conjuntos entonces no hace nada
	 * @param ElementoConjunto x, ElementoConjunto y: Los elementos a unir
	 */
	public void union(ElementoConjunto x, ElementoConjunto y);
	
	/**
	 * Retorna el elemento representante del conjunto que contiene a e
	 * @param ElementoConjunto e: El elemento a buscarle su representante
	 * @return ElementoConjuto que representa al elemento e 
	 */
	public ElementoConjunto findSet(ElementoConjunto e);	
	
}
