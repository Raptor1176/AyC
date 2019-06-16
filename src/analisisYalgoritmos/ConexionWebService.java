package analisisYalgoritmos;
import java.net.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import grafos.Grafo;

import java.io.*;

public class ConexionWebService{	
	
	public static void main(String[] args) throws IOException {		
		try{
			Grafo grafo = getGrafo(500,621,false);
			Algoritmos algoritmos = new Algoritmos(grafo);
			//grafo.print();
			System.out.println("Comienzo recorrido BFS");			
			long t1=System.currentTimeMillis();
			algoritmos.iniciarBFS(true);
			long t2=System.currentTimeMillis();
			System.out.println("Tiempo BFS: "+(t2-t1)+" ms");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	private static Grafo getGrafo(int nodos, int arcos, boolean conexo) throws Exception {		
		/* Esta parte esta sacada de: 
		 * https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
		 */
		String url = "http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos;
		if (conexo) {
			url+= "&conexo=1";
		}
		URL oracle = new URL(url);		
		URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        
		String jsonString=in.readLine(); //Leo la primer linea, que es el json del grafo obtenido
		
		in.close();
				
		//jsonString= "{\"nodos\": [0, 1, 2, 3, 4, 5], \"arcos\": [[[1, 0], 46], [[0, 4], 790], [[4, 2], 440], [[1, 5], 276], [[3, 0], 700], [[3, 5], 820], [[0, 2], 996]]}";
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