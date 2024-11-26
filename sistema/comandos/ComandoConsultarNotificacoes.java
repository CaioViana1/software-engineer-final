package sistema.comandos;

import biblioteca.Biblioteca;
import sistema.Parametros;

public class ComandoConsultarNotificacoes implements Comando {
    @Override
    public void executar(Parametros parametros) {
        Biblioteca biblioteca = Biblioteca.getInstancia();

        String codigoUsuario = parametros.getCodigo(1);
        int ntf = biblioteca.consultarNotificações(codigoUsuario);
        
        System.out.println("Quantidade de notificações: " + ntf);
    }
}
