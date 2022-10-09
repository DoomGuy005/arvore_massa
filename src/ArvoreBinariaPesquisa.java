public class ArvoreBinariaPesquisa {
    private NoArvore raiz;

    public NoArvore getRaiz() {
        return this.raiz;
    }
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

    public NoArvore menorElementoDaDireita(NoArvore no) {
        NoArvore noAux;
        if (no.temFilhos() == EnumFilhos.TODOS) {
            noAux = no.getFilhoDireito();
            while (noAux.getFilhoEsquerdo() != null) {
                noAux = noAux.getFilhoEsquerdo();
            }
            return noAux;
        }
        return null;
    }

    public NoArvore remover(NoArvore noARemover, NoArvore noPai, float valor) {
        // procurando nó a ser removido
        if (noARemover == null) {
            return null;
        }
        if (noARemover.getValor() > valor) {
            return this.remover(noARemover.getFilhoDireito(), noARemover, valor);
        }
        if (noARemover.getValor() < valor) {
            return this.remover(noARemover.getFilhoEsquerdo(), noARemover, valor);
        }

        // nó foi encontrado
        if (noARemover.getValor() == valor) {
            // 1º caso - removendo nó folha
            if (noARemover.temFilhos() == EnumFilhos.NENHUM) {
                if (noARemover.getValor() > noPai.getValor()) {
                    noPai.setFilhoDireito(null);
                } else {
                    noPai.setFilhoEsquerdo(null);
                }
                return noARemover;
            }

            // 2º caso - removendo nó com um filho - verificando filho direito
            if (noARemover.temFilhos() == EnumFilhos.DIREITO) {
                noPai.setValor(noARemover.getValor());
                noPai.setFilhoDireito(null);
            }

            // 2º caso - removendo nó com um filho - verificando filho esquerdo
            if (noARemover.temFilhos() == EnumFilhos.ESQUERDO) {
                noPai.setValor(noARemover.getValor());
                noPai.setFilhoEsquerdo(null);
            }

            // 3º caso - nó com dois filhos
            if (noARemover.temFilhos() == EnumFilhos.TODOS) {
                NoArvore noAux = this.menorElementoDaDireita(noARemover);
                noARemover.setValor(noAux.getValor());
                noAux.setValor(valor);
                this.remover(noARemover.getFilhoDireito(), noARemover, valor);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return raiz.toString();
    }
}
