package servicios.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.UnifiedJedis;

import java.util.Set;

public class RedisCluster implements Master {
    private JedisCluster jedisCluster;

    public RedisCluster(Set<HostAndPort> clusters) {
        this.jedisCluster = new JedisCluster(clusters);
    }

    @Override
    public UnifiedJedis getMaster() {
        return jedisCluster;
    }
}
