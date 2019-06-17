package analisisYalgoritmos;

import java.awt.Color;
import conjuntosDisjuntos.ConjuntosDisjuntos;
import conjuntosDisjuntos.ElementoConjunto;
import grafos.Grafo;
import grafos.IArco;
import grafos.INodo;
import grafos.Nodo;
import heap.IHeap;
import heap.Heap;
import listas.Cola;
import listas.Lista;

public class Algoritmos {

	private Grafo grafo;
	private int[] padre; // Arreglo de padres de cada nodo utilizado para el recorrido BFS.
	private int[] nivel; // Arreglo de niveles de cada nodo utilizado para el recorrido BFS.
	
	private ConjuntosDisjuntos conjuntoDisjunto;
	private Lista<IArco> listaArcos;
	private IArco[] arcosOrdenados;
	private Heap heapArcos;
	private Lista<IArco> arbolCubrimiento;
	private int cantidadNodos;
	
	public Algoritmos(Grafo graf) {
		this.grafo = graf;
	}
	
	public boolean conexoBFS() {
		this.iniciarBFS(false);
		for(int i = 1; i < this.padre.length; i++) {
			if(this.padre[i] == -1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean conexoDisjointSet() {
		this.listaArcos = new Lista<IArco>();
		this.cantidadNodos = this.grafo.getNodos().length;
		this.conjuntoDisjunto = new ConjuntosDisjuntos(cantidadNodos, true);
		for(Nodo n: this.grafo.getNodos()){
			this.conjuntoDisjunto.makeSet(n);
			n.getArcos().start();
			while(n.getArcos().hasNext()) {
				IArco arc = n.getArcos().next();
				if(arc.getMarca() == 0)
				{
					this.listaArcos.add(arc);
					arc.setMarca(1);
				}
				else {
					arc.setMarca(0);
				}
			}
		}
		this.listaArcos.start();
		while(this.listaArcos.hasNext()) {
			IArco arc = this.listaArcos.next();
			ElementoConjunto conjNodoIzq = arc.getNodoIzquierdo();
			ElementoConjunto conjNodoDer = arc.getNodoDerecho();
			this.conjuntoDisjunto.union(conjNodoIzq, conjNodoDer);
			//System.out.print(conjNodoIzq.getID()+" U ");
			//System.out.println(conjNodoDer.getID()+ " ");
		}
		System.out.println(this.conjuntoDisjunto.toString());
		return this.conjuntoDisjunto.isOneSet();
	}
	
	public Lista<IArco> arbolDeCubrimientoOCH(){
		this.iniciarKruskalOrdenado(true, false);
		return this.arbolCubrimiento;
	}
	
	public Lista<IArco> arbolDeCubrimientoOSH(){
		this.iniciarKruskalOrdenado(false, false);
		return this.arbolCubrimiento;
	}
	
	public Lista<IArco> arbolDeCubrimientoHCH(){
		this.iniciarKruskalHeap(true, false);
		return this.arbolCubrimiento;
	}
	
	public Lista<IArco> arbolDeCubrimientoHSH(){
		this.iniciarKruskalHeap(false, false);
		return this.arbolCubrimiento;
	}
	
	public void iniciarBFS (boolean imprimirPadreyNivel) {
		// Creamos el arreglo padres.
		this.padre = new int[this.grafo.getNodos().length];
		// Inicializamos los elementos del arreglo padre en -1 para identiicar las raices de la foresta del recorrido.
		for(int i=0; i < this.padre.length; i++) {
			this.padre[i] = -1;
		}
		// Creamos el arreglo nivel.
		this.nivel = new int[this.grafo.getNodos().length];
		this.BFS(imprimirPadreyNivel);
	}
	
	public void iniciarKruskalOrdenado (boolean conHeuristica, boolean imprimirArbolCubrimiento) {
		this.listaArcos = new Lista<IArco>();
		this.cantidadNodos = this.grafo.getNodos().length;
		this.conjuntoDisjunto = new ConjuntosDisjuntos(cantidadNodos, conHeuristica);
		for(Nodo n: this.grafo.getNodos()){
			this.conjuntoDisjunto.makeSet(n);
			n.getArcos().start();
			while(n.getArcos().hasNext()) {
				IArco arc = n.getArcos().next();
				if(arc.getMarca() == 0)
				{
					this.listaArcos.add(arc);
					arc.setMarca(1);
				}
				else {
					arc.setMarca(0);
				}
			}
		}
		this.arbolCubrimiento = new Lista<IArco>();
		this.HeapSort();
		this.KruskalOrdenado(imprimirArbolCubrimiento);
	}
	
	public void iniciarKruskalHeap (boolean conHeuristica, boolean imprimirArbolCubrimiento) {
		this.listaArcos = new Lista<IArco>();
		this.cantidadNodos = this.grafo.getNodos().length;
		this.conjuntoDisjunto = new ConjuntosDisjuntos(cantidadNodos, conHeuristica);
		this.heapArcos = new Heap(this.grafo.getCantidadArcos());
		for(Nodo n: this.grafo.getNodos()){
			this.conjuntoDisjunto.makeSet(n);
			n.getArcos().start();
			while(n.getArcos().hasNext()) {
				IArco arc = n.getArcos().next();
				if(arc.getMarca() == 0)
				{
					this.heapArcos.insert(arc);
					arc.setMarca(1);
				}
				else {
					arc.setMarca(0);
				}
			}
		}
		this.arbolCubrimiento = new Lista<IArco>();
		this.KruskalHeap(imprimirArbolCubrimiento);
	}
	
	/**
	 * Metodo que se encarga de realizar el recorrido BFS del grafo.
	 * Al finalizar, en los atributos padre y nivel quedan los resultados del recorrido.
	 * @param imprimirPadreyNivel Booleano que determina si se imprimen los arreglos padre y nivel al finalizar el recorrido.
	 */
	private void BFS(boolean imprimirPadreyNivel) {
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
	private void visitarBF(Cola<INodo> Q) {
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
	 * Ordeno la lista de arcos con un heap
	 * @return Un arreglo de arcos ordenados por menor peso
	 */
	private void HeapSort() {
		IHeap heap = new Heap(this.listaArcos.size());
		this.arcosOrdenados = new IArco[this.listaArcos.size()];
		// Inserto todos en un heap
		this.listaArcos.start();
		while(this.listaArcos.hasNext()) {
			heap.insert(this.listaArcos.next());
		}
		int i=0;
		// Mientras el heap no este vacio
		while(!heap.isEmpty()) {
			// Elimino arco minimo y lo guardo en el arreglo
			this.arcosOrdenados[i] = heap.removeMin();
			i++;
		}
	}
	
	
	
	private void KruskalOrdenado(boolean imprimirArbolCubrimiento) {
		int count = 0;
		int i = 0;
		while((count < (this.cantidadNodos - 1)) && (i < this.arcosOrdenados.length)) {
			IArco arc = this.arcosOrdenados[i];
			ElementoConjunto conjNodoIzq = this.conjuntoDisjunto.findSet(arc.getNodoIzquierdo());
			ElementoConjunto conjNodoDer = this.conjuntoDisjunto.findSet(arc.getNodoDerecho());
			if(conjNodoIzq.getID() != conjNodoDer.getID()) {
				this.conjuntoDisjunto.union(conjNodoIzq, conjNodoDer);
				this.arbolCubrimiento.add(arc);
				count++;
			}
			i++;
		}
		System.out.println("Algoritmo de Kruskal completado correctamente.");
		// Si la impresion de los arreglos padre y nivel estan habilitadas, imprimimos.
		if(imprimirArbolCubrimiento) {
			this.printKruskal();
		}
	}
	
	private void KruskalHeap(boolean imprimirArbolCubrimiento) {
		int count = 0;
		while((count < (this.cantidadNodos - 1)) && (!this.heapArcos.isEmpty())) {
			IArco arc = this.heapArcos.removeMin();
			ElementoConjunto conjNodoIzq = this.conjuntoDisjunto.findSet(arc.getNodoIzquierdo());
			ElementoConjunto conjNodoDer = this.conjuntoDisjunto.findSet(arc.getNodoDerecho());
			if(conjNodoIzq.getID() != conjNodoDer.getID()) {
				this.conjuntoDisjunto.union(conjNodoIzq, conjNodoDer);
				this.arbolCubrimiento.add(arc);
				count++;
			}
		}
		System.out.println("Algoritmo de Kruskal completado correctamente.");
		// Si la impresion de los arreglos padre y nivel estan habilitadas, imprimimos.
		if(imprimirArbolCubrimiento) {
			this.printKruskal();
		}
	}
	
	private void printKruskal() {
		this.arbolCubrimiento.start();
		while(this.arbolCubrimiento.hasNext()) {
			IArco arc = this.arbolCubrimiento.next();
			System.out.print(arc.toString()+" ");
		}
		System.out.println();
	}
}

