package grafos;

import java.awt.Color;
import java.util.ArrayList;

import listas.Cola;
import listas.Lista;

public class Grafo implements IGrafo {
	
	private Nodo[] nodos;
	private int[] padre;
	private int[] nivel;

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
			Arco arc1 = new Arco(nodI, nodD, peso);
			//Arco arc2 = new Arco(nodD, nodI, peso);
			nodI.addArco(arc1);			
			nodD.addArco(arc1);
		}		
	}
	
	public void BFS() {
		this.padre = new int[this.nodos.length];
		for(int i=0; i < this.padre.length; i++) {
			this.padre[i] = -1;
		}
		this.nivel = new int[this.nodos.length];
		for(Nodo n: this.nodos) {
			n.setMarca(Color.WHITE);
		}
		Cola<INodo> Q = new Cola<INodo>();
		for(Nodo n: this.nodos) {
			if(n.getMarca().equals(Color.WHITE)) {
				n.setMarca(Color.GRAY);
				Q.addLast(n);
				this.visitarBF(Q);
			}
		}
		System.out.print("Padre: ");
		
		for(int i: this.padre) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print("Nivel: ");
		for(int i: this.nivel) {
			System.out.print(i + " ");
		}
	}
	
		
	public void visitarBF(Cola<INodo> Q) {
		while(!Q.vacia()) {
			INodo u = Q.tope();
			Lista<IArco> arcList = u.getArcos();
			arcList.start();
			while(arcList.hasNext()) {
				IArco arc = arcList.next();
				//System.out.print("-"+arc.getPeso()+"-");
				INodo w = arc.getNodoDerecho();
				
				if(u.getID() == w.getID()) {
					w = arc.getNodoIzquierdo();
				}
				
				if(w.getMarca().equals(Color.WHITE)) {
					w.setMarca(Color.GRAY);
					this.padre[w.getID()] = u.getID();
					//System.out.println(w.getID());
					this.nivel[w.getID()] = this.nivel[u.getID()] + 1;
					Q.addLast(w);
					//System.out.print("+" + w.getID()+"+");
				}
			}
			u.setMarca(Color.BLACK);
			Q.deleteFirst();
			//System.out.print("-" +Q.deleteFirst().getID()+"-");
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
