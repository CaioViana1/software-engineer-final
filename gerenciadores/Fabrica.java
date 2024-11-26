package gerenciadores;

import java.util.ArrayList;
import java.util.Date;

import biblioteca.*;
import sistema.Parametros;
import usuarios.*;

public class Fabrica {
    public static Livro criarLivro(String codigo, String titulo, ArrayList<String> autores, int anoDePublicacao, int edicao, String editora, int numeroDeExemplares){
        return new Livro(codigo, titulo, autores, anoDePublicacao, edicao, editora, numeroDeExemplares);
    }

    public static Reserva criarReserva(Usuario usuario, Livro livro, Date data){
        return new Reserva(usuario, livro, data);
    }

    public static Emprestimo criarEmprestimo(UsuarioGenerico usuario, Exemplar exemplar, Date dataEmprestimo, Date dataDevolucao){
        return new Emprestimo(usuario, exemplar, dataEmprestimo, dataDevolucao);
    }

    public static Exemplar criarExemplar(Livro livro, String codigoDoExemplar){
        return new Exemplar(livro, codigoDoExemplar);
    }

    public static Parametros criarParametros(ArrayList<String> parametros){
        return new Parametros(parametros);
    }

    public static Usuario criarAlunoGraduacao(String nome, String codigo){return new AlunoGraduacao(nome, codigo);}

    public static Usuario criarAlunoPos(String nome, String codigo){return new AlunoPos(nome, codigo);}

    public static Usuario criarProfessor(String nome, String codigo){return new Professor(nome, codigo);}

}
