package sistema;

import java.util.ArrayList;

public class Parametros {
    private ArrayList<String> parametros;

    public Parametros (ArrayList<String> parametros){
        this.parametros = parametros;
    }

    public String getCodigo(int codigo) {
        return parametros.get(codigo-1);
    }

}
