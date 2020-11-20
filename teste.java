package Exercicio1;

public class teste {
	public static void main(String[] args) {

		AnalizadorMatrizAdjacencia analizador = new AnalizadorMatrizAdjacencia();

		// matriz de um grafo não dirigido
		int[][] matriz = new int[3][3];
		matriz[0][0] = 0;
		matriz[0][1] = 1;
		matriz[0][2] = 1;
		matriz[1][0] = 1;
		matriz[1][2] = 1;
		matriz[1][1] = 0;
		matriz[2][0] = 1;
		matriz[2][1] = 1;
		matriz[2][2] = 0;

		System.out.println("Grafo não dirigido: \n");
		System.out.println(analizador.tipoDoGrafo(matriz));
		System.out.println(analizador.arrestasDoGrafo(matriz));
		System.out.println(analizador.grausDoVertice(matriz));

		// matriz de um grafo dirigido
		int[][] matriz2 = new int[3][3];
		matriz2[0][0] = 0;
		matriz2[0][1] = 1;
		matriz2[0][2] = 0;
		matriz2[1][0] = 0;
		matriz2[1][2] = 2;
		matriz2[1][1] = 0;
		matriz2[2][0] = 1;
		matriz2[2][1] = 2;
		matriz2[2][2] = 1;

		System.out.println();
		System.out.println("Grafo dirigido: \n");
		System.out.println(analizador.tipoDoGrafo(matriz2));
		System.out.println(analizador.arrestasDoGrafo(matriz2));
		System.out.println(analizador.grausDoVertice(matriz2));

	}
}
