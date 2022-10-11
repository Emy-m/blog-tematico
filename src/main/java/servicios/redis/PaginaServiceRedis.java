package servicios.redis;

import api.PaginaService;
import org.json.JSONArray;
import redis.clients.jedis.JedisPooled;

public class PaginaServiceRedis  implements PaginaService {
    private RedisSentinel sentinels;

    public PaginaServiceRedis(RedisSentinel sentinels) {
        this.sentinels = sentinels;
    }

    @Override
    public String pagina(String paginaId) {
        try (JedisPooled jedis = sentinels.getJedisMaster()) {
            Object jsonObject = jedis.jsonGet("page:" + paginaId);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
