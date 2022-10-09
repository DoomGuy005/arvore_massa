import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa(20);
        arvore.inserir(10);
        arvore.inserir(11);
        arvore.inserir(16);
        arvore.inserir(17);
        arvore.inserir(17);
        out.println(arvore);
    }
}