package analisisYalgoritmos;
import java.io.IOException;

import grafos.Grafo;

public class AnalisisEmpirico{
	
	private enum Casos {
		Ordenado_Con_Heuristica,
		Ordenado_Sin_Heuristica,
		Heap_Con_Heuristica,
		Heap_Sin_Heuristica		
	}
	
	private static Grafo grafo;
	private static long start;
	private static long elapsedTime;
	
	private static Algoritmos algoritmos;
/*
	private static int[] nodos= {400,400,400,400,400};
	private static int[] arcos= {399,5000,15000,30000,50000};			
	private static int[][] tiempo= {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
*//*
	private static int[] nodos= {500};
	private static int[] arcos= {499};			
	private static int[][] tiempo= {{0,0,0,0}};
	*/
	
	
	public static void main(String[] args) throws IOException {		
		try{
			
			int caso=3;			
			System.out.println("Nodos: "+Constantes.nodos[caso]+" Arcos: "+Constantes.arcos[caso]);
			grafo = ConexionWebService.GrafoConstante(caso);
			algoritmos = new Algoritmos(grafo);			
			
			//Casos test=Casos.Ordenado_Con_Heuristica;
			Casos test=Casos.Ordenado_Sin_Heuristica;
			//Casos test=Casos.Heap_Con_Heuristica;
			//Casos test=Casos.Heap_Sin_Heuristica;

			
			if(test==Casos.Ordenado_Con_Heuristica){
				start = System.currentTimeMillis();  
				algoritmos.arbolDeCubrimientoOCH();
				elapsedTime = System.currentTimeMillis() - start;
				System.out.print("Ordenado CON heuristica: ");
				System.out.println(elapsedTime);
			}

			if(test==Casos.Ordenado_Sin_Heuristica){
				start = System.currentTimeMillis();  
				algoritmos.arbolDeCubrimientoOSH();
				elapsedTime = System.currentTimeMillis() - start;
				System.out.print("Ordenado SIN heuristica: ");
				System.out.println(elapsedTime);
			}
			
			if(test==Casos.Heap_Con_Heuristica){
				start = System.currentTimeMillis();  
				algoritmos.arbolDeCubrimientoHCH();
				elapsedTime = System.currentTimeMillis() - start;
				System.out.print("Heap CON heuristica: ");
				System.out.println(elapsedTime);
			}
			
			if(test==Casos.Heap_Sin_Heuristica){
				start = System.currentTimeMillis();  
				algoritmos.arbolDeCubrimientoHSH();
				elapsedTime = System.currentTimeMillis() - start;
				System.out.print("Heap SIN heuristica: ");
				System.out.println(elapsedTime);
			}		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}
