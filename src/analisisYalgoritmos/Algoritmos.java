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

	private Grafo grafo; // Grafo utilizado para los algoritmos implementados.
	private int[] padre; // Arreglo de padres de cada nodo utilizado para el recorrido BFS.
	private int[] nivel; // Arreglo de niveles de cada nodo utilizado para el recorrido BFS.
	
	private ConjuntosDisjuntos conjuntoDisjunto; // Conjunto disjunto utilizado para conectitud y Kruskal.
	private Lista<IArco> listaArcos; // Lista de arcos del grafo, utilizada para Kruskal.
	private IArco[] arcosOrdenados; // Arreglo auxiliar para guardar los arcos ordenados para Kruskal.
	private Heap heapArcos; // Heap utilizado para almacenar los arcos para Kruskal.
	private Lista<IArco> arbolCubrimiento; // Lista donde queda almacenado el arbol de cubrimiento luego de aplicar Kruskal.
	private int cantidadNodos; // Cantidad de nodos del grafo.
	
	/**
	 * Constructor de la clase Algoritmos.
	 * @param graf Grafo de entrada que se utiliza en los algoritmos.
	 */
	public Algoritmos(Grafo graf) {
		this.grafo = graf;
	}
	
	/**
	 * Metodo utilizado para calcular si un grafo es conexo utilizando BFS.
	 * @return TRUE si en el arreglo padre hay solo un -1 (unica raiz), FALSE en caso contrario.
	 */
	public boolean conexoBFS() {
		// Hacemos un recorrido BFS.
		this.iniciarBFS(false); // O()
		// Recorremos el arreglo padre y si detectamos mas de un -1 es que hay mas de un arbol
		// en la foresta, por lo tanto el grafo no es conexo.
		for(int i = 1; i < this.padre.length; i++) { // O(n)
			if(this.padre[i] == -1) { // O(1)
				return false; // O(1)
			}
		}
		// Si hay solo un -1 quiere decir que hay un solo arbol en la foresta,
		// por lo tanto el grafo es conexo.
		return true;
	}
	
	/**
	 * Metodo utilizado para calcular si un grafo es conexo utilizando conjuntos disjuntos.
	 * @return ****************************COMPLETAR CUANDO ESTE TERMINADO*****************
	 */
	public boolean conexoDisjointSet() {
		// Inicializamos la lista de arcos.
		this.listaArcos = new Lista<IArco>();//c
		// Obtenemos la cantidad de nodos del grafo.
		Nodo[] nodosGrafo = this.grafo.getNodos();
		this.cantidadNodos = nodosGrafo.length;//c
		// Inicializamos el conjunto disjunto.
		this.conjuntoDisjunto = new ConjuntosDisjuntos(cantidadNodos, false);//c
		// Para cada nodo del grafo...
		for(int i = 0; i < this.cantidadNodos; i++){// cant nodos
			Nodo n = nodosGrafo[i];
			// Creamos un conjunto con ese nodo.
			this.conjuntoDisjunto.makeSet(n);// c
			n.getArcos().start();
			// Para cada arco de ese nodo...
			while(n.getArcos().hasNext()) { //cant arcos
				IArco arc = n.getArcos().next();
				// Si no esta marcado...
				if(arc.getMarca() == 0) {
					// Lo agregamos a la lista de arcos.
					this.listaArcos.add(arc);
					// Y lo marcamos.
					arc.setMarca(1);
				}
				else {
					// Si esta marcado, lo desmarcamos.
					arc.setMarca(0);
				}
			}
		}
		this.listaArcos.start();
		// Para cada arco en la lista de arcos...
		while(this.listaArcos.hasNext()) {// cant arcos
			IArco arc = this.listaArcos.next();
			// Obtenemos los nodos que conecta ese arco...
			ElementoConjunto conjNodoIzq = arc.getNodoIzquierdo();
			ElementoConjunto conjNodoDer = arc.getNodoDerecho();
			// Y los unimos en un mismo conjunto.
			this.conjuntoDisjunto.union(conjNodoIzq, conjNodoDer);
		}
		// Al finalizar preguntamos si el conjunto que nos quedo es un unico conjunto.
		System.out.println(this.conjuntoDisjunto.toString());
		return this.conjuntoDisjunto.isOneSet();
	}
	
	/**
	 * Metodo utilizado para obtener el arbol de cubrimiento de un grafo utilizando Kruskal
	 * sobre una lista ordenada de arcos, utilizando heuristicas en el conjunto disjunto.
	 * @return Lista de arcos que representan el arbol de cubrimiento para el grafo.
	 */
	public Lista<IArco> arbolDeCubrimientoOCH(){
		this.iniciarKruskalOrdenado(true, false);
		return this.arbolCubrimiento;
	}
	
	/**
	 * Metodo utilizado para obtener el arbol de cubrimiento de un grafo utilizando Kruskal
	 * sobre una lista ordenada de arcos, sin utilizar heuristicas en el conjunto disjunto.
	 * @return Lista de arcos que representan el arbol de cubrimiento para el grafo.
	 */
	public Lista<IArco> arbolDeCubrimientoOSH(){
		this.iniciarKruskalOrdenado(false, false);
		return this.arbolCubrimiento;
	}
	
	/**
	 * Metodo utilizado para obtener el arbol de cubrimiento de un grafo utilizando Kruskal
	 * sobre un min heap de arcos, utilizando heuristicas en el conjunto disjunto.
	 * @return Lista de arcos que representan el arbol de cubrimiento para el grafo.
	 */
	public Lista<IArco> arbolDeCubrimientoHCH(){
		this.iniciarKruskalHeap(true, false);
		return this.arbolCubrimiento;
	}
	
	/**
	 * Metodo utilizado para obtener el arbol de cubrimiento de un grafo utilizando Kruskal
	 * sobre un min heap de arcos, sin utilizar heuristicas en el conjunto disjunto.
	 * @return Lista de arcos que representan el arbol de cubrimiento para el grafo.
	 */
	public Lista<IArco> arbolDeCubrimientoHSH(){
		this.iniciarKruskalHeap(false, false);
		return this.arbolCubrimiento;
	}
	
	/**
	 * Metodo utilizado para iniciar las estructuras que utiliza el recorrido BFS
	 * y luego iniciar dicho recorrido. Al finalizar el arreglo padre y nivel quedaran completados.
	 * @param imprimirPadreyNivel Booleano que determina si se imprimen los arreglos padre y nivel al finalizar.
	 */
	public void iniciarBFS (boolean imprimirPadreyNivel) {
		// Creamos el arreglo padres.
		this.padre = new int[this.grafo.getNodos().length]; // O(1)
		// Inicializamos los elementos del arreglo padre en -1 para identiicar las raices de la foresta del recorrido.
		for(int i = 0; i < this.padre.length; i++) { // O(n)
			this.padre[i] = -1; // O(1)
		}
		// Creamos el arreglo nivel.
		this.nivel = new int[this.grafo.getNodos().length]; // O(1)
		// Iniciamos el recorrido BFS.
		this.BFS(imprimirPadreyNivel); // O()
	}
	
	/**
	 * Metodo utilizado para iniciar las estructuras que utiliza Kruskal con un arreglo ordenado y
	 * luego iniciar el algoritmo de Kruskal. Al finalizar, la lista arbolCubrimiento contendra el resultado.
	 * @param conHeuristica Booleano que determina si se utilizan heuristicas en el conjunto disjunto.
	 * @param imprimirArbolCubrimiento Booleano que determina si se imprime la lista que representa el arbol de cubrimiento al finalizar.
	 */
	public void iniciarKruskalOrdenado (boolean conHeuristica, boolean imprimirArbolCubrimiento) {
		// Inicializamos la lista de arcos.
		this.listaArcos = new Lista<IArco>();
		// Obtenemos la cantidad de nodos del grafo.
		Nodo[] nodosGrafo = this.grafo.getNodos();
		this.cantidadNodos = nodosGrafo.length;
		// Inicializamos la estructura conjuntos disjuntos.
		this.conjuntoDisjunto = new ConjuntosDisjuntos(cantidadNodos, conHeuristica);
		// Para cada nodo del grafo...
		for(int i = 0; i < this.cantidadNodos; i++){// cant nodos
			Nodo n = nodosGrafo[i];
			// Creamos un conjunto para cada uno.
			this.conjuntoDisjunto.makeSet(n);
			n.getArcos().start();
			// Para cada arco del nodo...
			while(n.getArcos().hasNext()) {// Cantidad de arcos
				IArco arc = n.getArcos().next();
				// Si no esta marcado...
				if(arc.getMarca() == 0) {
					// Lo agregamos a la lista de arcos.
					this.listaArcos.add(arc);
					// Y lo marcamos.
					arc.setMarca(1);
				}
				else {
					// Si esta marcado, lo desmarcamos.
					arc.setMarca(0);
				}
			}
		}
		// Inicializamos la lista que contendra el arbol de cubrimiento.
		this.arbolCubrimiento = new Lista<IArco>();
		// Ejecutamos un heapsort para ordenar la lista de arcos.
		this.HeapSort(); //n log n
		// Iniciamos el algoritmo de Kruskal.
		this.KruskalOrdenado(imprimirArbolCubrimiento);
	}
	
	/**
	 * Metodo utilizado para iniciar las estructuras que utiliza Kruskal con un min heap y
	 * luego iniciar el algoritmo de Kruskal. Al finalizar, la lista arbolCubrimiento contendra el resultado.
	 * @param conHeuristica Booleano que determina si se utilizan heuristicas en el conjunto disjunto.
	 * @param imprimirArbolCubrimiento Booleano que determina si se imprime la lista que representa el arbol de cubrimiento al finalizar.
	 */
	public void iniciarKruskalHeap (boolean conHeuristica, boolean imprimirArbolCubrimiento) {
		// Inicializamos la lista de arcos.
		this.listaArcos = new Lista<IArco>();
		// Obtenemos la cantidad de nodos del grafo.
		Nodo[] nodosGrafo = this.grafo.getNodos();
		this.cantidadNodos = nodosGrafo.length;
		// Inicializamos la estructura conjuntos disjuntos.
		this.conjuntoDisjunto = new ConjuntosDisjuntos(cantidadNodos, conHeuristica);
		// Inicializamos el heap que contendra los arcos.
		this.heapArcos = new Heap(this.grafo.getCantidadArcos());
		// Para cada nodo del grafo...
		for(int i = 0; i < this.cantidadNodos; i++){// cant nodos
			Nodo n = nodosGrafo[i];
			// Creamos un conjunto para cada uno.
			this.conjuntoDisjunto.makeSet(n);
			n.getArcos().start();
			// Para cada arco de ese nodo...
			while(n.getArcos().hasNext()) {// cant arcos
				IArco arc = n.getArcos().next();
				// Si no esta marcado...
				if(arc.getMarca() == 0) {
					// Lo agregamos al heap de arcos.
					this.heapArcos.insert(arc);//cant arcos
					// Y lo marcamos.
					arc.setMarca(1);
				}
				else {
					// Si esta marcado, lo desmarcamos.
					arc.setMarca(0);
				}
			}
		}
		// Inicializamos la lista que contendra el arbol de cubrimiento.
		this.arbolCubrimiento = new Lista<IArco>();
		// Iniciamos el algoritmo de Kruskal.
		this.KruskalHeap(imprimirArbolCubrimiento);
	}
	
	/**
	 * Metodo que se encarga de realizar el recorrido BFS del grafo.
	 * Al finalizar, en los arreglos padre y nivel quedan los resultados del recorrido.
	 * @param imprimirPadreyNivel Booleano que determina si se imprimen los arreglos padre y nivel al finalizar el recorrido.
	 */
	private void BFS(boolean imprimirPadreyNivel) {
		Nodo[] nodosGrafo = this.grafo.getNodos();
		// Marcamos los nodos del grafo como blancos.
		for(int i = 0; i < this.cantidadNodos; i++) { // O(N) , N = cantidad nodos
			Nodo n = nodosGrafo[i];
			n.setMarca(Color.WHITE); // O(1)
		}
		// Creamos una cola para manejar los nodos.
		Cola<INodo> Q = new Cola<INodo>(); // O(1)
		// Por cada nodo en la lista de nodos...
		for(int i = 0; i < this.cantidadNodos; i++) { // O(N) , N = cantidad nodos
			Nodo n = nodosGrafo[i];
			// Si es un nodo blanco...
			if(n.getMarca().equals(Color.WHITE)) { // O(1)
				// Lo marcamos en gris.
				n.setMarca(Color.GRAY); // O(1)
				// Lo agregamos a la cola.
				Q.addLast(n); // O(1)
				// Y comenzamos el proceso de visitar.
				this.visitarBF(Q);
			}
		}
		// Si la impresion de los arreglos padre y nivel estan habilitadas, imprimimos.
		if(imprimirPadreyNivel) {
			System.out.println("Recorrido BFS completado correctamente.");
			System.out.print("Padre: [ ");
			for(int i = 0; i < this.padre.length; i++) {
				System.out.print(this.padre[i] + " ");
			}
			System.out.println("]");
			System.out.print("Nivel: [ ");
			for(int i = 0; i < this.nivel.length; i++) {
				System.out.print(this.nivel[i] + " ");
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
		while(!Q.vacia()) { // O(A) , A = cantidad de arcos.
			// Obtenemos el nodo del tope.
			INodo u = Q.tope(); // O(1)
			// Obtenemos la lista de arcos de ese nodo.
			Lista<IArco> arcList = u.getArcos(); // O(1)
			arcList.start(); // O(1)
			// Mientras haya arcos en esa lista...
			while(arcList.hasNext()) { // O(A) , A = cantidad de arcos.
				// Obtenemos un arco.
				IArco arc = arcList.next(); // O(1)
				// Obtenemos el nodo que derecho de ese arco.
				INodo w = arc.getNodoDerecho(); // O(1)
				// Si el nodo derecho, es el nodo donde empezamos.
				if(u.getID() == w.getID()) { // O(1)
					// Obtenemos el izquierdo.
					w = arc.getNodoIzquierdo(); // O(1)
				}
				// Si el nodo obtenido es de color blanco.
				if(w.getMarca().equals(Color.WHITE)) { // O(1)
					// Lo marcamos en gris.
					w.setMarca(Color.GRAY); // O(1)
					// Seteamos como padre, el nodo anterior.
					this.padre[w.getID()] = u.getID(); // O(1)
					// Seteamos como nivel, el nivel del padre mas uno.
					this.nivel[w.getID()] = this.nivel[u.getID()] + 1; // O(1)
					// Y lo agregamos a la cola.
					Q.addLast(w); // O(1)
				}
			}
			// Si la lista de arcos se vacia quiere decir que ya analizamos todos los adyacentes del nodo.
			// Por lo tanto, lo marcamos como negro.
			u.setMarca(Color.BLACK); // O(1)
			// Y lo sacamos de la cola.
			Q.deleteFirst(); // O(1)
		}
	}
	
	/**
	 * Metodo utilizado para ordenar la lista de arcos del grafo.
	 * Al finalizar quedaran los arcos ordenados de menor a mayor
	 * en el arreglo arcosOrdenados.
	 */
	private void HeapSort() {
		// Creo un heap.
		IHeap heap = new Heap(this.listaArcos.size());
		// Inicializo el arreglo que contendra los arcos ordenados.
		this.arcosOrdenados = new IArco[this.listaArcos.size()];
		this.listaArcos.start();
		// Recorro la lista de arcos, insertando cada uno en el heap.
		while(this.listaArcos.hasNext()) {
			heap.insert(this.listaArcos.next());
		}
		int i=0;
		// Mientras el heap no este vacio.
		while(!heap.isEmpty()) {
			// Elimino arco minimo y lo guardo en el arreglo.
			this.arcosOrdenados[i] = heap.removeMin();
			i++;
		}
	}
	
	/**
	 * Metodo utilizado para calcular Kruskal utilizando un arreglo de arcos ordenado.
	 * Al finalizar quedara el arbol de cubrimiento en la lista arbolCubrimiento.
	 * @param imprimirArbolCubrimiento Booleano que determina si se imprime la lista arbolCubrimiento al finalizar.
	 */
	private void KruskalOrdenado(boolean imprimirArbolCubrimiento) {
		// Contador para la cantidad de nodos.
		int count = 0;
		// Contador para la cantidad de arcos.
		int i = 0;
		// Mientras no hayamos recorrido mas de la cantidad de nodos y mientras haya arcos sin recorrer.
		while((count < (this.cantidadNodos - 1)) && (i < this.arcosOrdenados.length)) {
			// Obtenemos cada arco.
			IArco arc = this.arcosOrdenados[i];
			// Obtenemos el representante del conjunto de cada uno de los nodos que conecta el arco.
			ElementoConjunto conjNodoIzq = this.conjuntoDisjunto.findSet(arc.getNodoIzquierdo());
			ElementoConjunto conjNodoDer = this.conjuntoDisjunto.findSet(arc.getNodoDerecho());
			// Si son diferentes los representantes del conjunto...
			if(conjNodoIzq.getID() != conjNodoDer.getID()) {
				// Unimos ambos conjuntos.
				this.conjuntoDisjunto.union(conjNodoIzq, conjNodoDer);
				// Y añadimos ese arco al arbol de cubrimiento.
				this.arbolCubrimiento.add(arc);
				count++;
			}
			i++;
		}
		// Si la impresion de los arreglos padre y nivel estan habilitadas, imprimimos.
		if(imprimirArbolCubrimiento) {
			System.out.println("Algoritmo de Kruskal completado correctamente.");
			this.printKruskal();
		}
	}
	
	/**
	 * Metodo utilizado para calcular Kruskal utilizando un min heap de arcos.
	 * Al finalizar quedara el arbol de cubrimiento en la lista arbolCubrimiento.
	 * @param imprimirArbolCubrimiento Booleano que determina si se imprime la lista arbolCubrimiento al finalizar.
	 */
	private void KruskalHeap(boolean imprimirArbolCubrimiento) {
		// Contador para la cantidad de nodos.
		int count = 0;
		// Mientras no hayamos recorrido mas de la cantidad de nodos y mientras haya arcos en el heap.
		while((count < (this.cantidadNodos - 1)) && (!this.heapArcos.isEmpty())) {
			// Obtenemos cada arco.
			IArco arc = this.heapArcos.removeMin();
			// Obtenemos el representante del conjunto de cada uno de los nodos que conecta el arco.
			ElementoConjunto conjNodoIzq = this.conjuntoDisjunto.findSet(arc.getNodoIzquierdo());
			ElementoConjunto conjNodoDer = this.conjuntoDisjunto.findSet(arc.getNodoDerecho());
			// Si son diferentes los representantes del conjunto...
			if(conjNodoIzq.getID() != conjNodoDer.getID()) {
				// Unimos ambos conjuntos.
				this.conjuntoDisjunto.union(conjNodoIzq, conjNodoDer);
				// Y añadimos ese arco al arbol de cubrimiento.
				this.arbolCubrimiento.add(arc);
				count++;
			}
		}
		// Si la impresion de los arreglos padre y nivel estan habilitadas, imprimimos.
		if(imprimirArbolCubrimiento) {
			System.out.println("Algoritmo de Kruskal completado correctamente.");
			this.printKruskal();
		}
	}
	
	/**
	 * Metodo auxiliar utilizado para imprimir el arbol de cubrimiento generado por Kruskal.
	 */
	private void printKruskal() {
		this.arbolCubrimiento.start();
		while(this.arbolCubrimiento.hasNext()) {
			IArco arc = this.arbolCubrimiento.next();
			System.out.print(arc.toString()+" ");
		}
		System.out.println();
	}
}

