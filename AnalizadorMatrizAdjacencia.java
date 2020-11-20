package Exercicio1;

import java.util.LinkedList;

public class AnalizadorMatrizAdjacencia {

	private char alfabeto[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private String criaStringGrafo(boolean simples, boolean regular, boolean completo, boolean nulo, boolean dirigido) {

		String str = "O Grafo representado por esta matriz é";

		if (nulo) {
			str += " nulo, ";
		} else {
			str += " não nulo, ";
		}

		if (simples) {
			str += "simples, ";
		} else {
			str += "multigrafo, ";
		}

		if (regular) {
			str += "regular, ";
		} else {
			str += "não é regular, ";
		}

		if (completo) {
			str += "completo, ";
		} else {
			str += "não é completo, ";
		}

		if (dirigido) {
			str += "e é dirigido.";
		} else {
			str += "e não é dirigido.";
		}

		return str;
	}

	private boolean ehDirigido(int matriz[][]) {
		boolean dirigido = false;

		for (int i = 0; i < matriz.length; i++) {
			if (dirigido == false) {
				for (int j = 0; j < matriz.length; j++) {
					if (dirigido == false) {
						if (matriz[i][j] != matriz[j][i]) {
							dirigido = true;
							break;
						}
					}
				}
			} else {
				break;
			}
		}
		return dirigido;
	}

	public String tipoDoGrafo(int matriz[][]) {

		boolean nulo = true;
		boolean simples = true;
		boolean regular = true;
		boolean completo = true;
		int grauVerticeColuna = -1;// inicia com -1 para salvar o primeiro grau encontrado.
		int auxGrauVerticeColuna = 0;
		int grauVerticeLinha = 0;
		int auxGrauVerticeLinha = 0;
		boolean ehDirigido = ehDirigido(matriz);

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {

				// verificacao nulo
				if (nulo) {
					if (matriz[i][j] != 0) {
						nulo = false;
					}
				}

				// validacao grafo simples
				if (simples) {
					if (matriz[i][j] > 1) {
						simples = false;
						completo = false;
						regular = false;
					}
				}

				// validacao loops
				if (i == j) {
					if (matriz[i][j] != 0) {
						regular = false;
						simples = false;
						completo = false;
						
						// validaçao regular
						if (regular) {
							if (ehDirigido) {
								auxGrauVerticeLinha += matriz[j][i];
								auxGrauVerticeColuna += matriz[i][j];
							} else {
								auxGrauVerticeColuna += (matriz[i][j] * 2);
							}
						}
					}
				} else {

					// validacao completo
					if (completo) {
						if (matriz[i][j] != 1) {
							completo = false;
						}
					}

					// validacao regular
					if (regular) {
						if (ehDirigido) {
							auxGrauVerticeLinha += matriz[j][i];
							auxGrauVerticeColuna += matriz[i][j];
						} else {
							auxGrauVerticeColuna += matriz[i][j];
						}
					}
				}
			}

			// validação regular
			if (regular) {
				if (ehDirigido) {
					if (grauVerticeColuna == -1) {
						grauVerticeLinha = auxGrauVerticeLinha;
						auxGrauVerticeLinha = 0;
						grauVerticeColuna = auxGrauVerticeColuna;
						auxGrauVerticeColuna = 0;

						if (grauVerticeLinha != grauVerticeColuna) {
							regular = false;
						}

					} else {//else do if que verifica se eh a primeira vez
						if (grauVerticeColuna != auxGrauVerticeColuna || grauVerticeLinha != auxGrauVerticeLinha) {
							regular = false;
						} else {
							auxGrauVerticeColuna = 0;
							auxGrauVerticeLinha = 0;
						}
					}
				} else {//else do if que verifica se eh dirigido
					if (grauVerticeColuna == -1) {
						grauVerticeColuna = auxGrauVerticeColuna;
						auxGrauVerticeColuna = 0;
					} else {
						if (grauVerticeColuna != auxGrauVerticeColuna) {
							regular = false;
						} else {
							auxGrauVerticeColuna = 0;
						}
					}
				}
			}
		}

		return criaStringGrafo(simples, regular, completo, nulo, ehDirigido(matriz));
	}

	public String arrestasDoGrafo(int matriz[][]) {
		String str = "";
		String conjuntos = "";
		int qtdArrestas = 0;

		if (ehDirigido(matriz)) {
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					qtdArrestas += matriz[i][j];
					int contador = matriz[i][j];
					while (contador != 0) {
						conjuntos += "(" + alfabeto[j] + "," + alfabeto[i] + ")" + ",";
						contador--;
					}
				}
			}

			return str + "Quantidade de arrestas:" + qtdArrestas + "\n" + "E = {"
					+ conjuntos.substring(0, conjuntos.length() - 1) + "}";
		} else {
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					int contador = matriz[i][j];
					qtdArrestas += matriz[i][j];
					while (contador != 0) {
						conjuntos += "(" + alfabeto[i] + "," + alfabeto[j] + ")" + ",";
						contador--;
					}
					if (i == j) {
						break;
					}
				}
			}
			if (conjuntos.equals("")) {

				return str + "Quantidade de arrestas:" + qtdArrestas + "\n" + "E = {" + conjuntos + "}";
			} else {

				return str + "Quantidade de arrestas:" + qtdArrestas + "\n" + "E = {"
						+ conjuntos.substring(0, conjuntos.length() - 1) + "}";
			}
		}
	}

	public String grausDoVertice(int matriz[][]) {
		String str = "Os vertices e seus respectivos graus são: \n";

		if (ehDirigido(matriz)) {
			int grauEntrada = 0;
			int grauSaida = 0;
			LinkedList<Integer> listaGrausEntrada = new LinkedList<>();
			LinkedList<Integer> listaGrausSaida = new LinkedList<>();

			for (int i = 0; i < matriz.length; i++) {
				grauEntrada = 0;
				grauSaida = 0;
				for (int j = 0; j < matriz.length; j++) {
					if (i == j) {
						grauEntrada += matriz[i][j];
						grauSaida += matriz[i][j];
					} else {
						grauEntrada += matriz[i][j];
						grauSaida += matriz[j][i];
					}
				}
				str += "grauE(" + alfabeto[i] + ") = " + grauEntrada + "\n";
				str += "grauS(" + alfabeto[i] + ") = " + grauSaida + "\n";
				listaGrausEntrada.add(grauEntrada);
				listaGrausSaida.add(grauSaida);
				listaGrausEntrada.sort(null);
				listaGrausSaida.sort(null);
			}

			return str + "Sequência de graus de entrada: "
					+ listaGrausEntrada.toString().substring(1, listaGrausEntrada.toString().length() - 1)
					+ "\nSequência de graus de saída: "
					+ listaGrausSaida.toString().substring(1, listaGrausEntrada.toString().length() - 1);
		} else {
			int grau = 0;
			LinkedList<Integer> listaGraus = new LinkedList<>();
			for (int i = 0; i < matriz.length; i++) {
				grau = 0;
				for (int j = 0; j < matriz.length; j++) {
					if (matriz[i][j] != 0) {
						if (i == j) {
							grau += matriz[i][j] * 2;
						} else {
							grau += matriz[i][j];
						}
					}
				}
				str += "grau(" + alfabeto[i] + ") = " + grau + "\n";
				listaGraus.add(grau);
			}
			listaGraus.sort(null);

			return str + "Sequência de graus: "
					+ listaGraus.toString().substring(1, listaGraus.toString().length() - 1);

		}
	}
}
