package codificacao;

import java.util.Scanner;

public class TesteCriptografiaMatriz {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        TabelaCriptografia tabela = new TabelaCriptografia();
        Matriz matrizUtil = new Matriz();

        // Exibir tabela de mapeamento de letras para números
        tabela.exibirTabelaDeCriptografia();

        System.out.print("Informe a frase/palavra que deseja criptografar:");
        String msg = tec.nextLine().toUpperCase();

        // Gerar a matriz com a mensagem
        int[][] matriz = matrizUtil.gerarMatriz(msg);

        // Imprimir palavra que vai ser criptografada
        System.out.println("\nPalavra que deve ser criptografada: \n" + msg);

        // Imprimir a matriz gerada
        System.out.println("\nMatriz gerada:");
        matrizUtil.printMatriz(matriz);

        // Solicitar a chave de codificação (matriz 2x2)
        int[][] chave = solicitarChaveDeCodificacao(tec);

        // Imprimir a chave de codificação informada
        System.out.println("\nChave de codificação (matriz 2x2) informada:");
        matrizUtil.printMatriz(chave);

        // Exibir o processo de multiplicação detalhada
        matrizUtil.exibirMultiplicacaoDetalhada(chave, matriz);

        // Realizar a multiplicação das matrizes
        int[][] matrizCodificada = matrizUtil.multiplicarMatrizes(chave, matriz);

        // Mostrar a multiplicação das matrizes
        System.out.println("\nMatriz codificada (resultado da multiplicação):");
        matrizUtil.printMatriz(matrizCodificada);

        // Perguntar se deseja descriptografar
        System.out.print("\nDeseja descriptografar a mensagem? (S/N): ");
        char resposta = tec.next().charAt(0);

        if (resposta == 'S' || resposta == 's') {
            // Calcular a matriz inversa
            int[][] chaveInversa = matrizUtil.calcularInversa(chave);
            if (chaveInversa == null) {
                System.out.println("Não é possível calcular a inversa para a chave fornecida.");
            } else {
                // Descriptografar a mensagem
                int[][] matrizDescriptografada = matrizUtil.multiplicarMatrizes(chaveInversa, matrizCodificada);
                System.out.println("\nMatriz descriptografada:");
                matrizUtil.printMatriz(matrizDescriptografada);
            }
        }
    }

 // Função para solicitar a chave de codificação (matriz 2x2) do usuário
 	public static int[][] solicitarChaveDeCodificacao(Scanner tec) {
 		int[][] chave = new int[2][2];

 		System.out.println("\nInforme a chave de codificação (matriz 2x2):");

 		// Solicitar os elementos da matriz 2x2
 		for (int i = 0; i < 2; i++) {
 			for (int j = 0; j < 2; j++) {
 				System.out.printf("Informe o valor de [%d][%d]: ", i + 1, j + 1);
 				chave[i][j] = tec.nextInt();
 			}
 		}

 		return chave;
 	}
}
