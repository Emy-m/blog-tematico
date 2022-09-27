package modelo;

import java.util.Date;

public class Pagina {
    private String titulo;
    private String texto;
    private String autor;
    private Date fechaPublicacion;

    public Pagina(String titulo, String texto, String autor, Date fechaPublicacion) {
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "Pagina{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                '}';
    }
}
