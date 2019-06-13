import estructuras.ILista;
import estructuras.Lista;

public class Test {

	public static void main(String[] args) {
		ILista<Integer> l= new Lista<Integer>();
		l.add(8);
		l.add(5);
		l.add(4);
		
		
		Object[] a= l.all();
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();
		
	}

}
