package web;

import java.util.List;

public class PostDTO {
    private String id;
    private String titulo;
    private String texto;
    private String resumen;
    private String autor;
    private String fecha;
    private List<String> tags;
    private List<String> relatedLinks;

    public PostDTO(String id, String titulo, String texto, String resumen, String autor, String fecha, List<String> tags, List<String> relatedLinks) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.resumen = resumen;
        this.autor = autor;
        this.fecha = fecha;
        this.tags = tags;
        this.relatedLinks = relatedLinks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(List<String> relatedLinks) {
        this.relatedLinks = relatedLinks;
    }
}