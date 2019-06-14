import java.util.Random;

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
		int cant = 6;
		int uniones= 9;
		
		ElemTest e;		
		ConjuntoDisjunto<Integer> conj=new ConjuntoDisjunto<Integer>(cant,true);
		for (int i=0; i<cant;i++) {
			e=new ElemTest(i);
			conj.makeSet(e);
		}
		Elemento<Integer>[] elems=conj.getElementos();
		
		System.out.println("*********** Estado inicial ***********\n");
		for(Elemento<Integer> el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadre(el.getID())+" Rango: "+conj.getRango(el.getID()));
		}		
		
		Random ran=new Random();
		
		for (int n=1;n<=uniones;n++) {
			int e1=ran.nextInt(cant);
			int e2=ran.nextInt(cant);
			conj.union(elems[e1],elems[e2]);
		}
		
		System.out.println("\n*********** Estado luego de "+uniones+" uniones ***********\n");		
		for(Elemento<Integer> el: elems) {
			System.out.println("E: "+el.getID()+" Padre: "+conj.getPadre(el.getID())+" Rango: "+conj.getRango(el.getID()));
		}
		
		System.out.println("\n*********** toString luego de "+uniones+" uniones ***********\n");
		System.out.println(conj.toString());
	}

}
