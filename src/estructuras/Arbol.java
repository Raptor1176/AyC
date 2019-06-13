package estructuras;

public class Arbol<E> {

	private class NArbol{
		int e;
		ILista sig;
		
		private NArbol(int e){
			this.e=e;
			sig=new Lista();
		}		
	}
	
	NArbol r;
	
	public Arbol(){
		r=null;
	}
	
	
	
}
