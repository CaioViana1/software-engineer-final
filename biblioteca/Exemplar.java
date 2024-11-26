package biblioteca;

public class Exemplar {
    private Livro livro;
    private String codigoDoExemplar;
    private boolean isDisponivel;
    private Emprestimo emprestimo;

    public Exemplar(Livro livro, String codigoDoExemplar) {
        this.livro = livro;
        this.codigoDoExemplar = codigoDoExemplar;
        this.isDisponivel = true;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
        setDisponivel(false);
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getCodigoDoExemplar() {
        return codigoDoExemplar;
    }

    public void setCodigoDoExemplar(String codigoDoExemplar) {
        this.codigoDoExemplar = codigoDoExemplar;
    }

    public boolean isDisponivel() {
        return isDisponivel;
    }

    public void setDisponivel(boolean disponivel) {
        isDisponivel = disponivel;
    }

    public boolean comparaEmprestimo(String codigoUsuario){
        if(emprestimo.getUsuario().getCodigo().equals(codigoUsuario)){
            return true;
        }
        return false;
    }
}
