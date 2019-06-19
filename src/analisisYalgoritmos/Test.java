package analisisYalgoritmos;

import java.util.Random;

import conjuntosDisjuntos.*;

public class Test {
	
	public static void main (String[] args) {
		
		class Elem implements ElementoConjunto{
			private int id;
			public Elem(int n) {
				id=n;
			}
			public int getID() {
				return id;
			}
		}
		
		
		int cant=3000;
		boolean usoH=true;
		//int uniones=15000;
		int uniones=cant-1;
		
		boolean imprimirCD=false;
		
		
		IConjuntosDisjuntos conj=new ConjuntosDisjuntos(cant,usoH);		
		Random ran=new Random();
		ElementoConjunto[] elems=new ElementoConjunto[cant];
		ElementoConjunto e1,e2;
		
		// Creo los elementos de test
		for (int i=0; i<cant;i++) {
			elems[i]=new Elem(i);
		}	
		
		
		long t1=System.nanoTime();
		
		for (int i=0; i<cant;i++) {			
			conj.makeSet(elems[i]);
			// Hago los make set
		}		
		for(int i=0;i<uniones;i++) {
			// Hago algunas uniones (simulo kruskal)
			e1=conj.findSet(elems[ran.nextInt(cant)]);
			e2=conj.findSet(elems[ran.nextInt(cant)]);
			if(e1.getID()!=e2.getID()) {
				conj.union(e1,e2);
			}
		}
		
		long t2=System.nanoTime();
		
		System.out.println("Cantidad de elementos: "+ cant
				+ "\nUsando heuristica? "+ usoH
				+ "\nUniones: "+ uniones
				+ "\nUn solo conjunto? "+ conj.isOneSet()
				+ "\nTiempo: "+ ((t2-t1)/1000) + " (nanos/1000)"
				+ "\n");
		if (imprimirCD) System.out.println(conj.toString());
	}

}
