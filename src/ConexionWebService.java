import java.net.*;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import analisisYalgoritmos.Algoritmos;
import grafos.Grafo;

import java.io.*;

public class ConexionWebService{	
	
	public static void main(String[] args) throws IOException {		
		try{
			Grafo grafo = getGrafo(10,20);
			Algoritmos algoritmos = new Algoritmos(grafo);
			grafo.print();
			algoritmos.BFS(true);			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	private static Grafo getGrafo(int nodos, int arcos) throws Exception {
		// TODO Auto-generated method stub
		
		//Esta parte esta sacada de: https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
		String url = "http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos;
		URL oracle = new URL(url);
		
		URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();        
        /*********** HASTA ACA *************/
        
        // pruebo si existe curl?????
        boolean exists = new File("C:/curl/curl.exe").exists(); 
        System.out.println(exists);
        
        // esto ami no me funciona..........
        /***** ESTO ES LO QUE HIZO MARTIN****/
        String curl="curl "+url;	
        System.out.println(curl);
		Process process = Runtime.getRuntime().exec(curl);			
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