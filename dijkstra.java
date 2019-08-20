// Éste es el algoritmo de ruta más corta de Dijkstra.
// Dado un gráfo y un vértice fuente, encuentra las rutas más cortas
// desde la fuente a todos los vértices en el gráfo dado.

// Se genera un arc(árbol de ruta más corta) con la fuente dada como raíz,
// Mantenemos dos conjuntos(sets), uno contiene vértices incluidos en el arc,
// y el otro incluye vértices aún no incluidos en el arc.
// En cada paso del algoritmo, encontramos un vértice que está en el otro
// conjunto (conjunto de aún no incluido) y tiene una distancia mínima
// desde la fuente.

// A continuación, se detallan los pasos utilizados:

// 1) Cree un conjunto arcSet (conjunto de árbol de ruta más corta) que realice
// un seguimiento de los vértices incluidos en el árbol de ruta más corta,
// es decir, cuya distancia mínima desde la fuente se calcula y finaliza.
// Inicialmente, este conjunto está vacío.

// 2) Asigne un valor de distancia a todos los vértices en el gráfico de entrada.
// Inicialice todos los valores de distancia como INFINITO. Asigne el valor de
// distancia como 0 para el vértice de origen para que se seleccione primero.

// 3) Si bien arcSet no incluye todos los vértices
// 		A) Elija un vértice U que no esté allí en arcSet y que tenga un valor
// 			de distancia mínima.
// 		B) Incluye U para arcSet.
// 		C) Actualizar el valor de distancia de todos los vértices adyacentes
// 			de U. Para actualizar los valores de distancia, recorra todos los
// 			vértices adyacentes. Para cada vértice adyacente v, si la suma del
// 			valor de distancia de U(desde la fuente) y el valor del grafo[U][v],
// 			es menor que el valor de distancia de v, entonces actualice el
// 			valor de distancia de v.

// Usaremos un array booleano arcSet[] para representar el conjunto de vértices
// incluidos en ARC. Si un valor arcSet[v] es verdadero, entonces el vértice v
// se incluye en ARC, de lo contrario no. En el array dist[] almacenamos valores
// de distancia más corta de todos los vértices.

import java.util.*;
import java.lang.*;
import java.io.*;

class RutaMasCorta {
	static final int V=9;

	char matriz[][] = {};

	void imprimirSolucion(int dist[], int n){

		System.out.println("Vértice		Distancia desde la fuente");

		for(int i=0 ; i<n ; i++){
			System.out.println(i + "		  " + dist[i]);
		}
	}

	int distanciaMinima(int dist[], Boolean arcSet[]){
		int min = Integer.MAX_VALUE, min_vert_indice = -1, v;

		for(v=0 ; v<V ; v++){
			if(arcSet[v] == false && dist[v] <= min){
				min = dist[v];
				min_vert_indice = v;
			}
		}
		return min_vert_indice;
	}

	void dijkstra(int grafo[][], int fuente){
		int dist[] = new int[V];
		int i, v, contador;
		Boolean arcSet[] = new Boolean[V];

		for(i=0 ; i<V ; i++){
			dist[i] = Integer.MAX_VALUE;
			arcSet[i] = false;
		}

		dist[fuente] = 0;

		for(contador=0 ; contador<V-1 ; contador++){
			// Calcula para cada iteración vertU como el
			// próximo vértice adyacente no calculado
			// a la fuente y sus adyacentes.
			int vertU = distanciaMinima(dist, arcSet);

			arcSet[vertU] = true;

			for(v=0 ; v<V; v++){
				if(!arcSet[v] && grafo[vertU][v]!=0 &&
					dist[vertU]!=Integer.MAX_VALUE &&
					dist[vertU]+grafo[vertU][v]<dist[v]){
						dist[v] = dist[vertU] + grafo[vertU][v];

				}
			}
		}

		imprimirSolucion(dist, V);
	}

