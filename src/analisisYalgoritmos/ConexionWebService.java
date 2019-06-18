package analisisYalgoritmos;
import java.net.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import grafos.Grafo;

import java.io.*;

public class ConexionWebService{	
	

	public static Grafo getGrafo(int nodos, int arcos, boolean conexo) throws Exception {		
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
				
		//jsonString= Constantes.jsons[3];
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

	public static Grafo GrafoConstante(int c){
		Gson gson = new GsonBuilder().create();
		try{							
			Grafo.GrafoObj gr = gson.fromJson(Constantes.jsons[c], Grafo.GrafoObj.class);
			Grafo g= new Grafo(gr);
			
			return g;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;			
	}
	
	
}