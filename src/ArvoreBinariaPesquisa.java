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

    public void pesquisar(float valor) {
        NoArvore noAPesquisar = this.pesquisar(valor, this.raiz);
        if (noAPesquisar != null) {
            System.out.printf("Elemento: %.2f%n", noAPesquisar.getValor());
            if (noAPesquisar.getFilhoDireito() != null) {
                System.out.printf("Filho direito: %.2f%n", noAPesquisar.getFilhoDireito().getValor());
            }
            if (noAPesquisar.getFilhoEsquerdo() != null) {
                System.out.printf("Filho esquerdo: %.2f%n", noAPesquisar.getFilhoEsquerdo().getValor());
            }
            if (noAPesquisar.getPai() != null) {
                System.out.printf("Pai: %.2f%n", noAPesquisar.getPai().getValor());
            }
        }
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

    public NoArvore maiorElementoDaEsquerda(NoArvore noAPesquisar) {
        NoArvore noAux = noAPesquisar.getFilhoEsquerdo();
        while (noAux.getFilhoDireito() != null) {
            noAux = noAux.getFilhoDireito();
        }
        return noAux;
    }

    public NoArvore removerRecursivo(NoArvore noARemover, NoArvore noPai, float valor) {
        // procurando nó a ser removido
        if (noARemover == null) {
            return null;
        }
        if (noARemover.getValor() < valor) {
            return this.removerRecursivo(noARemover.getFilhoDireito(), noARemover, valor);
        }
        if (noARemover.getValor() > valor) {
            return this.removerRecursivo(noARemover.getFilhoEsquerdo(), noARemover, valor);
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
            // 1º passo - vá para o filho direito
            // 2º passo - busque pelo ultimo elemento a esquerda depois do filho direito
            // 3º passo - substitua o valor do nó a ser removido pelo ultimo elemento
            // 4º passo - remova o filho direito do pai do ultimo elemento
            if (noARemover.temFilhos() == EnumFilhos.TODOS) {
                NoArvore noAux = this.maiorElementoDaEsquerda(noARemover);
                noARemover.setValor(noAux.getValor());
                noAux.getPai().setFilhoDireito(null);
                this.pesquisar(noARemover.getValor());
                noAux = null;
                this.removerRecursivo(noARemover.getFilhoDireito(), noARemover, valor);
            }
        }
        return null;
    }

    public NoArvore remover(float valor) {
        return this.removerRecursivo(this.raiz, null, valor);
    }

    @Override
    public String toString() {
        return raiz.toString();
    }
}
