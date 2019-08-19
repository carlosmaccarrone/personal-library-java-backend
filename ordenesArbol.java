// A diferencia de las estructuras de datos lineales (matriz, array, 
// lista, colas, pilas, etc.) que solo tienen una forma lógica de 
// atravesarlas, los árboles se pueden atravesar de diferentes maneras. 
// Éste programa muestra las tres formas generalmente más utilizadas
// para recorrer árboles

// El recorrido InOrden proporciona nodos en orden no decreciente.
// Entre los usos de PreOrden se distingue crear una copia del árbol. 
// El recorrido PostOrdeN se utiliza para eliminar el árbol.

class Nodo {
	int dato;
	Nodo left, right;

	public Nodo(int valor){
		dato = valor;
		left = right = null;
	}
}

class ArbolBinario {
	Nodo raiz;

	ArbolBinario(){
		raiz = null;
	}

	void imprimirPostOrden(Nodo nodo){
		if(nodo == null) return;

		imprimirPostOrden(nodo.left);
		imprimirPostOrden(nodo.right);

		System.out.print(nodo.dato + " ");
	}

	void imprimirInOrden(Nodo nodo){
		if(nodo == null) return;

		imprimirInOrden(nodo.left);

		System.out.print(nodo.dato + " ");

		imprimirInOrden(nodo.right);
	}

	void imprimirPreOrden(Nodo nodo){
		if(nodo == null) return;

		System.out.print(nodo.dato + " ");
		
		imprimirPreOrden(nodo.left);
		imprimirPreOrden(nodo.right);
	}

	void imprimirPostOrden(){ imprimirPostOrden(raiz); }
	void imprimirInOrden(){ imprimirInOrden(raiz); }
	void imprimirPreOrden(){ imprimirPreOrden(raiz); }

	public static void main(String[] args){
		ArbolBinario arbol = new ArbolBinario();

		arbol.raiz = new Nodo(1);
		arbol.raiz.left = new Nodo(2);
		arbol.raiz.right = new Nodo(3);
		arbol.raiz.left.left = new Nodo(4);
		arbol.raiz.left.right = new Nodo(5);

		System.out.println("Arbol binario Pre-Orden: ");
		arbol.imprimirPreOrden();

		System.out.println("\nArbol binario In-Orden: ");
		arbol.imprimirInOrden();

		System.out.println("\nArbol binario Post-Orden: ");
		arbol.imprimirPostOrden();
		System.out.println("\n");
	}
}

// Uso:
// 		$ javac arbol.java
// 		$ java ArbolBinario