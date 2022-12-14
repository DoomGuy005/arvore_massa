import static java.lang.System.out;

public class Main {
	public static void main(String[] args) {
		ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa(20);
		arvore.inserir(10);
		arvore.inserir(21);
		arvore.inserir(16);
		arvore.inserir(9);
		arvore.inserir(9.5F);
		arvore.inserir(9.4F);
		arvore.inserir(9.6F);
		arvore.inserir(17);
		out.println(arvore);
		arvore.remover(10);
		out.println(arvore);
		arvore.remover(9.5f);
		out.println(arvore);
		arvore.remover(9.6f);
		out.println(arvore);
		arvore.inserir(22);
		out.println(arvore);
		arvore.inserir(21.5f);
		out.println(arvore);
		arvore.inserir(21.6f);
		out.println(arvore);
		arvore.pesquisar(21.6f);
		arvore.remover(22);
		out.println(arvore);
	}
}
