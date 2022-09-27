package servicios;

import api.PublicacionService;

public class PublicacionServiceMDB implements PublicacionService {
    private static final String PUBLICACION_COLLECTION_NAME = "publicacion";
    private int dbPort;
    private String dbName;
    public PublicacionServiceMDB(int dbPort, String dbName) {
        this.dbName = dbName;
        this.dbPort = dbPort;
    }

    @Override
    public String ultimosPosts() {
        return null;
    }

    @Override
    public String post(String postId) {
        return null;
    }

    @Override
    public String posts(String nombre) {
        return null;
    }

    @Override
    public String autoresPosts() {
        return null;
    }

    @Override
    public String postsPorTexto(String texto) {
        return null;
    }
}
