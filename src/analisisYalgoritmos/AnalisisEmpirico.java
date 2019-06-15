package analisisYalgoritmos;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grafos.Grafo;


public class AnalisisEmpirico{
	
	public static void main(String[] args) throws IOException {
		
		try{
			Grafo grafo = getGrafo(10,20);
			Algoritmos algoritmos = new Algoritmos(grafo);
			grafo.print();
			algoritmos.BFS(true);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		/*
		 * Generar varios grafos de diferente configuraci�n y buscar 
		 * �rbol de cubrimiento minimal para cada uno. 
		 * 
		 * Medir el rendimiento usando timestamps.
		 * 
		 */
		
		
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