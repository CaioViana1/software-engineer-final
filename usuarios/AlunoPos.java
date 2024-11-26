package usuarios;

import biblioteca.*;

public class AlunoPos extends Usuario{
    public AlunoPos(String nome, String codigo) {
        super(nome, codigo);
    }

    @Override
    public void atualizar(Livro livro) {

    }

    @Override
    public int getLimiteDiasEmprestimo() {
        return 5;
    }

    @Override
    public boolean podeTerMaisLivrosEmprestados() {
        int qtdAtualLivrosEmprestados = 0;
        for (Emprestimo emprestimo : emprestimos){
            if (!emprestimo.isFinalizado()) qtdAtualLivrosEmprestados++;
        }
        return (qtdAtualLivrosEmprestados < 4);
    }

    @Override
    public boolean temReserva(String codigoLivro) {
        for (Reserva reserva : reservas) {
            if (reserva.getLivro().getCodigo().equals(codigoLivro) && !reserva.isFinalizada()) return true;
        }
        return false;

    }

    @Override
    public boolean temEmprestimoDoLivro(String codigoLivro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getExemplar().getLivro().getCodigo().equals(codigoLivro) && !emprestimo.isFinalizado()) return true;
        }
        return false;
    }

    @Override
    public int consultarNotificacoes() {
        return 0;
    }
}
