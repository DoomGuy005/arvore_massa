import static java.lang.System.out;

public class NoArvore {
	private NoArvore pai;
	private NoArvore filhoEsquerdo;
	private NoArvore filhoDireito;
	private float valor;

	public NoArvore(float valor) {
		this.valor = valor;
	}

	public NoArvore getPai() {
		return pai;
	}

	public NoArvore getFilhoEsquerdo() {
		return this.filhoEsquerdo;
	}

	public NoArvore getFilhoDireito() {
		return filhoDireito;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public void setFilhoDireito(NoArvore filhoDireito) {
		this.filhoDireito = filhoDireito;
	}

	public void setFilhoEsquerdo(NoArvore filhoEsquerdo) {
		this.filhoEsquerdo = filhoEsquerdo;
	}

	public void setPai(NoArvore pai) {
		this.pai = pai;
	}

	private boolean isEmpty(NoArvore no) {
		return (no == null);
	}

	public EnumFilhos temFilhos() {
		if (this.filhoDireito != null && this.filhoEsquerdo != null) {
			return EnumFilhos.TODOS;
		}
		if (this.filhoDireito != null) {
			return EnumFilhos.DIREITO;
		}
		if (this.filhoEsquerdo != null) {
			return EnumFilhos.ESQUERDO;
		}
		return EnumFilhos.NENHUM;
	}

	public boolean temFilhoDireito() {
		return (
				this.getFilhoDireito() != null
				&& this.getFilhoEsquerdo() == null
		);
	}
	public boolean temFilhoEsquerdo() {
		return (
				this.getFilhoDireito() == null
				&& this.getFilhoEsquerdo() != null
		);
	}
	public boolean temDoisFilhos() {
		return (this.getFilhoDireito() != null && this.getFilhoEsquerdo() != null);
	}

	public boolean ehFolha() {
		return (this.getFilhoDireito() == null && this.getFilhoEsquerdo() == null);
	}

	public boolean ehFilhoDireito() {
		return this.getPai().getValor() < this.getValor();
	}

	public boolean ehFilhoEsquerdo() {
		return this.getPai().getValor() > this.getValor();
	}


	@Override
	public String toString() {
		String texto = "";
		if (!isEmpty(filhoEsquerdo)) {
			texto += this.filhoEsquerdo.toString();
		}
		texto += String.format(" %.2f", this.valor);
		if (!isEmpty(filhoDireito)) {
			texto += this.filhoDireito.toString();
		}
		return texto;
	}

	public void printNo() {
		String fEsq = this.filhoEsquerdo != null ? String.format("\tesquerdo: %f\n", this.filhoEsquerdo.getValor())
				: "\tsem esquerdo\n";
		String fDir = this.filhoDireito != null ? String.format("\tdireito: %f\n", this.filhoDireito.getValor())
				: "\tsem direito\n";
		String pai = this.pai != null ? String.format("\tpai: %f\n", this.pai.getValor()) : "\tsem pai\n";
		String no = "{\n" + String.format("\tn??: %f\n", this.valor) + fEsq + fDir + pai + "}";
		System.out.println(no);
	}
}
