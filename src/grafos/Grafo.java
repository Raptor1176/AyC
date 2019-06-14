package Grafos;

import java.util.ArrayList;

public class Grafo implements IGrafo {
	
	private Nodo[] nodos;

	@SuppressWarnings("rawtypes")
	public Grafo(GrafoObj grafoJson){
		
		// Guardamos los nodos en un arreglo de enteros.
		int [] nodosJson = grafoJson.nodos;
		// Inicializamos la lista de nodos del grafo.
		this.nodos = new Nodo[nodosJson.length];
		// Por cada elemento en el arreglo de enteros, creamos un nodo.
		for(int n : nodosJson) {
			Nodo nod = new Nodo(n);
			this.nodos[n] = nod;
		}
		
		// Obtenemos la lista de arcos del Json.
		Object[][] arcosJson = grafoJson.arcos;		
		
		// Por cada elemento en la lista de arcos
		for (int i = 0; i<arcosJson.length; i++){
			// Obtenemos los nodos que conecta el arco.
			Nodo nodI = this.nodos[((Double) ((ArrayList) arcosJson[i][0]).get(0)).intValue()];
			Nodo nodD = this.nodos[((Double) ((ArrayList) arcosJson[i][0]).get(1)).intValue()];
			// Obtenemos el peso del arco.

			int peso = ((Double) arcosJson[i][1]).intValue();
			// Creamos el arco pasando los nodos previamente creados y el peso.
			Arco arc = new Arco(nodI, nodD, peso);
			nodI.addArco(arc);			
			nodD.addArco(arc);
		}		
	}
	
	public static class GrafoObj {
		int[] nodos;
		Object[][] arcos;
	}
	
	public void print(){
		for(Nodo n: nodos){
			System.out.println(n.getID());
			n.getArcos().start();
			while(n.getArcos().hasNext())
				System.out.print(n.getArcos().next().toString()+" ");
			System.out.println();
			
		}
	}
}
