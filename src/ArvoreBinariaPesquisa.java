public class ArvoreBinariaPesquisa {
    private NoArvore raiz;

    public ArvoreBinariaPesquisa(float valor) {
        this.raiz = new NoArvore(valor);
    }

    public NoArvore pesquisar(float valor, NoArvore no) {
        if (no == null) {
            return null;
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

    public NoArvore remover(NoArvore noARemover, NoArvore noPai, float valor) {
        // 1º caso - removendo nó folha
        if (noARemover.getFilhoDireito() == null && noARemover.getFilhoEsquerdo() == null) {
            if (noARemover.getValor() > noPai.getValor()) {
                noPai.setFilhoDireito(null);
                return noARemover;
            } else {
                noPai.setFilhoEsquerdo(null);
                return noARemover;
            }
        }

        // 2º caso - removendo nó com um filho - verificando filho direito
        if (noARemover.getFilhoDireito() != null && noARemover.getFilhoEsquerdo() == null) {
            noPai.setValor(noARemover.getValor());
            noPai.setFilhoDireito(null);
        }

        // 2º caso - removendo nó com um filho - verificando filho esquerdo
        if (noARemover.getFilhoDireito() == null && noARemover.getFilhoEsquerdo() != null) {
            noPai.setValor(noARemover.getValor());
            noPai.setFilhoEsquerdo(null);
        }

        return null;
        // 3º caso - nó com dois filhos
    }

    @Override
    public String toString() {
        return raiz.toString();
    }
}
