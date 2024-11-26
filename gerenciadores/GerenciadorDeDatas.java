package gerenciadores;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GerenciadorDeDatas {
    public static Date dataAsDate(String data) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.parse(data);
    }

    public static Date getDataDeDevolucao(Date data, int deadline) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, deadline);
        return c.getTime();
    }
}
