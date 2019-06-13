package estructuras;

public class Arbol<E> {

	private class NArbol<N>{
		N e;
		ILista<NArbol<N>> sig;
		
		private NArbol(N e){
			this.e=e;
			sig=new Lista<NArbol<N>>();
		}		
	}
	
	NArbol r;
	
	public Arbol(){
		r=null;
	}
	
	
	
}
