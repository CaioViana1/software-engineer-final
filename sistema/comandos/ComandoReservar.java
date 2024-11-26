package sistema.comandos;

import java.text.ParseException;
import biblioteca.Biblioteca;
import sistema.Parametros;

public class ComandoReservar implements Comando {
    @Override
    public void executar(Parametros parametros) throws ParseException {
        Biblioteca biblioteca = Biblioteca.getInstancia();

        String codigoUsuario = parametros.getCodigo(1);
        String codigoLivro = parametros.getCodigo(2);
        String data = parametros.getCodigo(3);

        biblioteca.criarReservaParaUsuario(codigoUsuario, codigoLivro, data);
    }
}
