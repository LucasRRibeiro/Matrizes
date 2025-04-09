package codificacao;

//Classe responsável pela tabela de mapeamento
public class TabelaCriptografia {

	// Exibir a tabela de mapeamento de letras para números
	public void exibirTabelaDeCriptografia() {
		System.out.println("Tabela de Criptografia:");
		System.out.println("-------------------------");
		System.out.println("Espaço -> 0");
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(c + " -> " + getCharNumber(c));
		}
		System.out.println("-------------------------");
	}

	// Mapeamento de caracteres para números
	public static int getCharNumber(char c) {
		if (c == ' ') {
			return 0; // Espaço
		} else {
			return (c - 'A' + 1); // Letras A=1, B=2, ..., Z=26
		}
	}
}