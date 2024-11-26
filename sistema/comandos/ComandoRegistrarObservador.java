package sistema.comandos;

import biblioteca.Biblioteca;
import sistema.Parametros;

public class ComandoRegistrarObservador implements Comando{
    @Override
    public void executar(Parametros parametros) {
        Biblioteca biblioteca = Biblioteca.getInstancia();

        String codigoUsuario = parametros.getCodigo(1);
        String codigoLivro = parametros.getCodigo(2);

        biblioteca.registrarObservador(codigoUsuario, codigoLivro);


    }
}
