package main;

import servicios.PaginaServiceMDB;
import servicios.PublicacionServiceMDB;
import web.WebAPI;

public class Main {
    private static final int API_PORT = 7070;
    private static final int DB_PORT = 27017;
    private static final String DB_NAME = "db_tematica";
    public static void main(String[] args) {
        PaginaServiceMDB paginaServiceMDB = new PaginaServiceMDB(DB_PORT, DB_NAME);
        PublicacionServiceMDB publicacionServiceMDB = new PublicacionServiceMDB(DB_PORT, DB_NAME);

        WebAPI webAPI = new WebAPI(publicacionServiceMDB, paginaServiceMDB, API_PORT);
        webAPI.start();
    }
}
