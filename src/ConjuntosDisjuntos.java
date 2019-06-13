import estructuras.Elemento;

public class ConjuntosDisjuntos {
	
	// Necesito tambien un arreglo con los elementos para retornarlos en el findset
	private Elemento[] elementos;
	private int[] padres;
	private int[] rangos;
	private boolean heuristicas;

	public ConjuntosDisjuntos(int tamanio, boolean usarHeuristicas) {		
		this.heuristicas=usarHeuristicas;
		this.padres= new int[tamanio];
		this.rangos= new int[tamanio];
		this.elementos=new Elemento[tamanio];
	}
	
	
	/************************************************
	 * Solo para testing
	 */
	public Elemento[] getElementos() {
		return this.elementos;
	}
	
	public Elemento getElemento(int i) {
		return this.elementos[i];
	}
	
	public int getPadre(int i) {
		return this.padres[i];
	}
	
	public int getRango(int i) {
		return this.rangos[i];
	}
	
	public void makeSet(Elemento elem) {		
		int index=elem.getID();
		this.elementos[index]=elem;
		this.padres[index]=index;
		this.rangos[index]=0;		
	}
	
	/******************************************/
	
	public Elemento findSet(Elemento elem) {
		// Obtengo el indice del elemento
		int index=elem.getID();
		
		
		if(this.padres[index]==index) {			
			// Si el elemento tiene como padre a si mismo
			// entonces el elemento identificador es el mismo elemento
			return elem;
		}
		else {
			// Si no llamo recursivamente para hallar al elemento representante
			Elemento representante = findSet(this.elementos[this.padres[index]]);
			
			if (this.heuristicas) {
				/*
				 * Heuristica de compresion de caminos
				 */					
				// Obtengo el indice del representante
				int indexRepresentante= representante.getID();
				
				// Acorto el camino poniendo como padre al padre del representante (que es el mismo)
				this.padres[index]=this.padres[indexRepresentante];				
			}
			
			// Retorno el elemento representante
			return representante;
		}
	}	
	
	public void union(Elemento e1, Elemento e2) {
		// Obtengo los indices de los elementos
		int index1= e1.getID();
		int index2= e2.getID();
		
		// Obtengo los elementos padres (representantes) de los elementos		
		int padreE1= this.padres[index1];
		int padreE2= this.padres[index2];
		
		if(padreE1==padreE2) {
			// Mismos padres, ambos pertenecen al mismo conjunto
			// Entonces hay que hacer nada
			return ;
		}
		else {
			
			if (this.heuristicas) {				
				/*
				 * Heuristica de union por rangos
				 */
			
				// Si el rango de e1 es menor que el rango de e2
		        if (this.rangos[padreE1] < this.rangos[padreE2]) {	  
		        	// Entonces muevo al conjunto que contiene a e1 
		        	// debajo del conjunto que contiene e2
		            this.padres[padreE1] = padreE2;
				}
		        // Sino, si el rango de e2 es menor que el de e1
		        else if (this.rangos[padreE2] < this.rangos[padreE1]) {
		        	// Entonces muevo al conjunto que contiene a e2 
		        	// debajo del conjunto que contiene e1
		            this.padres[padreE2] = padreE1; 
		        }
		        else {
		        	// Si los rangos son iguales entonces
		        	// muevo uno conjunto debajo de otro, no importa cual
		            this.padres[padreE2] = padreE1; 
		  
		            // Luego incremento el rango al cual le puse al otro conjunto debajo
		            this.rangos[padreE1] = this.rangos[padreE1] + 1; 
		        }	        
			}
			else {
				/*
				 * Sin heuristicas
				 */
				
				// Sino, seteo como padre de e2 al padre de e1
				this.padres[index2]=this.padres[index1];
			}
		}
	}
	
}