import estructuras.ILista;

public class Lista implements ILista{

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
	
	public Lista(){
		p=null;
		u=null;
		t=0;
	}
	
	public void add(int e){
		if(p!=null){
			Nodo aux= new Nodo(e);
			u.sig=aux;
			u=u.sig;
		}else{
			p= new Nodo(e);
			u=p;
		}
		t++;
	}
	
	
	public int size(){
		return t;
	}
	@Override
	public int first() {		
		return p.e;
	}

	@Override
	public int last() {		
		return u.e;
	}

	
	@Override
	public int[] all() {
		int[] arr= null;
		if(t>0){			
			arr= new int[t];
			Nodo a=p;
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
