package servicios.redis;

import api.PaginaService;
import redis.clients.jedis.JedisPooled;

public class PaginaServiceRedis  implements PaginaService {
    private final static String PAGINAS_KEY = "paginas";
    private RedisSentinel sentinels;

    public PaginaServiceRedis(RedisSentinel sentinels) {
        this.sentinels = sentinels;
    }

    @Override
    public String pagina(String paginaId) {
        try (JedisPooled jedis = sentinels.getJedisMaster()) {
            return jedis.hget(PAGINAS_KEY, "pagina:" + paginaId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
