import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa(20);
        arvore.inserir(10);
        arvore.inserir(21);
        arvore.inserir(16);
        arvore.inserir(9);
        arvore.inserir(9.5F);
        arvore.inserir(17);
        out.println(arvore);
        arvore.remover(10);
        out.println(arvore);
    }
}