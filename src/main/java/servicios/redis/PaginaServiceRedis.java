package servicios.redis;

import api.PaginaService;
import org.json.JSONArray;
import redis.clients.jedis.UnifiedJedis;

public class PaginaServiceRedis  implements PaginaService {
    private Master master;

    public PaginaServiceRedis(Master master) {
        this.master = master;
    }

    @Override
    public String pagina(String paginaId) {
        try (UnifiedJedis jedis = master.getMaster()) {
            Object jsonObject = jedis.jsonGet("page:" + paginaId);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
