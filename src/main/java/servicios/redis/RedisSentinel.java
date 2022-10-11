package servicios.redis;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

public class RedisSentinel {
    private JedisSentinelPool sentinels;
    private String user;
    private String password;

    public RedisSentinel(Set<String> sentinels, String groupName, String user, String password){
        this.sentinels = new JedisSentinelPool(groupName, sentinels);
        this.user = user;
        this.password = password;
    }

    public JedisPooled getJedisMaster() {
        return new JedisPooled(sentinels.getCurrentHostMaster().getHost(), sentinels.getCurrentHostMaster().getPort(), user, password);
    }
}
