package usuarios;

import biblioteca.Livro;

public class Professor extends Usuario{
    private int notificacoes;

    public Professor(String nome, String codigo) {
        super(nome, codigo);
        notificacoes = 0;
    }

    @Override
    public void atualizar(Livro livro) {
        notificacoes++;
    }

    @Override
    public boolean podeTerMaisLivrosEmprestados() {
        return true;
    }

    @Override
    public boolean temReserva(String codigoLivro) {
        return true;

    }

    @Override
    public boolean temEmprestimoDoLivro(String codigoLivro) {
        return false;
    }

    @Override
    public int consultarNotificacoes() {
        return this.notificacoes;
    }

    @Override
    public int getLimiteDiasEmprestimo() {
        return 7;
    }
}
