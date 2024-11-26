package biblioteca;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import usuarios.*;
import gerenciadores.*;

public class Biblioteca {
    private HashMap<String, Usuario> usuarios;
    private HashMap<String, Livro> livros;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Reserva> reservas;

    private static Biblioteca instancia;

    private Biblioteca() {
        this.usuarios = new HashMap<>();
        this.livros = new HashMap<>();
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public static Biblioteca getInstancia() {
        if (instancia == null)
            instancia = new Biblioteca();
        return instancia;
    }

    // funções "add" para preenchimento do "banco de dados" da biblioteca
    public void addLivro(Livro livro){
        livros.put(livro.getCodigo(), livro);
    }

    public void addUsuario(Usuario usuario){
        usuarios.put(usuario.getCodigo(), usuario);
    }

    public Usuario buscarUsuarioPorCodigo(String codigoUsuario) {
        return usuarios.get(codigoUsuario);
    }

    public Livro buscarLivroPorCodigo(String codigoLivro) {
        return livros.get(codigoLivro);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public ArrayList<Exemplar> exemplaresDisponiveisDoLivro(String codigoDoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoDoLivro);
        ArrayList<Exemplar> exemplaresDoLivro = livro.getExemplaresDisponiveis();
        
        return exemplaresDoLivro;
    }

    public void emprestarLivroParaUsuario(String codigoUsuario, String codigoLivro, String data) throws ParseException {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        ArrayList<Exemplar> exemplaresDisponiveis = exemplaresDisponiveisDoLivro(codigoLivro);
    
        if (livro == null) GerenciadorMensagens.falhaLivroInexistente(usuario.getNome());
        // caso 1
        else if(exemplaresDisponiveis.isEmpty()) GerenciadorMensagens.falhaSemDisponibilidadeDeExemplares(usuario.getNome(), livro.getTitulo());
        // caso 2
        else if (usuario.isDevedor()) GerenciadorMensagens.falhaUsuarioDevedor(usuario.getNome(), livro.getTitulo());
        // caso 3
        else if(!usuario.podeTerMaisLivrosEmprestados()) GerenciadorMensagens.falhaLimiteDeEmprestimos(usuario.getNome(), livro.getTitulo());
        // casos 4 e 5
        else if(!usuario.temReserva(codigoLivro) && livro.getNumeroDeReservas() >= livro.getNumeroDeExemplaresDisponiveis()) GerenciadorMensagens.falhaAusenciaDeReserva(usuario.getNome(), livro.getTitulo());
        // caso 6
        else if(usuario.temEmprestimoDoLivro(codigoLivro)) GerenciadorMensagens.falhaEmprestimoRepetido(usuario.getNome(), livro.getTitulo());
        // falta caso de se ele tiver esse livro ja em emprestimo não poder!!
        else concluirEmprestimoDeLivro(usuario, livro, data);
    }

    public void concluirEmprestimoDeLivro(Usuario usuario, Livro livro, String data) throws ParseException {
        // se o usuário tiver reserva desse livro, vai finalizar
        usuario.finalizarReserva(livro.getCodigo());
        livro.removerReserva(usuario.getCodigo());

        Date dataDeEmprestimo = GerenciadorDeDatas.dataAsDate(data);
        Date dataDeDevolucao = GerenciadorDeDatas.getDataDeDevolucao(dataDeEmprestimo, usuario.getLimiteDiasEmprestimo());

        // pegando codigo do exemplar
        ArrayList<Exemplar> exemplares = livro.getExemplaresDisponiveis();
        Exemplar exemplar = exemplares.get(0);

        Emprestimo emprestimo = Fabrica.criarEmprestimo(usuario, exemplar, dataDeEmprestimo, dataDeDevolucao);

        livro.adicionarEmprestimo(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);
        adicionarEmprestimo(emprestimo);

        GerenciadorMensagens.sucessoEmprestimo(usuario.getNome(), livro.getTitulo());
    }
    
    public void devolverLivro(String codigoUsuario, String codigoLivro) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        if (!usuario.temEmprestimoDoLivro(codigoLivro)){
            GerenciadorMensagens.falhaDevolucao(usuario.getNome(), livro.getTitulo());
        }
        else{
            usuario.finalizarEmprestimo(codigoLivro);
            GerenciadorMensagens.sucessoDevolucao(usuario.getNome(), livro.getTitulo());
            livro.removerEmprestimo(codigoUsuario);
        }
    }

    public void criarReservaParaUsuario(String codigoUsuario, String codigoLivro, String data) throws ParseException {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        if (!usuario.estaAptoParaReservar()){
            GerenciadorMensagens.falhaReserva(usuario.getNome());
        }
        else{
            Date dataReserva = GerenciadorDeDatas.dataAsDate(data);
            Reserva novaReserva = Fabrica.criarReserva(usuario, livro, dataReserva);
            adicionarReserva(novaReserva);
            livro.adicionarReserva(novaReserva);
            usuario.adicionarReserva(novaReserva);
            GerenciadorMensagens.sucessoReserva(usuario.getNome(), livro.getTitulo());
        }
    }


    public void registrarObservador(String codigoUsuario, String codigoLivro) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        livro.adicionarObservador(usuario);
        GerenciadorMensagens.sucessoRegistrarObservador(usuario.getNome(), livro.getTitulo());
    }

    public void consultarInformacoesLivro(String codigoLivro){
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        livro.consultarInformacoes();
    }

    public void consultarInformacoesUsuario(String codigoUsuario){
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        usuario.consultarInformacoes();
    }

    public int consultarNotificações(String codigoUsuario){
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);

        return usuario.consultarNotificacoes();
    }
}
