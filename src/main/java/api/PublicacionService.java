package api;

public interface PublicacionService {
    String ultimasPublicaciones();

    String publicacion(String publicacionId);

    String publicaciones(String nombre);

    String autoresPublicaciones();

    String publicacionesPorTexto(String texto);
}
