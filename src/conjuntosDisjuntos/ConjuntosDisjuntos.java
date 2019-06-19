package conjuntosDisjuntos;

public class ConjuntosDisjuntos implements IConjuntosDisjuntos{
		
	private int[] padres;
	private int[] rangos;
	private boolean heuristicas;
	
	// Necesito tambien un arreglo con los elementos para retornarlos en el findset
	private ElementoConjunto[] elementos;
	

	public ConjuntosDisjuntos(int tamanio, boolean usarHeuristicas) {		
		this.heuristicas=usarHeuristicas;
		this.padres= new int[tamanio];
		this.rangos= new int[tamanio];
		this.elementos=new ElementoConjunto[tamanio];
	}
	
	
	
	
	public void makeSet(ElementoConjunto elem) {
		// Creo un conjunto con elem como unico elemento
		int index=elem.getID(); // Obtengo el id que es el indice de todos los arreglos
		this.elementos[index]=elem; // Guardo elem en el arreglo de elementos
		this.padres[index]=index; // Seteo su padre a si mismo
		this.rangos[index]=0; // Seteo su rango en 0
	}
	
	public ElementoConjunto findSet(ElementoConjunto elem) {
		// Obtengo el indice del elemento
		int index=elem.getID();
		
		
		if(this.padres[index]==index) {			
			// Si el elemento tiene como padre a si mismo
			// entonces el elemento identificador es el mismo elemento
			return elem;
		}
		else {
			// Si no llamo recursivamente para hallar al elemento representante
			ElementoConjunto representante = findSet(this.elementos[this.padres[index]]);
			
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
	
	private void link(ElementoConjunto p1, ElementoConjunto p2) {
		// Tomo el indice de los arreglos (que es el id de cada elemento)
		int padreE1= p1.getID(); 
		int padreE2= p2.getID();	
		if (padreE1!=padreE2) {			
			// Si los padres son distintos ( no estan en el mismo conjunto )	
			
			if(heuristicas) {
				// Si estoy usando heuristicas
				
				// Si el rango de padreE1 es menor que el rango de padreE2
				if (rangos[padreE1] > rangos[padreE2]) {
					// Entonces muevo al conjunto que contiene a e1 
		        	// debajo del conjunto que contiene e2
					padres[padreE2] = padreE1;
				}
				// Sino, si el rango de padreE2 es menor que el de padreE1
				else if (rangos[padreE1] < rangos[padreE2]) {
					// Entonces muevo al conjunto que contiene a e2 
		        	// debajo del conjunto que contiene e1
					padres[padreE1] = padreE2;
				}
				else {
					// Si los rangos son iguales entonces
		        	// muevo uno conjunto debajo de otro, no importa cual
		            padres[padreE2] = padreE1; 
		  
		            // Luego incremento el rango al cual le puse al otro conjunto debajo
		            rangos[padreE1] = rangos[padreE1] + 1; 
				}
				
			}
			else {
				// Si no estoy usando heuristicas
				
				// Muevo un conjunto debajo de otro, sin importarme el rango
				padres[padreE2]=padreE1;
			}			
		}
	}
	
	
	public void union(ElementoConjunto e1, ElementoConjunto e2) {		
		
		link(findSet(e1),findSet(e2));
		
	}
		/*		
		// Obtengo los elementos padres (representantes) de los elementos		
		int padreE1= findSet(e1).getID();
		int padreE2= findSet(e2).getID();

		//System.out.println("Padre de "+index1 + " : " + padreE1);
		//System.out.println("Padre de "+index2 + " : " + padreE2);
		if(padreE1==padreE2) {
			// Mismos padres, ambos pertenecen al mismo conjunto
			// Entonces no hay que hacer nada
			return ;
		}
		else {
			if (this.heuristicas) {				
				/*
				 * Heuristica de union por rangos
				 *
			
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
				 *
				
				// Sino, seteo como padre de e2 al padre de e1
				this.padres[index2]=this.padres[index1];
			}
			
		}
	}*/
	
	/************************************************
	 * Solo para testing
	 */
	
	public int getPadres(int i) {
		return this.padres[i];
	}
	
	public int getRangos(int i) {
		return this.rangos[i];
	}
	
	public ElementoConjunto[] getElementos() {
		return this.elementos;
	}
	
	public ElementoConjunto getElemento(int i) {
		return this.elementos[i];
	}
	

	
	public String toString() {
		String s="Padres: [ ";
		for (int i=0; i<padres.length;i++) {
			s+=padres[i]+ " ";
		}
		s+="]\n";
		s+="Rangos: [ ";
		for (int j=0; j<elementos.length;j++) {
			s+=rangos[j]+" ";
		}
		s+="]";
		return s;
	}
	
	/******************************************/
	
	public boolean isOneSet() {
		boolean primeraRaiz=false;
		for (int i=0;i<padres.length;i++) {
			if(padres[i]==i) {
				if(!primeraRaiz)	
					primeraRaiz=true;
				else 
					return false;
			}
		}
		return true;
	}
	
	
}