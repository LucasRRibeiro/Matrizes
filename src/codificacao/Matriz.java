package codificacao;

// Classe responsável pelas operações com matrizes
public class Matriz {

    // Função para gerar a matriz a partir da mensagem
    public int[][] gerarMatriz(String msg) {
        int totalCaracteres = msg.length();
        int colunas = (int) Math.ceil(totalCaracteres / 2.0); // Número de colunas
        int[][] matriz = new int[2][colunas]; // Matriz de 2 linhas e "colunas" colunas

        // Preencher a matriz por linha
        int index = 0;
        for (int i = 0; i < 2; i++) { // Duas linhas
            for (int j = 0; j < colunas; j++) { // Número de colunas
                if (index < totalCaracteres) {
                    matriz[i][j] = TabelaCriptografia.getCharNumber(msg.charAt(index));
                    index++;
                } else {
                    matriz[i][j] = 0; // Preencher com 0 se não houver mais caracteres
                }
            }
        }
        return matriz;
    }

    // Função para multiplicar a matriz chave pela matriz da mensagem
    public int[][] multiplicarMatrizes(int[][] chave, int[][] mensagem) {
        int linhas = chave.length; // Linhas da matriz chave (2)
        int colunas = mensagem[0].length; // Colunas da matriz da mensagem

        int[][] resultado = new int[linhas][colunas];

        // Multiplicação das duas matrizes
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado[i][j] = (chave[i][0] * mensagem[0][j] + chave[i][1] * mensagem[1][j]) % 26;
                if (resultado[i][j] < 0) {
                    resultado[i][j] += 26; // Ajustar valores negativos
                }
            }
        }
        return resultado;
    }

    // Método para exibir o processo de multiplicação detalhada
    public void exibirMultiplicacaoDetalhada(int[][] chave, int[][] matriz) {
        // Mostrar a matriz chave (A)
        System.out.println("\nMatriz A:");
        printMatriz(chave);

        // Mostrar a matriz da mensagem (M)
        System.out.println("\nMatriz B (Mensagem):");
        printMatriz(matriz);

        // Exibir o processo de multiplicação
        System.out.println("\nMultiplicação A * B:");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.println("\nMultiplicando coluna " + (j + 1) + ":");
            for (int i = 0; i < chave.length; i++) {
                System.out.print("(" + chave[i][0] + " * " + matriz[0][j] + ") + (" + chave[i][1] + " * " + matriz[1][j] + ")");
                System.out.println(" = " + ((chave[i][0] * matriz[0][j]) + (chave[i][1] * matriz[1][j])));
            }
        }
    }

    // Função para imprimir a matriz
    public static void printMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }


	public static int[][] calcularInversa(int[][] chave) {
		int a = chave[0][0];
		int b = chave[0][1];
		int c = chave[1][0];
		int d = chave[1][1];

		// Exibir a matriz chave
		System.out.println("\n" + "Matriz Chave:");
		printMatriz(chave);

		// Determinante da matriz chave
		int det = a * d - b * c;
		System.out.println("\nDeterminante (a * d - b * c):");
		System.out.println("\n(" + a + " * " + d + ") - (" + b + " * " + c + ") = " + det);

		det = (det % 26 + 26) % 26; // Garantir que o determinante esteja no módulo 26
		System.out.println("\n" + "Determinante mod 26: " + det);

		// Encontrar o inverso modular do determinante
		int invDet = inversoModular(det, 26);
		if (invDet == -1) {
			System.out.println("O determinante não possui inverso em mod 26. Não é possível descriptografar.");
			return null; // Se não existe inverso, não é possível descriptografar
		}

		System.out.println("\n" + "Inverso modular do determinante: " + invDet);

		// Calcular a matriz inversa
		int[][] inversa = new int[2][2];
		inversa[0][0] = (d * invDet) % 26;
		inversa[0][1] = (-b * invDet + 26) % 26; // Ajusta para positivo
		inversa[1][0] = (-c * invDet + 26) % 26; // Ajusta para positivo
		inversa[1][1] = (a * invDet) % 26;

		// Mostrar o cálculo da matriz inversa
		System.out.println("\nMatriz Inversa:");
		System.out.println("(" + d + " * " + invDet + ") mod 26 = " + inversa[0][0]);
		System.out.println("(-" + b + " * " + invDet + " + 26) mod 26 = " + inversa[0][1]);
		System.out.println("(-" + c + " * " + invDet + " + 26) mod 26 = " + inversa[1][0]);
		System.out.println("(" + a + " * " + invDet + ") mod 26 = " + inversa[1][1] + "\n");

		printMatriz(inversa);

		return inversa;
	}

	// Função para calcular o inverso modular usando o Algoritmo de Euclides
	// Estendido
	public static int inversoModular(int det, int mod) {
		for (int i = 1; i < mod; i++) {
			if ((det * i) % mod == 1) {
				return i;
			}
		}
		return -1; // Não tem inverso
	}
}