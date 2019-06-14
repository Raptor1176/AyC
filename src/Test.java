import java.util.Random;

import conjuntosDisjuntos.ConjuntoDisjunto;
import conjuntosDisjuntos.Elemento;
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
		/*
		int cant = 50;
		ElemTest e;		
		ConjuntoDisjunto conj=new ConjuntoDisjunto(cant,true);
		for (int i=0; i<cant;i++) {
			e=new ElemTest(i);
			conj.makeSet(e);
		}
		Elemento[] elems=conj.getElementos();
		
		System.out.println("*********** Estado inicial ***********\n");
		for(Elemento el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadre(el.getID())+" Rango: "+conj.getRango(el.getID()));
		}		
		
		Random ran=new Random();
		int uniones= 40;
		for (int n=1;n<=uniones;n++) {
			int e1=ran.nextInt(cant);
			int e2=ran.nextInt(cant);
			conj.union(elems[e1],elems[e2]);
		}
		
		System.out.println("\n*********** Estado luego de "+uniones+" uniones ***********\n");		
		for(Elemento el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadre(el.getID())+" Rango: "+conj.getRango(el.getID()));
		}
		*/
		
		Lista<Integer> q= new Lista<Integer>();
		
		q.add(8);
		q.add(4);
		q.add(3);
		q.add(5);
		
		q.start();
		while(q.hasNext())
			System.out.print(q.next());
		
			
	}

}
