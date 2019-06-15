import java.util.Random;

import heap.ElemTest;
import heap.Heap;
import conjuntosDisjuntos.ConjuntosDisjuntos;
import conjuntosDisjuntos.ElementoConjunto;

import listas.Cola;
import listas.ILista;
import listas.Lista;

public class Test {

	public static void main(String[] args) {
		
		/* TEST LISTA
		
		
		ILista<Integer> l= new Lista<Integer>();
		l.add(8);
		l.add(5);
		l.add(4);
		
		
		Object[] a= l.all();
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();

		*/
		
		
		/*
		 * TEST CONJUNTOS DISJUNTOS
		 */
		
		int cant = 24;
		int uniones= 82;
		
		ElemTest e;		
		ConjuntosDisjuntos conj=new ConjuntosDisjuntos(cant,true);
		
		System.out.println("*********** Creo un ConjuntosDisjuntos de "+cant+" elementos ***********\n");
		System.out.println("Hago "+cant+" makeSet() de elementos con id desde 0 hasta "+(cant-1)+" ***********\n");
		// Hago CANT de make set
		for (int i=0; i<cant;i++) {
			e=new ElemTest(i);
			conj.makeSet(e);
		}
		
		
		ElementoConjunto[] elems=conj.getElementos();
		
		// Imprimo estado inicial
		System.out.println("*********** Estado inicial ***********\n");
		for(ElementoConjunto el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadres(el.getID())+" Rango: "+conj.getRangos(el.getID()));
		}		
		
		// Hago UNIONES de uniones random 
		Random ran=new Random();		
		for (int n=1;n<=uniones;n++) {
			int e1=ran.nextInt(cant);
			int e2=ran.nextInt(cant);
			conj.union(elems[e1],elems[e2]);
		}
		
		// Imprimo el estado final
		System.out.println("\n*********** Estado luego de "+uniones+" uniones ***********\n");		
		for(ElementoConjunto el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadres(el.getID())+" Rango: "+conj.getRangos(el.getID()));
		}
		
		
		// Imprimo como quedaron los conjuntos y la cantidad
		System.out.println("\n*********** toString luego de "+uniones+" uniones ***********\n");
		System.out.println(conj.toString());
		
		
		int cantHeap= 24;
		int maxRandom= 500;
		
		Heap h=new Heap(cantHeap);
		ElemTest el;
		System.out.println("\n\n\n******** Creo heap de max "+cantHeap+" elementos *******");
		System.out.println("Inserto "+(cantHeap-1)+" elementos randoms de 0 a "+maxRandom);
		for(int i=0;i<cantHeap;i++) {			
			el=new ElemTest(ran.nextInt(maxRandom));
			h.insert(el);
		}
		
		System.out.println("\n******* Heap antes de eliminar **********");
		System.out.println("\nHeap:\n"+h.toString());
		System.out.println("Heap size: "+h.size());
		System.out.println("Heap min => "+h.min().getID());
		
		
		int cantRemoves=6;
		System.out.println("\n******* Hago "+cantRemoves+" removeMin() **********");
		for (int i=1;i<=cantRemoves;i++) {
			System.out.println("Heap removemin => "+h.removeMin().getID());
			//System.out.println("Heap => "+h.toString());
		}
		System.out.println("\n******* Heap despues de hacer "+cantRemoves+" removeMin() **********");
		
		System.out.println("\nHeap:\n"+h.toString());
		System.out.println("\nHeap size: "+h.size());
		System.out.println("Heap min  => "+h.min().getID());
		
	}

}
