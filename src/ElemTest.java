import ConjuntosDisjuntos.Elemento;

public class ElemTest implements Elemento<Integer>{
	
	private int id;
	
	public ElemTest(int id) {
		this.id=id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public Integer getElemento() {
		return this.id;
	}

}
