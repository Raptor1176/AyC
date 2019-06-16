package grafos;

import java.util.ArrayList;

public class Grafo {
	
	private Nodo[] nodos; // Lista de nodos del grafo.
	private int cantidadArcos;

	/**
	 * Constructor de la clase Grafo.
	 * @param grafoJson Grafo a construir representado en formato Json.
	 */
	@SuppressWarnings("rawtypes")
	public Grafo(GrafoObj grafoJson){
		// Obtenemos la lista de nodos del Json.
		int [] nodosJson = grafoJson.nodos;
		this.cantidadArcos = 0;
		// Inicializamos la lista de nodos del grafo.
		this.nodos = new Nodo[nodosJson.length];
		// Por cada elemento de la lista de nodos del Json, creamos un nodo para el grafo.
		for(int n : nodosJson) {
			Nodo nod = new Nodo(n);
			this.nodos[n] = nod;
		}
		// Obtenemos la lista de arcos del Json.
		Object[][] arcosJson = grafoJson.arcos;		
		// Por cada elemento en la lista de arcos del Json
		for (int i = 0; i < arcosJson.length; i++){
			// Obtenemos los nodos que conecta el arco.
			Nodo nodI = this.nodos[((Double) ((ArrayList) arcosJson[i][0]).get(0)).intValue()];
			Nodo nodD = this.nodos[((Double) ((ArrayList) arcosJson[i][0]).get(1)).intValue()];
			// Obtenemos el peso del arco.
			int peso = ((Double) arcosJson[i][1]).intValue();
			// Creamos un arco para el grafo pasando los nodos previamente creados y el peso.
			Arco arc1 = new Arco(nodI, nodD, peso);
			this.cantidadArcos++;
			// Agregamos el arco creado a los nodos previamente creados.
			nodI.addArco(arc1);			
			nodD.addArco(arc1);
		}		
	}
	
	/**
	 * Clase que representa el grafo en formato Json.
	 * Se utiliza para construir el grafo en el constructor.
	 */
	public static class GrafoObj {
		int[] nodos;
		Object[][] arcos;
	}
	
	/*
	 * Metodo para obtener la lista de nodos del grafo.
	 */
	public Nodo[] getNodos() {
		return this.nodos;
	}
	
	public int getCantidadArcos(){
		return this.cantidadArcos;
	}
	
	/**
	 * Metodo para imprimir el grafo por consola.
	 */
	public void print(){
		for(Nodo n: nodos){
			n.getArcos().start();
			System.out.print("Arcos del nodo " + n.getID() + " : ");
			while(n.getArcos().hasNext()) {
				IArco arc = n.getArcos().next();
				if(arc.getMarca() == 0)
				{
					System.out.print(arc.toString()+" ");
					arc.setMarca(1);
				}
				else {
					arc.setMarca(0);
				}
			}
			System.out.println();
		}
	}
}
