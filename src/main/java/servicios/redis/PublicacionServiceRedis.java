package servicios.redis;

import api.PublicacionService;
import org.json.JSONArray;
import org.json.JSONObject;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.search.Document;
import java.util.List;
import java.util.Map;

public class PublicacionServiceRedis implements PublicacionService {
    private String url;

    public PublicacionServiceRedis(String url) {
        this.url = url;
    }

    @Override
    public String ultimasPublicaciones() {
        try (JedisPooled jedis = new JedisPooled(this.url)) {
            List<String> pubsPorFecha = jedis.zrange("pubsPorFecha", 0, 3);
            List<JSONArray> jsonArray = jedis.jsonMGet(pubsPorFecha.toArray(new String[pubsPorFecha.size()]));
            JSONArray jsonArrayPlain = new JSONArray();
            for (JSONArray json : jsonArray) {
                jsonArrayPlain.put(json.getJSONObject(0));
            }
            return jsonArrayPlain.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String publicacion(String publicacionId) {
        try (JedisPooled jedis = new JedisPooled(this.url)) {
            Object jsonObject = jedis.jsonGet("post:" + publicacionId);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String publicaciones(String nombre) {
        try (JedisPooled jedis = new JedisPooled(this.url)) {
            List<Document> resultado = jedis.ftSearch("postIdx", "@autor:" + nombre).getDocuments();
            JSONArray jsonArray = new JSONArray();
            for (Document document : resultado) {
                document.getProperties().forEach(p -> {
                    jsonArray.put(p.getValue());
                });
            }
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String autoresPublicaciones() {
        try (JedisPooled jedis = new JedisPooled(this.url)) {
            Map<String, String> resultado = jedis.hgetAll("autor:pubs");
            JSONArray resultadoJSON = new JSONArray();
            resultado.entrySet().stream()
                    .map(e -> {
                        JSONObject address = new JSONObject();
                        address.put("_id", e.getKey());
                        address.put("count", e.getValue());
                        return address;
                    })
                    .forEach(resultadoJSON::put);
            return resultadoJSON.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String publicacionesPorTexto(String texto) {
        try (JedisPooled jedis = new JedisPooled(this.url)) {
            List<Document> resultado = jedis.ftSearch("postIdx", "@texto:" + texto).getDocuments();
            JSONArray jsonArray = new JSONArray();
            for (Document document : resultado) {
                document.getProperties().forEach(p -> {
                    jsonArray.put(p.getValue());
                });
            }
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
