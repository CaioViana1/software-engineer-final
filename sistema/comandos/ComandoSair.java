package sistema.comandos;

import sistema.Parametros;

public class ComandoSair implements Comando {
    @Override
    public void executar(Parametros parametros) {
        System.out.println("Encerrando o sistema. Até mais!");
        System.exit(0);
    }
}
