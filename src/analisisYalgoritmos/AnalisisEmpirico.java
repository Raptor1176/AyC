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

	
	public static void main(String[] args) throws IOException {		
		try{
			
			int caso=4;			
			//System.out.println("Nodos: "+Constantes.nodos[caso]+" Arcos: "+Constantes.arcos[caso]);
			//grafo = ConexionWebService.GrafoConstante(caso);
			grafo = ConexionWebService.getGrafo(500, 124750, true);
			//algoritmos = new Algoritmos(grafo);			
			Casos test;
			//test=Casos.Ordenado_Con_Heuristica;
			//test=Casos.Ordenado_Sin_Heuristica;
			test=Casos.Heap_Con_Heuristica;
			//test=Casos.Heap_Sin_Heuristica;
			//test= Casos.Conexo_BFS;
			//test= Casos.Conexo_Disjoint;

			algoritmos = new Algoritmos(grafo);	

			if(test==Casos.Conexo_BFS){
				start = System.currentTimeMillis();  
				boolean res= algoritmos.conexoBFS();
				elapsedTime = System.currentTimeMillis() - start;
				System.out.println("Conexo BFS: "+res);
				System.out.println(elapsedTime);
			}
			

			if(test==Casos.Conexo_Disjoint){
				start = System.currentTimeMillis();  
				boolean res= algoritmos.conexoDisjointSet();
				elapsedTime = System.currentTimeMillis() - start;
				System.out.println("Conexo DS: "+res);
				System.out.println(elapsedTime);
			}
			
			
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
