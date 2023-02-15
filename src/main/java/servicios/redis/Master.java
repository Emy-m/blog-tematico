package servicios.redis;

import redis.clients.jedis.UnifiedJedis;

public interface Master {
    UnifiedJedis getMaster();
}