	public static void main(String[] args){
		// Para el ejemplo usamos una matriz simétrica de
		// nueve vértices númerados del 0 al 8, los valores
		// que vaya ocupando la matriz serán las aristas que
		// conectan a los vértices.
		// La matriz debe ser cuadrada y los valores que tome
		// positivos.
		// 				Vértices	 0	 1	 2	 3	 4	 5	 6	 7	 8
		int grafo[][] = new int[][]{{0 , 4 , 0 , 0 , 0 , 0 , 0 , 8 , 0},	// V0
									{4 , 0 , 8 , 0 , 0 , 0 , 0 , 11, 0},	// V1
									{0 , 8 , 0 , 7 , 0 , 4 , 0 , 0 , 2},	// V2
									{0 , 0 , 7 , 0 , 9 , 14, 0 , 0 , 0},	// V3
									{0 , 0 , 0 , 9 , 0 , 10, 0 , 0 , 0},	// V4
									{0 , 0 , 4 , 14, 10, 0 , 2 , 0 , 0},	// V5
									{0 , 0 , 0 , 0 , 0 , 2 , 0 , 1 , 6},	// V6
									{8 , 11, 0 , 0 , 0 , 0 , 1 , 0 , 7},	// V7
									{0 , 0 , 2 , 0 , 0 , 0 , 6 , 7 , 8}		// V8
								};

		RutaMasCorta ruta = new RutaMasCorta();

		// Llamamos a la función con el grafo dado y una fuente.
		ruta.dijkstra(grafo, 0);
	}
}

// Uso:
// 		$ javac dijkstra.java
// 		$ java RutaMasCorta

// El código calcula la distancia más corta, pero no imprime
// la ruta que va haciéndo.

// Para fuente igual a vértice 0 imprime:
// Distancia desde el vértice 0 al 0: 0
// Distancia desde el vértice 0 al 1: 4		---- V0 -> V1
// Distancia desde el vértice 0 al 2: 12	---- V0 -> V1 -> V2
// Distancia desde el vértice 0 al 3: 19	---- V0 -> V1 -> V2 -> V3
// Distancia desde el vértice 0 al 4: 21	---- V0 -> V7 -> V6 -> V5 -> V4
// Distancia desde el vértice 0 al 5: 11	---- V0 -> V7 -> V6 -> V5
// Distancia desde el vértice 0 al 6: 9		---- V0 -> V7 -> V6
// Distancia desde el vértice 0 al 7: 8		---- V0 -> V7
// Distancia desde el vértice 0 al 8: 14	---- V0 -> V1 -> V2 -> V8


// Para fuente igual a vértice 5 imprime:
// Distancia desde el vértice 5 al 0: 11	---- V5 -> V6 -> V7 -> V0
// Distancia desde el vértice 5 al 1: 12	---- V5 -> V2 -> V1
// Distancia desde el vértice 5 al 2: 4		---- V5 -> V2
// Distancia desde el vértice 5 al 3: 11	---- V5 -> V2 -> V3
// Distancia desde el vértice 5 al 4: 10	---- V5 -> V4
// Distancia desde el vértice 5 al 5: 0
// Distancia desde el vértice 5 al 6: 2		---- V5 -> V6
// Distancia desde el vértice 5 al 7: 3		---- V5 -> V6 -> V7
// Distancia desde el vértice 5 al 8: 6		---- V5 -> V2 -> V8


		/*
					V0   V1   V2   V3   V4   V5   V6   V7   V8
				----------------------------------------------
			V0  | 0	 , 4  , 0  , 0  , 0  , 0  , 0  , 8	, 0	 |
			V1	| 4	 , 0  , 8  , 0  , 0  , 0  , 0  , 11 , 0	 |
			V2	| 0	 , 8  , 0  , 7  , 0  , 4  , 0  , 0	, 2	 |
			V3	| 0	 , 0  , 7  , 0  , 9  , 14 , 0  , 0	, 0	 |
			V4	| 0	 , 0  , 0  , 9  , 0  , 10 , 0  , 0	, 0	 |
			V5	| 0	 , 0  , 4  , 14 , 10 , 0  , 2  , 0	, 0	 |
			V6	| 0	 , 0  , 0  , 0	, 0  , 2  , 0  , 1	, 6	 |
			V7	| 8	 , 11 , 0  , 0	, 0  , 0  , 1  , 0	, 7	 |
			V8	| 0	 , 0  , 2  , 0	, 0  , 0  , 6  , 7	, 0	 |
				----------------------------------------------
		*/
