import estructuras.ILista;
import estructuras.Lista;
import estructuras.Elemento;

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
		int max = 20;
		ElemTest e;
		Elemento[] elems;
		ConjuntosDisjuntos conj=new ConjuntosDisjuntos(max,true);
		for (int i=0; i<max;i++) {
			e=new ElemTest(i);
			conj.makeSet(e);
		}
		elems=conj.getElementos();
	}

}
