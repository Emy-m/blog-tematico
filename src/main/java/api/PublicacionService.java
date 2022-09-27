package api;

public interface PublicacionService {
    String ultimosPosts();

    String post(String postId);

    String posts(String nombre);

    String autoresPosts();

    String postsPorTexto(String texto);
}
