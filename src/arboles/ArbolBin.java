package arboles;

public class ArbolBin<E> extends Arbol<E>{

	public int addChildren(NodoA<E> n, E elem) {
		if (n.getChildren().size()<2){
			NodoA<E> nuevo=new NodoA<E>(elem);
			nuevo.setFather(n);
			n.incrementRank();
			n.getChildren().add(nuevo);
			return 0;
		}
		return 1;
	}
	
	public NodoA<E> getHijoIzq(NodoA<E> n){
		return n.getChildren().first();
	}
	
	public NodoA<E> getHijoDer(NodoA<E> n){
		return n.getChildren().last();
	}
}
