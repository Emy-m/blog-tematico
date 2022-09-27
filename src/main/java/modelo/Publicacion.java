package modelo;

import java.util.Date;
import java.util.List;

public class Publicacion {
    private String titulo;
    private String texto;
    private List<String> tags;
    private List<String> linksRelacionados;
    private String autor;
    private Date fechaPublicacion;

    public Publicacion(String titulo, String texto, List<String> tags, List<String> linksRelacionados, String autor,
                       Date fechaPublicacion) {
        this.titulo = titulo;
        this.texto = texto;
        this.tags = tags;
        this.linksRelacionados = linksRelacionados;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getLinksRelacionados() {
        return linksRelacionados;
    }

    public void setLinksRelacionados(List<String> linksRelacionados) {
        this.linksRelacionados = linksRelacionados;
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
        return "Publicacion{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", tags=" + tags +
                ", linksRelacionados=" + linksRelacionados +
                ", autor='" + autor + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
