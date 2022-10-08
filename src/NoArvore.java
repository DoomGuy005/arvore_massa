public class NoArvore {
    private NoArvore pai;
    private NoArvore filhoEsquerdo;
    private NoArvore filhoDireito;
    private final float valor;

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

    @Override
    public String toString() {
        String texto = String.format("%f", this.valor);
        if (!isEmpty(filhoEsquerdo)) {
            texto = texto + "\nFilho esquerdo de " + String.format("%f", this.valor) + " = " + this.filhoEsquerdo.toString();
        }
        if (!isEmpty(filhoDireito)) {
            texto = texto + "\nFilho direito de " + String.format("%f", this.valor) + " = " + this.filhoDireito.toString();
        }
        return texto;
    }
}
