package sistema.comandos;

import java.text.ParseException;
import sistema.Parametros;

public interface Comando {
    public void executar(Parametros parametros) throws ParseException;
}
