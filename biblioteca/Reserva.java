package biblioteca;

import usuarios.Usuario;

import java.util.Date;

public class Reserva {
    private Livro livro;
    private Usuario usuario;
    private Date data;
    private boolean finalizada;

    public Reserva(Usuario usuario, Livro livro, Date data){
        this.usuario = usuario;
        this.livro = livro;
        this.finalizada = false;
        this.data = data;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
