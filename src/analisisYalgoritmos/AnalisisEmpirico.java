package analisisYalgoritmos;
import java.io.IOException;
import grafos.Grafo;

public class AnalisisEmpirico{
	
	private static Grafo grafo;
	private static long start;
	private static long elapsedTime;

	private static int[] nodos= {100,100,100,100};
	private static int[] arcos1= {99,1000,2500,4000};			
	private static int[][] tiempo= new int[nodos.length][4];
	
	
	public static void main(String[] args) throws IOException {		
		try{			
			
			for(int t=0; t<10; t++){				
				for(int i=0; i<nodos.length; i++) {
					grafo = ConexionWebService.getGrafo(nodos[i],arcos1[i],true);
					Algoritmos algoritmos = new Algoritmos(grafo);
					
					/*
					System.out.println("Nodos: "+nodos[i]+" Arcos: "+ arcos1[i]);
					start = System.nanoTime();    
					elapsedTime = System.nanoTime() - start;
					//grafo.print();
					System.out.println("recorrido bfs: "+ elapsedTime);					
					 */
					
					start = System.nanoTime();    
					algoritmos.arbolDeCubrimientoOCH();
					elapsedTime = System.nanoTime() - start;
					tiempo[i][0]+= elapsedTime;					
					
					start = System.nanoTime();    
					algoritmos.arbolDeCubrimientoOSH();
					elapsedTime = System.nanoTime() - start;
					tiempo[i][1]+= elapsedTime;					
					
					start = System.nanoTime();
					algoritmos.arbolDeCubrimientoHCH();
					elapsedTime = System.nanoTime() - start;
					tiempo[i][2]+= elapsedTime;					
					
					start = System.nanoTime();    
					algoritmos.arbolDeCubrimientoHSH();
					elapsedTime = System.nanoTime() - start;
					tiempo[i][3]+= elapsedTime;					
					
						
					System.out.println();
	
				}
				
			}
			
			for(int i=0; i<nodos.length; i++){
				System.out.println("Nodos:"+ nodos[i]+" Arcos: "+arcos1[i]);
				
				System.out.print("Ordenado CON heuristica: ");
				System.out.println(tiempo[i][0]/10+" ");
				System.out.print("Ordenado SIN heuristica: ");
				System.out.println(tiempo[i][1]/10+" ");
				System.out.print("Heap CON heuristica: ");
				System.out.println(tiempo[i][2]/10+" ");
				System.out.print("Heap SIN heuristica: ");
				System.out.println(tiempo[i][3]/10+" ");
				
					
				System.out.println();
			}
			
			//grafo.print();
			//System.out.println("Comienzo recorrido BFS");			
			//long t1=System.nanoTime();
			//algoritmos.iniciarBFS(true);
			//long t2=System.nanoTime();
			//System.out.println("Tiempo BFS: "+(t2-t1)+" ms");
			
			//System.out.println(algoritmos.conexoBFS());
			//System.out.println(algoritmos.conexoDisjointSet());
			//algoritmos.arbolDeCubrimientoOCH();
			//algoritmos.arbolDeCubrimientoOSH();
			//algoritmos.arbolDeCubrimientoHCH();
			//algoritmos.arbolDeCubrimientoHSH();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	/*
	private static Grafo getGrafo(int nodos, int arcos) throws Exception {
		// TODO Auto-generated method stub
		String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos;
		Process process = Runtime.getRuntime().exec(consulta);			
		InputStream inputSt = process.getInputStream();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(inputSt).useDelimiter("\\A");
		
		String jsonString = s.hasNext() ? s.next() : "";
		//String jsonString= "{\"nodos\": [0, 1, 2, 3, 4, 5], \"arcos\": [[[1, 0], 46], [[0, 4], 790], [[4, 2], 440], [[1, 5], 276], [[3, 0], 700], [[3, 5], 820], [[0, 2], 996]]}";
		System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
		Gson gson = new GsonBuilder().create();
		try{
			
			Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
			Grafo g= new Grafo(gr);
			
			return g;
		} catch (Exception e) {
			throw new Exception(jsonString);
		}			
	}
	*/
	
}
