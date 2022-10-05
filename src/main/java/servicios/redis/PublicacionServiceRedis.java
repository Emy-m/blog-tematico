package servicios.redis;

import api.PublicacionService;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Set;

public class PublicacionServiceRedis implements PublicacionService {
    private final static String PUBLICACIONES_KEY = "publicaciones";
    private String url;

    public PublicacionServiceRedis(String url) {
        this.url = url;
    }

    @Override
    public String ultimasPublicaciones() {
        Jedis jedis = new Jedis(this.url);
        List<String> pubsPorFecha = jedis.zrange("pubsPorFecha", 0, 3);
        List<String> resultado = jedis.hmget(PUBLICACIONES_KEY, pubsPorFecha.toArray(new String[pubsPorFecha.size()]));
        return resultado.toString();
    }

    @Override
    public String publicacion(String publicacionId) {
        Jedis jedis = new Jedis(this.url);
        return jedis.hget(PUBLICACIONES_KEY, "publicacion:" + publicacionId);
    }

    @Override
    public String publicaciones(String nombre) {
        Jedis jedis = new Jedis(this.url);
        Set<String> comparacion = jedis.sinter(new String[]{"pubsPorNombre:" + nombre, "indicePubs"});
        List<String> resultado = jedis.hmget(PUBLICACIONES_KEY, comparacion.toArray(new String[comparacion.size()]));
        return resultado.toString();
    }

    @Override
    public String autoresPublicaciones() {
        /*Hacer un hash o set en redis para cada autor con su cantidad de publicaciones, cada ves que se agrega una aumentaria y asi*/
        return null;
    }

    @Override
    public String publicacionesPorTexto(String texto) {
        /*buscar info de como agregar la extension a redis para hacer esto*/
        return null;
    }
}
