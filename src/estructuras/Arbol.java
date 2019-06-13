package estructuras;

public class Arbol<E> {

	private class NArbol<N>{
		int e;
		ILista<NArbol<N>> sig;
		
		private NArbol(int e){
			this.e=e;
			sig=new Lista<NArbol<N>>();
		}		
	}
	
	NArbol<E> r;
	
	public Arbol(){
		r=null;
	}
	
	
	
}
