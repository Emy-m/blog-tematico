package servicios.redis;

import api.PaginaService;
import redis.clients.jedis.Jedis;

public class PaginaServiceRedis  implements PaginaService {
    private final static String PAGINAS_KEY = "paginas";
    private String url;

    public PaginaServiceRedis(String url) {
        this.url = url;
    }

    @Override
    public String pagina(String paginaId) {
        Jedis jedis = new Jedis(this.url);
        return jedis.hget(PAGINAS_KEY, "pagina:" + paginaId);
    }
}
