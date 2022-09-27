package servicios;

import api.PaginaService;

public class PaginaServiceMDB implements PaginaService {
    private static final String PAGINA_COLLECTION_NAME = "paginas";
    private int dbPort;
    private String dbName;
    public PaginaServiceMDB(int dbPort, String dbName) {
        this.dbPort = dbPort;
        this.dbName = dbName;
    }

    @Override
    public String pagina(String paginaId) {
        return null;
    }
}
