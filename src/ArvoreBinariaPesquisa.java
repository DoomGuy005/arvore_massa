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
		System.out.printf("Pesquisando o valor %.2f na árvore...%n", valor);
		if (noAPesquisar != null) {
			System.out.print("Valor encontrado => ");
			noAPesquisar.printNo();
		} else {
			System.out.print("Valor não encontrado!");
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
		NoArvore noAux = no.getFilhoDireito();
		while (noAux.getFilhoEsquerdo() != null) {
			noAux = noAux.getFilhoEsquerdo();
		}
		return noAux;
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
		noARemover.printNo();
		if (noARemover.getValor() < valor) {
			System.out.println("\n>> indo pra direita");
			return this.removerRecursivo(noARemover.getFilhoDireito(), noARemover, valor);
		}
		if (noARemover.getValor() > valor) {
			System.out.println("\n>> indo pra esquerda");
			return this.removerRecursivo(noARemover.getFilhoEsquerdo(), noARemover, valor);
		}

		// nó foi encontrado
		if (noARemover.getValor() == valor) {
			System.out.println("\n>> encontrou");
			// 1º caso - removendo nó folha
			if (noARemover.temFilhos() == EnumFilhos.NENHUM) {
				if (noPai.temFilhos() == EnumFilhos.DIREITO) {
					noPai.setFilhoDireito(null);
				} else {
					noPai.setFilhoEsquerdo(null);
				}
				return noARemover;
			}

			// 2º caso - removendo nó com um filho - verificando filho direito
			if (noARemover.temFilhos() == EnumFilhos.DIREITO) {
				System.out.println("\n>> tem um filho direito");
				noARemover.getFilhoDireito().setPai(noARemover.getPai());
				if (noARemover == noPai.getFilhoDireito()) {
					noARemover.getPai().setFilhoDireito(noARemover.getFilhoDireito());
				} else {
					noARemover.getPai().setFilhoEsquerdo(noARemover.getFilhoDireito());
				}
				return noARemover;
			}

			// 2º caso - removendo nó com um filho - verificando filho esquerdo
			if (noARemover.temFilhos() == EnumFilhos.ESQUERDO) {
				System.out.println("\n>> tem um filho esquerdo");
				noARemover.getFilhoEsquerdo().setPai(noARemover.getPai());
				if (noARemover == noPai.getFilhoDireito()) {
					noARemover.getPai().setFilhoDireito(noARemover.getFilhoEsquerdo());
				} else {
					noARemover.getPai().setFilhoEsquerdo(noARemover.getFilhoEsquerdo());
				}
				return noARemover;
			}

			// 3º caso - nó com dois filhos
			// 1º passo - vá para o filho esquerdo
			// 2º passo - busque pelo ultimo elemento a direita depois do filho esquerdo
			// 3º passo - substitua o valor do nó a ser removido pelo ultimo elemento
			// 4º passo - remova o filho direito do pai do ultimo elemento
			if (noARemover.temFilhos() == EnumFilhos.TODOS) {
				System.out.println("\n>> tem dois filhos");
				if (noARemover.getFilhoEsquerdo().temFilhos() == EnumFilhos.NENHUM) {
					noARemover.setValor(noARemover.getFilhoEsquerdo().getValor());
					noARemover.setFilhoEsquerdo(null);
					return noARemover;
				}
				NoArvore noAux = this.maiorElementoDaEsquerda(noARemover);
				noARemover.setValor(noAux.getValor());
				noAux.getPai().setFilhoDireito(null);
				return noARemover;
			}
		}
		return null;
	}

	public NoArvore remover(float valor) {
		System.out.println(String.format("Valor a remover: %.2f", valor));
		return this.removerRecursivo(this.raiz, null, valor);
	}

	@Override
	public String toString() {
		return raiz.toString();
	}
}
