package main;

import servicios.mongo.PaginaServiceMDB;
import servicios.mongo.PublicacionServiceMDB;
import servicios.redis.PaginaServiceRedis;
import servicios.redis.PublicacionServiceRedis;
import web.WebAPI;

public class Main {
    private static final int API_PORT = 7070;
    private static final int MDB_PORT = 27017;
    private static final String MDB_NAME = "db_tematica";

    private static final String REDIS_URL = "redis://default:redispw@localhost:49153";

    public static void main(String[] args) {
        PaginaServiceMDB paginaServiceMDB = new PaginaServiceMDB(MDB_PORT, MDB_NAME);
        PublicacionServiceMDB publicacionServiceMDB = new PublicacionServiceMDB(MDB_PORT, MDB_NAME);

        PaginaServiceRedis paginaServiceRedis = new PaginaServiceRedis(REDIS_URL);
        PublicacionServiceRedis publicacionServiceRedis = new PublicacionServiceRedis(REDIS_URL);

        WebAPI webAPI = new WebAPI(publicacionServiceRedis, paginaServiceRedis, API_PORT);
        webAPI.start();
    }
}
