import java.util.Random;

import conjuntosDisjuntos.ConjuntoDisjunto;
import conjuntosDisjuntos.Elemento;
import heap.Heap;
import listas.Cola;
import listas.ILista;
import listas.Lista;
import conjuntosDisjuntos.ConjuntoDisjunto;
import conjuntosDisjuntos.Elemento;
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
		
		int cant = 10;
		int uniones= 20;
		
		ElemTest e;		
		ConjuntoDisjunto<Integer> conj=new ConjuntoDisjunto<Integer>(cant,true);
		
		// Hago CANT de make set
		for (int i=0; i<cant;i++) {
			e=new ElemTest(i);
			conj.makeSet(e);
		}
		
		
		Elemento<Integer>[] elems=conj.getElementos();
		
		// Imprimo estado inicial
		System.out.println("*********** Estado inicial ***********\n");
		for(Elemento<Integer> el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadre(el.getID())+" Rango: "+conj.getRango(el.getID()));
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
		for(Elemento<Integer> el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadre(el.getID())+" Rango: "+conj.getRango(el.getID()));
		}
		
		
		// Imprimo como quedaron los conjuntos y la cantidad
		System.out.println("\n*********** toString luego de "+uniones+" uniones ***********\n");
		System.out.println(conj.toString());
		
		
		int cantHeap= 15;
		
		Heap<Integer> h=new Heap<Integer>(cantHeap);
		ElemTest el;
		
		for(int i=0;i<cantHeap;i++) {			
			el=new ElemTest(ran.nextInt(50));
			h.insert(el);
		}
		
		System.out.println("Heap:\n"+h.toString());
		System.out.println("Heap size:"+h.size());
		System.out.println("Heap min:"+h.min().getElemento());
		
		
	}

}
