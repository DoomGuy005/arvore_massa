public class ArvoreBinariaPesquisa {
    private NoArvore raiz;

    public ArvoreBinariaPesquisa(float valor) {
        this.raiz = new NoArvore(valor);
    }

    public NoArvore pesquisar(float valor, NoArvore no) {
        if (no == null) {
            return no;
        }
        if (valor > no.getValor()) {
            return this.pesquisar(valor, no.getFilhoDireito());
        }
        if (valor < no.getValor()) {
            return this.pesquisar(valor, no.getFilhoEsquerdo());
        }
        return no;
    }

    public NoArvore inserirRecursivo(float valor, NoArvore proximoNo, NoArvore pai) {
        if (proximoNo == null) {
            proximoNo = new NoArvore(valor);
            proximoNo.setPai(pai);
            if (proximoNo.getValor() > pai.getValor()) {
                pai.setFilhoDireito(proximoNo);
            }
            if (proximoNo.getValor() < pai.getValor()) {
                pai.setFilhoEsquerdo(proximoNo);
            }
            return proximoNo;
        }
        if (valor > proximoNo.getValor()) {
            return this.inserirRecursivo(valor, proximoNo.getFilhoDireito(), proximoNo);
        }
        if (valor < proximoNo.getValor()) {
            return this.inserirRecursivo(valor, proximoNo.getFilhoEsquerdo(), proximoNo);
        }
        return null;
    }

    public NoArvore inserir(float valor) {
        return this.inserirRecursivo(valor, this.raiz, this.raiz);
    }

    @Override
    public String toString() {
        return raiz.toString();
    }
}
