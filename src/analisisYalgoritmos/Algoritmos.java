package analisisYalgoritmos;
import java.awt.Color;

import conjuntosDisjuntos.ConjuntoDisjunto;
import grafos.Grafo;
import grafos.IArco;
import grafos.INodo;
import grafos.Nodo;
import listas.Cola;
import listas.Lista;

public class Algoritmos {

	private Grafo grafo;
	private ConjuntoDisjunto conjuntoDisjunto;
	private int[] padre; // Arreglo de padres de cada nodo utilizado para el recorrido BFS.
	private int[] nivel; // Arreglo de niveles de cada nodo utilizado para el recorrido BFS.
	
	public Algoritmos(Grafo graf) {
		this.grafo = graf;
		this.conjuntoDisjunto = null;
		// Creamos el arreglo padres.
		this.padre = new int[this.grafo.getNodos().length];
		// Inicializamos los elementos del arreglo padre en -1 para identiicar las raices de la foresta del recorrido.
		for(int i=0; i < this.padre.length; i++) {
			this.padre[i] = -1;
		}
		// Creamos el arreglo nivel.
		this.nivel = new int[this.grafo.getNodos().length];
	}
	/**
	 * Metodo que se encarga de realizar el recorrido BFS del grafo.
	 * Al finalizar, en los atributos padre y nivel quedan los resultados del recorrido.
	 * @param imprimirPadreyNivel Booleano que determina si se imprimen los arreglos padre y nivel al finalizar el recorrido.
	 */
	public void BFS(boolean imprimirPadreyNivel) {
		// Marcamos los nodos del grafo como blancos.
		for(Nodo n : this.grafo.getNodos()) {
			n.setMarca(Color.WHITE);
		}
		// Creamos una cola para manejar los nodos.
		Cola<INodo> Q = new Cola<INodo>();
		// Por cada nodo en la lista de nodos...
		for(Nodo n : this.grafo.getNodos()) {
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
}
