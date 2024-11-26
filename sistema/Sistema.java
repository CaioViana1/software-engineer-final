package sistema;

import java.text.ParseException;
import java.util.*;

import biblioteca.*;
import gerenciadores.Fabrica;
import sistema.comandos.*;

public class Sistema {
    private final HashMap<String, Comando> comandos = new HashMap<String, Comando>();
    private static Sistema instancia;

    private Sistema() {
        initComandos();
        initDados();
    }

    public static Sistema getInstancia(){
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public static void run(){
        Sistema sistema = getInstancia();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nSeja Bem-vindo à Biblioteca!\n");

        System.out.println("Digite um comando (ex: emp 123 100 20/03/2002) ou 'sai' para encerrar:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            ArrayList<String> leitura = new ArrayList<>(Arrays.asList(input.split(" ")));
            ArrayList<String> parametros = new ArrayList<>(leitura.subList(1, leitura.size()));
            
            Parametros parametros2 = Fabrica.criarParametros(parametros);

            try {
                // O comando está na primeira posição
                if (input.equalsIgnoreCase("sai")) {
                    sistema.executarComando(leitura.get(0), parametros2);
                    break;
                }
                else{
                    sistema.executarComando(leitura.get(0), parametros2);
                }

            } catch (ParseException e) {
                System.out.println("Erro ao executar o comando: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Comando desconhecido.");
            }
        }

        scanner.close();
    }

    private void initComandos() {
        comandos.put("emp", new ComandoEmprestar());
        comandos.put("dev", new ComandoDevolver());
        comandos.put("liv", new ComandoConsultarLivro());
        comandos.put("res", new ComandoReservar());
        comandos.put("obs", new ComandoRegistrarObservador());
        comandos.put("usu", new ComandoListarUsuario());
        comandos.put("ntf", new ComandoConsultarNotificacoes());
        comandos.put("sai", new ComandoSair());
    }

    public void executarComando(String strComando, Parametros parametros) throws ParseException {
        Comando comando = comandos.get(strComando);
        comando.executar(parametros);
    }

    public void initDados(){
        Biblioteca biblioteca = Biblioteca.getInstancia();

        // Inserindo usuarios da tabela
        biblioteca.addUsuario(Fabrica.criarAlunoGraduacao("João da Silva", "123"));
        biblioteca.addUsuario(Fabrica.criarAlunoPos("Luiz Fernando Rodrigues", "456"));
        biblioteca.addUsuario(Fabrica.criarAlunoGraduacao("Pedro Paulo", "789"));
        biblioteca.addUsuario(Fabrica.criarProfessor("Carlos Lucena", "100"));

        // autores
        ArrayList<String> autores1 = new ArrayList<>(List.of("Ian Sommervile"));
        ArrayList<String> autores2 = new ArrayList<>(List.of("Grady Booch", "James Rumbaugh", "Ivar Jacobson"));
        ArrayList<String> autores3 = new ArrayList<>(List.of("Steve McConnell"));
        ArrayList<String> autores4 = new ArrayList<>(List.of("Robert Martin"));
        ArrayList<String> autores5 = new ArrayList<>(List.of("Martin Fowler"));
        ArrayList<String> autores6 = new ArrayList<>(List.of("Ian Sommervile"));
        ArrayList<String> autores7 = new ArrayList<>(List.of("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"));
        ArrayList<String> autores8 = new ArrayList<>(List.of("Martin Fowler"));

        // Inserido Livros da tabela
        biblioteca.addLivro(Fabrica.criarLivro("100", "Engenharia de Software", autores1, 2000, 6, "AddisonWesley", 2));
        biblioteca.addLivro(Fabrica.criarLivro("101", "UML – Guia do Usuário", autores2, 2000, 7, "Campus", 1));
        biblioteca.addLivro(Fabrica.criarLivro("200", "Code Complete", autores3, 2014, 2, "Microsoft Press", 1));
        biblioteca.addLivro(Fabrica.criarLivro("201", "Agile Software Development, Principles, Patterns, and Practices", autores4, 2002, 1, "Prentice Hall", 1));
        biblioteca.addLivro(Fabrica.criarLivro("300", "Refactoring: Improving the Design of Existing Code", autores5, 1999, 1, "Addison-Wesley Professional", 2));
        biblioteca.addLivro(Fabrica.criarLivro("301", "Software Metrics: A Rigorous and Practical Approach", autores6, 2014, 3, "CRC Press", 3));
        biblioteca.addLivro(Fabrica.criarLivro("400", "Design Patterns: Elements of Reusable Object-Oriented Software", autores7, 1994, 1, "Addison-Wesley Professional", 2));
        biblioteca.addLivro(Fabrica.criarLivro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", autores8, 2003, 3, "Addison-Wesley Professional", 1));
    }
}
