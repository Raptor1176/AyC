package estructuras;
import estructuras.ILista;

public class Lista implements ILista{

<<<<<<< HEAD
	private class Nodo{
		int e;
		Nodo sig;
		
		private Nodo(int e){
			this.e=e;
			sig=null;
		}
	}
	
	private Nodo p;	
	private Nodo u;	
	private int t;
=======
	private class Nodo<N>{
		N elemento;
		Nodo<N> siguiente;
		
		private Nodo(N elem){
			this.elemento=elem;
			this.siguiente=null;
		}
	}
	
	private Nodo<E> primero;	
	private Nodo<E> ultimo;	
	private int tamaño;
>>>>>>> bab64e37a97d04c1e1b01118bb58a255bdd938fc
	
	public Lista(){
		this.primero=null;
		this.ultimo=null;
		this.tamaño=0;
	}
	
<<<<<<< HEAD
	public void add(int e){
		if(p!=null){
			Nodo aux= new Nodo(e);
			u.sig=aux;
			u=u.sig;
		}else{
			p= new Nodo(e);
			u=p;
=======
	public void add(E e){
		if(this.primero!=null){
			Nodo<E> nuevo= new Nodo<E>(e);
			this.ultimo.siguiente=nuevo;
			this.ultimo=this.ultimo.siguiente;
		}else{
			this.primero= new Nodo<E>(e);
			this.ultimo=primero;
>>>>>>> bab64e37a97d04c1e1b01118bb58a255bdd938fc
		}
		tamaño++;
	}
	
	
	public int size(){
		return tamaño;
	}
<<<<<<< HEAD
	@Override
	public int first() {		
		return p.e;
	}

	@Override
	public int last() {		
		return u.e;
=======

	public E first() {		
		return primero.elemento;
	}


	public E last() {		
		return ultimo.elemento;
>>>>>>> bab64e37a97d04c1e1b01118bb58a255bdd938fc
	}

	@Override
<<<<<<< HEAD
	public int[] all() {
		int[] arr= null;
		if(t>0){			
			arr= new int[t];
			Nodo a=p;
=======
	public E[] all() {
		E[] arr= null;
		if(tamaño>0){			
			arr= (E[]) new Object[t];
			Nodo<E> a=p;
>>>>>>> bab64e37a97d04c1e1b01118bb58a255bdd938fc
			int i=0;
			while(a!=null){
				arr[i]=a.e;
				i++;
				a=a.sig;
			}
		}
		return arr;	
	}

	
}
