package sistema.comandos;

import biblioteca.Biblioteca;
import sistema.Parametros;

public class ComandoListarUsuario implements Comando{
    @Override
    public void executar(Parametros parametros) {
        Biblioteca biblioteca = Biblioteca.getInstancia();

        String codigoUsuario = parametros.getCodigo(1);
        biblioteca.consultarInformacoesUsuario(codigoUsuario);
    }
}
