package usuarios;

public interface UsuarioGenerico {
    public int getLimiteDiasEmprestimo();
    public boolean podeTerMaisLivrosEmprestados();
    public boolean temReserva(String codigoLivro);
    public boolean temEmprestimoDoLivro(String codigoLivro);
    public int consultarNotificacoes();
}
