import estructuras.ILista;

public class Test {

	public static void main(String[] args) {
		ILista l= new Lista();
		l.add(8);
		l.add(5);
		l.add(4);
		
		
		int[] a= l.all();
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();
		
	}

}
