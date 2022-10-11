package main;

import servicios.mongo.PaginaServiceMDB;
import servicios.mongo.PublicacionServiceMDB;
import servicios.redis.PaginaServiceRedis;
import servicios.redis.PublicacionServiceRedis;
import servicios.redis.RedisSentinel;
import web.WebAPI;

import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final int API_PORT = 7070;
    private static final int MDB_PORT = 27017;
    private static final String MDB_NAME = "db_tematica";

    private static final String REDIS_URL = "redis://default:redispw@localhost:6379";
    private static final String REDIS_USER = "default";
    private static final String REDIS_PASS = "redispw";
    private static final String REDIS_SENTINEL_GROUP = "mymaster";

    public static void main(String[] args) {
        PaginaServiceMDB paginaServiceMDB = new PaginaServiceMDB(MDB_PORT, MDB_NAME);
        PublicacionServiceMDB publicacionServiceMDB = new PublicacionServiceMDB(MDB_PORT, MDB_NAME);

        Set<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:5000");
        sentinels.add("127.0.0.1:5001");
        sentinels.add("127.0.0.1:5002");
        RedisSentinel redisSentinel = new RedisSentinel(sentinels, REDIS_SENTINEL_GROUP, REDIS_USER, REDIS_PASS);

        PaginaServiceRedis paginaServiceRedis = new PaginaServiceRedis(redisSentinel);
        PublicacionServiceRedis publicacionServiceRedis = new PublicacionServiceRedis(redisSentinel);

        WebAPI webAPI = new WebAPI(publicacionServiceRedis, paginaServiceRedis, API_PORT);
        webAPI.start();
    }
}
