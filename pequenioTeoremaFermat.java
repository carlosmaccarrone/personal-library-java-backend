import java.io.*;
import java.math.*;


// Pequeño teorema de Fermat:
// Si n es un número primo, entonces para cada a, 1<a<(n-1).

// a^(n-1) ≡ 1 (mod n) 
// a^(n-1) % n = 1 
 
// Si un número dado es primo, entonces este método 
// siempre devuelve verdadero. Si el número dado es 
// compuesto (o no primo), entonces puede devolver 
// verdadero o falso, pero la probabilidad de producir 
// un resultado incorrecto para el compuesto es baja y 
// puede reducirse haciendo más iteraciones.

// Un valor más alto de k indica que la probabilidad
// de los resultados correctos para un número 
// compuesto sean altos. Para números primos, el 
// resultado siempre es correcto
// 1) Repetir lo siguiente k veces:
       // a) Elija aleatoriamente en el rango [2, n - 2]
       // b) Si mcd(a, n) ≠ 1, devuelve falso
       // c) Si a^(n-1) &nequiv; 1 (mod n), devuelve falso
// 2) Devuelve verdadero [probablemente primo].

class TestPrimalidad {

	static int arranque(int a, int n, int p){
		int resultado = 1;
		a = a % p;

		while(n > 0){
			if((n & 1) == 1){
				resultado = (resultado * a) % p;
			}

			n = n >> 1;
			a = (a * a) % p;
		}
		return resultado;
	}

	static boolean esPrimo(int n, int k){
		if(n <= 1 || n == 4) return false;
		if(n <= 3) return true;

		while(k > 0){
			int a = 2 + (int)(Math.random() % (n - 4));

			if(arranque(a, n-1, n) != 1) return false;
			k--;
		}

		return true;
	}

	public static void main(String[] args){
		int k = 999;

		if(esPrimo(234686243, k)){
			System.out.println(" verdadero");
		}else{
			System.out.println(" falso");
		}
	}

}

// Uso:
// 		$ javac fermat.java
// 		$ java TestPrimalidad