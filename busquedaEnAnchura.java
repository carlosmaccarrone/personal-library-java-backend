// Primera búsqueda en anchura para un grafo

// El primer recorrido transversal (o búsqueda) de un grafo es similar  
// al primer recorrido transversal de un árbol. El único problema es que, 
// a diferencia de los árboles, los grafos pueden contener ciclos, por lo
// que podemos volver al mismo vértice nuevamente. Para evitar procesar un 
// vértice más de una vez, utilizamos un array de booleanos 'visitado'. 
// Por simplicidad, suponemos que todos los vértices son accesibles desde 
// el vértice inicial.

// El contenedor de listas de STL se usa para almacenar listas de vértices 
// adyacentes y la cola de vértices necesarios para la busqueda.


// Programa en Java para imprimir el primer recorrido en anchura desde 
// un vértice fuente dado.
import java.io.*;
import java.util.*;

// Esta clase representa un grafo dirigido usando la representación 
// de la lista de adyacencia.
class Grafo {
	private int V;	// Nro de vértices
	private LinkedList<Integer> adj[];	// Lista de adyacencia

	// Constructor
	Grafo(int v){
		V = v;
		adj = new LinkedList[v];

		for(int i=0 ; i<v ; i++){
			adj[i] = new LinkedList<Integer>();
		}
	}

	// Función para agregar una arista al grafo
	void agregarArista(int v, int a){
		adj[v].add(a);
	}

	// Función que imprime el recorrido en anchura 
	// desde un vértice determinado.
	// busqueda(int v) atraviesa vértices accesibles desde v.
	void busqueda(int v){
		
		// Marcar todos los vértices como no visitados 
		// (por defecto establecido como falso)
		boolean visitado[] = new boolean[V];

		// Crear una cola para la búsqueda
		LinkedList<Integer> cola = new LinkedList<Integer>();

		// Marcar el vértice actual como visitado y encolarlo.
		visitado[v] = true;
		cola.add(v);

		while(cola.size() != 0){
			// Desencolar el vértice e imprimirlo
			v = cola.poll();
			System.out.print(v+ " ");

			// Obtiene todos los vértices adyacentes 
			// al vértice en la cola.
			// Si no se ha visitado un adyacente, 
			// se marca como visitado y se encola.
			Iterator<Integer> i = adj[v].listIterator();
			
			while(i.hasNext()){
				int n = i.next();
				
				if(!visitado[n]){
					visitado[n] = true;
					cola.add(n);
				}
			}
		}
	}

	public static void main(String args[]){
		Grafo g = new Grafo(4);

 		// Cada entrada es una tupla de dos nodos, uno 
 		// denotando el vértice fuente y el otro denotando 
 		// el vértice destino de la arista correspondiente. 
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);
		g.agregarArista(2, 0);
		g.agregarArista(2, 3);
		g.agregarArista(3, 3);

		// El siguiente es el primer recorrido
		// transversal (comenzando desde el vértice 2);
		g.busqueda(2);
		System.out.println("\n");
	}
}

// En la configuración por defecto, comenzamos el recorrido desde el vértice 2. 
// Cuando llegamos al vértice 0, buscamos todos los vértices adyacentes. 2 también 
// es un vértice adyacente de 0. Si no marcamos los vértices visitados, entonces 2 
// se procesará nuevamente y se convertirá en un proceso que no termina. Un primer 
// recorrido transversal del siguiente grafo es 2, 0, 3, 1.

// Uso:
// 		$ javac busquedaEnAnchura.java
// 		$ java Grafo


