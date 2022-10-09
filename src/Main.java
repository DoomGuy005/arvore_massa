import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa(20);
        arvore.inserir(10);
        arvore.inserir(21);
        arvore.inserir(16);
        arvore.inserir(9);
        arvore.inserir(17);
        out.println(arvore);
        arvore.remover(arvore.pesquisar(10, arvore.getRaiz()), arvore.getRaiz(), 10);
        out.println(arvore);
    }
}