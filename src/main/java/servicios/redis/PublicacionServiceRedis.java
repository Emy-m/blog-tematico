package servicios.redis;

import api.PublicacionService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.search.Document;
import web.PostDTO;

import java.util.List;
import java.util.Map;

public class PublicacionServiceRedis implements PublicacionService {
    private Master master;

    public PublicacionServiceRedis(Master master) {
        this.master = master;
    }

    @Override
    public String ultimasPublicaciones() {
        try (UnifiedJedis jedis = master.getMaster()) {
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
        try (UnifiedJedis jedis = master.getMaster()) {
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
        try (UnifiedJedis jedis = master.getMaster()) {
            List<Document> resultado = jedis.ftSearch("postIdx", "@autor:" + nombre).getDocuments();
            JSONArray jsonArray = new JSONArray();
            Gson gson = new Gson();
            for (Document document : resultado) {
                document.getProperties().forEach(p -> {
                    PostDTO post = gson.fromJson(p.getValue().toString(), PostDTO.class);
                    JSONObject postJson = new JSONObject(gson.toJson(post));
                    jsonArray.put(postJson);
                });
            }
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String autoresPublicaciones() {
        try (UnifiedJedis jedis = master.getMaster()) {
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
        try (UnifiedJedis jedis = master.getMaster()) {
            List<Document> resultado = jedis.ftSearch("postIdx", "@texto:" + texto).getDocuments();
            JSONArray jsonArray = new JSONArray();
            Gson gson = new Gson();
            for (Document document : resultado) {
                document.getProperties().forEach(p -> {
                    PostDTO post = gson.fromJson(p.getValue().toString(), PostDTO.class);
                    JSONObject postJson = new JSONObject(gson.toJson(post));
                    jsonArray.put(postJson);
                });
            }
            return jsonArray.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
