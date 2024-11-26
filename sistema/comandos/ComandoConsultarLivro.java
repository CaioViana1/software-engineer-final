package sistema.comandos;

import biblioteca.Biblioteca;
import sistema.Parametros;

public class ComandoConsultarLivro implements Comando {
    @Override
    public void executar(Parametros parametros) {
        Biblioteca biblioteca = Biblioteca.getInstancia();

        String codigoLivro = parametros.getCodigo(1);
        biblioteca.consultarInformacoesLivro(codigoLivro);
    }
}
