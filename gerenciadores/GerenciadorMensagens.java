package gerenciadores;

public class GerenciadorMensagens {
    public static void falhaLivroInexistente(String nomeUsuario){
        System.out.println("Erro ao procurar livro. O Código digitado não corresponde a nenhum item da bibilioteca.");
    }

    public static void falhaUsuarioDevedor(String nomeUsuario, String tituloLivro){
        System.out.println("Não foi possível realizar o empréstimo do livro " + tituloLivro + ". O usuário " + nomeUsuario + " está com saldo devedor.");
    }

    public static void falhaSemDisponibilidadeDeExemplares(String nomeUsuario, String tituloLivro){
        System.out.println("Não foi possível realizar o empréstimo do livro " + tituloLivro + " ao usuário " + nomeUsuario + ", pois não há exemplares disponíveis.");
    }

    public static void falhaLimiteDeEmprestimos(String nomeUsuario, String tituloLivro){
        System.out.println("Não foi possível realizar o empréstimo do livro " + tituloLivro + ". O usuário " + nomeUsuario + " atingiu o limite de empréstimos simultâneos.");
    }

    public static void falhaAusenciaDeReserva(String nomeUsuario, String tituloLivro){
        System.out.println("Não foi possível realizar o empréstimo do livro " + tituloLivro + ". O usuário " + nomeUsuario + " não tem uma reserva.");
    }

    public static void falhaEmprestimoRepetido(String nomeUsuario, String tituloLivro){
        System.out.println("Não foi possível realizar o empréstimo do livro " + tituloLivro + ". O usuário " + nomeUsuario + " já possui um empréstimo desse livro.");
    }

    public static void sucessoEmprestimo(String nomeUsuario, String tituloLivro){
        System.out.println("O livro " + tituloLivro + " foi emprestado ao usuario " + nomeUsuario + " com sucesso!!");
    }

    public static void falhaReserva(String nomeUsuario){
        System.out.println("Usuário " + nomeUsuario + " excedeu o número de reservas.");
    }

    public static void sucessoReserva(String nomeUsuario, String tituloLivro){
        System.out.println("Reserva do usuário " + nomeUsuario + " para o livro " + tituloLivro + " realizada com sucesso!");
    }

    public static void falhaDevolucao(String nomeUsuario, String tituloLivro){
        System.out.println("Usuário " + nomeUsuario + " não tem empréstimos do livro " + tituloLivro + ".");
    }

    public static void sucessoDevolucao(String nomeUsuario, String tituloLivro){
        System.out.println("Livro " + tituloLivro + " devolvido pelo usuário " + nomeUsuario + " com sucesso!");
    }

    public static void sucessoRegistrarObservador(String nomeUsuario, String tituloLivro){
        System.out.println("Observador " + nomeUsuario + " foi registrado ao livro " + tituloLivro + " com sucesso!");
    }

}
    