package biblioteca;

import java.util.ArrayList;

import gerenciadores.Fabrica;

import java.text.SimpleDateFormat;

import usuarios.*;

public class Livro {
    private String codigo;
    private String titulo;
    private String editora;
    private int anoDePublicacao;
    private int edicao;
    private ArrayList<String> autores;
    private ArrayList<Exemplar> exemplares;
    private ArrayList<Observador> observadores;
    private ArrayList<Reserva> reservas;

    public Livro(String codigo, String titulo, ArrayList<String> autores, int anoDePublicacao, int edicao, String editora, int numeroDeExemplares) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.anoDePublicacao = anoDePublicacao;
        this.edicao = edicao;
        this.editora = editora;
        this.observadores = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.exemplares = new ArrayList<>();
        this.initExemplares(numeroDeExemplares);

    }
    private void initExemplares(int numeroDeExemplares) {
        for (int i = 0; i < numeroDeExemplares; i++) {
            String codigoExemplar = String.valueOf(i + 1);
            Exemplar exemplar = Fabrica.criarExemplar(this, codigoExemplar);
            addExemplar(exemplar);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    public int getanoDePublicacao() {
        return anoDePublicacao;
    }

    public void setanoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    // add para criar o banco de dados da biblioteca
    public void addExemplar(Exemplar exemplar){
        exemplares.add(exemplar);
    }

    public int getNumeroDeExemplares() {
        return exemplares.size();
    }

    public int getNumeroDeExemplaresDisponiveis(){
        return getExemplaresDisponiveis().size();
    }

    public void adicionarObservador(Observador observador){
        observadores.add(observador);
    }

    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.atualizar(this);
        }
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public int getNumeroDeReservas() {
        return reservas.size();
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
        if (reservas.size() > 2) notificarObservadores();
    }

    public void removerReserva(String codigoUsuario) {
        for (Reserva reserva : reservas) {
            if (reserva.getUsuario().getCodigo().equals(codigoUsuario)) {
                reservas.remove(reserva);
            }
        }
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        ArrayList<Exemplar> exemplaresDisp = getExemplaresDisponiveis();
        exemplaresDisp.get(0).setEmprestimo(emprestimo);
    }

    public void removerEmprestimo(String codigoUsuario) {
        for (Exemplar exemplar : getExemplaresIndisponiveis()) {
            if (exemplar.comparaEmprestimo(codigoUsuario)) {
                exemplar.setEmprestimo(null);;
            }
        }
    }
    
    public void consultarInformacoes(){
        ArrayList<String> informacoes = stringLivro();
        for(String i : informacoes){
            System.out.println(i);
        }
    }

    public ArrayList<String> stringLivro(){
        ArrayList<String> informacoes = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        informacoes.add("=================");
        informacoes.add("Título: " + this.titulo + ", Número de reservas: " + reservas.size());
        if (reservas.size() > 0) {
            informacoes.add("---------------");
            informacoes.add("RESERVAS");
            for (int i = 0; i < reservas.size(); i++) {
                informacoes.add("Reserva " + (i+1) + ": " + reservas.get(i).getUsuario().getCodigo());
            }
        }
        if (exemplares.size() > 0) {
            informacoes.add("---------------");
            informacoes.add("EXEMPLARES");
            for (int i = 0; i < exemplares.size(); i++) {
                if (exemplares.get(i).isDisponivel()){
                    informacoes.add("Código: " + exemplares.get(i).getCodigoDoExemplar() + ", Status: Disponível");
                }else{
                    Emprestimo emprestimo = exemplares.get(i).getEmprestimo();
                    informacoes.add("Código: " + exemplares.get(i).getCodigoDoExemplar() + ", Status: Está com " + emprestimo.getUsuario().getNome()+
                            ". Data de emprestimo: " + formato.format(emprestimo.getDataEmprestimo()) + ", Data prevista de devolução: " + formato.format(emprestimo.getDataDevolucao()));
                }
            }
        }
        informacoes.add("=================");
        return informacoes;
    }


    public ArrayList<Exemplar> getExemplaresDisponiveis(){
        ArrayList<Exemplar> exemplaresDisp = new ArrayList<>();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.isDisponivel()) exemplaresDisp.add(exemplar);
        }
        return exemplaresDisp;
    }

    public ArrayList<Exemplar> getExemplaresIndisponiveis() {
        ArrayList<Exemplar> exemplaresIndisp = new ArrayList<>();
        for (Exemplar exemplar : exemplares) {
            if (! exemplar.isDisponivel()) exemplaresIndisp.add(exemplar);
        }
        return exemplaresIndisp;
    }
}
