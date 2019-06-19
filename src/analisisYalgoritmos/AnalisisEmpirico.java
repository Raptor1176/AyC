package analisisYalgoritmos;
import java.io.IOException;

import grafos.Grafo;

public class AnalisisEmpirico{
	
	private enum Casos {
		Ordenado_Con_Heuristica,
		Ordenado_Sin_Heuristica,
		Heap_Con_Heuristica,
		Heap_Sin_Heuristica,
		Conexo_Disjoint,
		Conexo_BFS
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
			
			int caso=0;			
			//System.out.println("Nodos: "+Constantes.nodos[caso]+" Arcos: "+Constantes.arcos[caso]);
			grafo = ConexionWebService.GrafoConstante(caso);
			//grafo = ConexionWebService.getGrafo(100, 99, true);
			algoritmos = new Algoritmos(grafo);			
			Casos test;
			//test=Casos.Ordenado_Con_Heuristica;
			//test=Casos.Ordenado_Sin_Heuristica;
			//test=Casos.Heap_Con_Heuristica;
			//test=Casos.Heap_Sin_Heuristica;
			test= Casos.Conexo_Disjoint;
			

			if(test==Casos.Conexo_BFS){
				start = System.nanoTime();  
				boolean res= algoritmos.conexoBFS();
				elapsedTime = System.nanoTime() - start;
				System.out.println("Conexo BFS: "+res);
				System.out.println(elapsedTime/1000);
			}
			

			if(test==Casos.Conexo_Disjoint){
				start = System.nanoTime();  
				boolean res= algoritmos.conexoDisjointSet();
				elapsedTime = System.nanoTime() - start;
				System.out.println("Conexo DS: "+res);
				System.out.println(elapsedTime/1000);
			}
			
			
			if(test==Casos.Ordenado_Con_Heuristica){
				start = System.nanoTime();  
				algoritmos.arbolDeCubrimientoOCH();
				elapsedTime = System.nanoTime() - start;
				System.out.print("Ordenado CON heuristica: ");
				System.out.println(elapsedTime/1000);
			}

			if(test==Casos.Ordenado_Sin_Heuristica){
				start = System.nanoTime();  
				algoritmos.arbolDeCubrimientoOSH();
				elapsedTime = System.nanoTime() - start;
				System.out.print("Ordenado SIN heuristica: ");
				System.out.println(elapsedTime/1000);
			}
			
			if(test==Casos.Heap_Con_Heuristica){
				start = System.nanoTime();  
				algoritmos.arbolDeCubrimientoHCH();
				elapsedTime = System.nanoTime() - start;
				System.out.print("Heap CON heuristica: ");
				System.out.println(elapsedTime/1000);
			}
			
			if(test==Casos.Heap_Sin_Heuristica){
				start = System.nanoTime();  
				algoritmos.arbolDeCubrimientoHSH();
				elapsedTime = System.nanoTime() - start;
				System.out.print("Heap SIN heuristica: ");
				System.out.println(elapsedTime/1000);
			}		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}
