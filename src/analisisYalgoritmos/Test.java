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
		
		int cant=5000;
		int uniones=80000;
		boolean usoH=false;
		
		ConjuntosDisjuntos conj=new ConjuntosDisjuntos(cant,usoH);		
		Random ran=new Random();
		ElementoConjunto[] elems=new ElementoConjunto[cant];
		
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
			// Hago algunas uniones
			conj.union(elems[ran.nextInt(cant)],elems[ran.nextInt(cant)]);
		}
		
		long t2=System.nanoTime();
		
		System.out.println("Cantidad de elementos: "+ cant
				+ "\nUsando heuristica? "+ usoH
				+ "\nUniones: "+ uniones
				+ "\nUn solo conjunto? "+ conj.isOneSet()
				+ "\nTiempo: "+ ((t2-t1)/1000) + " (nanos/1000)"
				+ "\n");
		//System.out.println(conj.toString());
	}

}
