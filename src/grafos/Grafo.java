package grafos;

import java.awt.Color;
import java.util.ArrayList;
import listas.Cola;
import listas.Lista;

public class Grafo {
	
	private Nodo[] nodos; // Lista de nodos del grafo.
	private int[] padre; // Arreglo de padres de cada nodo utilizado para el recorrido BFS.
	private int[] nivel; // Arreglo de niveles de cada nodo utilizado para el recorrido BFS.

	/**
	 * Constructor de la clase Grafo.
	 * @param grafoJson Grafo a construir representado en formato Json.
	 */
	@SuppressWarnings("rawtypes")
	public Grafo(GrafoObj grafoJson){
		// Obtenemos la lista de nodos del Json.
		int [] nodosJson = grafoJson.nodos;
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
			// Agregamos el arco creado a los nodos previamente creados.
			nodI.addArco(arc1);			
			nodD.addArco(arc1);
		}		
	}
	
	/**
	 * Metodo que se encarga de realizar el recorrido BFS del grafo.
	 * Al finalizar, en los atributos padre y nivel quedan los resultados del recorrido.
	 * @param imprimirPadreyNivel Booleano que determina si se imprimen los arreglos padre y nivel al finalizar el recorrido.
	 */
	public void BFS(boolean imprimirPadreyNivel) {
		// Creamos el arreglo padres.
		this.padre = new int[this.nodos.length];
		// Inicializamos los elementos del arreglo padre en -1 para identiicar las raices de la foresta del recorrido.
		for(int i=0; i < this.padre.length; i++) {
			this.padre[i] = -1;
		}
		// Creamos el arreglo nivel.
		this.nivel = new int[this.nodos.length];
		// Marcamos los nodos del grafo como blancos.
		for(Nodo n: this.nodos) {
			n.setMarca(Color.WHITE);
		}
		// Creamos una cola para manejar los nodos.
		Cola<INodo> Q = new Cola<INodo>();
		// Por cada nodo en la lista de nodos...
		for(Nodo n : this.nodos) {
			// Si es un nodo blanco...
			if(n.getMarca().equals(Color.WHITE)) {
				// Lo marcamos en gris.
				n.setMarca(Color.GRAY);
				// Lo agregamos a la cola.
				Q.addLast(n);
				// Y comenzamos el proceso de visitar.
				this.visitarBF(Q);
			}
		}
		System.out.println("Recorrido BFS completado correctamente.");
		// Si la impresion de los arreglos padre y nivel estan habilitadas, imprimimos.
		if(imprimirPadreyNivel) {
			System.out.print("Padre: [ ");
			for(int i: this.padre) {
				System.out.print(i + " ");
			}
			System.out.println("]");
			System.out.print("Nivel: [ ");
			for(int i: this.nivel) {
				System.out.print(i + " ");
			}
			System.out.println("]");
		}
		
	}
	
	/**
	 * Metodo auxiliar del recorrido BFS que se encarga de visitar los adyacentes de cada nodo.
	 * @param Q Cola donde se almacenan los nodos a visitar.
	 */
	public void visitarBF(Cola<INodo> Q) {
		// Mientras la cola no este vacia...
		while(!Q.vacia()) {
			// Obtenemos el nodo del tope.
			INodo u = Q.tope();
			// Obtenemos la lista de arcos de ese nodo.
			Lista<IArco> arcList = u.getArcos();
			arcList.start();
			// Mientras haya arcos en esa lista...
			while(arcList.hasNext()) {
				// Obtenemos un arco.
				IArco arc = arcList.next();
				// Obtenemos el nodo que derecho de ese arco.
				INodo w = arc.getNodoDerecho();
				// Si el nodo derecho, es el nodo donde empezamos.
				if(u.getID() == w.getID()) {
					// Obtenemos el izquierdo.
					w = arc.getNodoIzquierdo();
				}
				// Si el nodo obtenido es de color blanco.
				if(w.getMarca().equals(Color.WHITE)) {
					// Lo marcamos en gris.
					w.setMarca(Color.GRAY);
					// Seteamos como padre, el nodo anterior.
					this.padre[w.getID()] = u.getID();
					// Seteamos como nivel, el nivel del padre mas uno.
					this.nivel[w.getID()] = this.nivel[u.getID()] + 1;
					// Y lo agregamos a la cola.
					Q.addLast(w);
				}
			}
			// Si la lista de arcos se vacia quiere decir que ya analizamos todos los adyacentes del nodo.
			// Por lo tanto, lo marcamos como negro.
			u.setMarca(Color.BLACK);
			// Y lo sacamos de la cola.
			Q.deleteFirst();
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
