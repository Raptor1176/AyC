package analisisYalgoritmos;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grafos.Grafo;

public class AnalisisEmpirico{
	
	public static void main(String[] args) throws IOException {		
		try{
			Random rnd = new Random();
			int[] nodos= new int[10];
			int[] arcos= new int[10];
			
			Grafo grafo;// = ConexionWebService.getGrafo(10,9,true);
			for(int i=0; i<10; i++) {
				nodos[i]=rnd.nextInt((i+1)*5);
				arcos[i]=nodos[i]-1 +rnd.nextInt((i+1)*5);
				grafo = ConexionWebService.getGrafo(nodos[i],arcos[i],true);
				
			}
						
			Algoritmos algoritmos = new Algoritmos(grafo);
			grafo.print();
			//System.out.println("Comienzo recorrido BFS");			
			//long t1=System.currentTimeMillis();
			//algoritmos.iniciarBFS(true);
			//long t2=System.currentTimeMillis();
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
}
