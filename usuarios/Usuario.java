package usuarios;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

import biblioteca.*;

public abstract class Usuario implements Observador, UsuarioGenerico {
    protected String nome;
    protected String codigo;
    protected ArrayList<Emprestimo> emprestimos;
    protected ArrayList<Reserva> reservas;
    protected boolean isDevedor;

    public Usuario(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.isDevedor = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean isDevedor() {
        return isDevedor;
    }

    public void setDevedor(boolean devedor) {
        isDevedor = devedor;
    }

    public void adicionarReserva(Reserva reserva){
        reservas.add(reserva);
    }

    public void finalizarReserva(String codigoLivro) {
        for (Reserva reserva : reservas) {
            if (reserva.getLivro().getCodigo().equals(codigoLivro)) {
                reserva.setFinalizada(true);
            }
        }
    }

    public void finalizarEmprestimo(String codigoLivro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getExemplar().getLivro().getCodigo().equals(codigoLivro)) {
                emprestimo.setFinalizado(true);
            }
        }
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);

        // se o usuário tiver uma reserva desse livro, deve excluir essa reserva realizar o empréstimo
        finalizarReserva(emprestimo.getExemplar().getLivro().getCodigo());
    }

    public void consultarInformacoes(){
        ArrayList<String> informacoes1 = stringUsuario();
        for(String i : informacoes1){
            System.out.println(i);
        }
    }

    public ArrayList<String> stringUsuario() {
        ArrayList<String> informacoes = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        informacoes.add("=================");
        informacoes.add("Nome: " + getNome() + ".");

        if (reservas.size() > 0) {
            informacoes.add("---------------");
            informacoes.add("RESERVAS");
            for (int i = 0; i < reservas.size(); i++) {
                Reserva reserva = reservas.get(i);
                informacoes.add("Livro: " + reserva.getLivro().getTitulo() + ", Data da Reserva: " + formato.format(reserva.getData()));
            }
        }
        else{
            informacoes.add("---------------");
            informacoes.add("Usuário sem reservas.");
        }
        if (emprestimos.size() > 0) {
            informacoes.add("---------------");
            informacoes.add("EMPRÉSTIMOS");
            for (int i = 0; i < emprestimos.size(); i++) {
                Emprestimo emprestimo = emprestimos.get(i);
                if (emprestimo.isFinalizado()) {
                    informacoes.add("Livro: " + emprestimo.getExemplar().getLivro().getTitulo() + ", Data de Empréstimo: " + formato.format(emprestimo.getDataEmprestimo()) +
                            ", Status: Finalizado" + ", Data de Devolução Prevista: " + formato.format(emprestimo.getDataDevolucao()) + ".");
                } else {
                    informacoes.add("Livro: " + emprestimo.getExemplar().getLivro().getTitulo() + ", Data de Empréstimo: " + formato.format(emprestimo.getDataEmprestimo()) +
                            ", Status: Em andamento" + ", Data de Devolução Prevista: " + formato.format(emprestimo.getDataDevolucao()) + ".");
                }
            }
        }
        else{
            informacoes.add("---------------");
            informacoes.add("Usuário sem empréstimos");
        }
        informacoes.add("=================");

        return informacoes;
    }

    public boolean estaAptoParaReservar() {
        int qtdAtualReserva = 0;
        for (Reserva reserva : reservas) {
            if (!reserva.isFinalizada()) qtdAtualReserva++;
        }
        return (qtdAtualReserva < 3);
    }
}
