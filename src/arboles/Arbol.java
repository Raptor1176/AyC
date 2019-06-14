package arboles;

import listas.Lista;

public class Arbol<E> {

	protected class NodoA<A>{		
		private A element;
		private Lista<NodoA<A>> children;
		private int rank;
		private NodoA<A> father;
		
		protected NodoA(A elem){
			this.element=elem;
			this.children=new Lista<NodoA<A>>();
			this.rank=0;
			this.father=null;
		}

		protected A getElement() {
			return element;
		}

		protected void setElement(A element) {
			this.element = element;
		}

		protected Lista<NodoA<A>> getChildren() {
			return children;
		}

		protected int getRank() {
			return rank;
		}

		protected void setRank(int rank) {
			this.rank = rank;
		}
		
		protected void incrementRank() {
			this.rank++;
		}

		protected NodoA<A> getFather() {
			return father;
		}

		protected void setFather(NodoA<A> father) {
			this.father = father;
		}
	}
		
	protected NodoA<E> root;
	protected int size;
	
	public Arbol(){
		this.root=null;
		this.size=0;
	}
	
	public E root() {
		return this.root.getElement();
	}
	
	public void setRoot(E elem) {
		NodoA<E> n=new NodoA<E>(elem);
		this.root=n;
		this.size++;
	}
	
	public NodoA<E> getFather(NodoA<E> n) {
		return n.getFather();
	}
	
	/**
	 * @return 0 si logr� insertar correctamente, 1 si falla
	 */
	public int addChildren(NodoA<E> n, E elem) {
		NodoA<E> nuevo=new NodoA<E>(elem);
		nuevo.setFather(n);
		n.incrementRank();
		n.getChildren().add(nuevo);
		return 0;
	}

	public int size() {
		return this.size;
	}	
}
