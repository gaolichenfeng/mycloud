package cn.dockers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class testDocker {
	@Test
	public void testss() {
		Jedis jedis = new Jedis("192.168.64.150", 7000);
		jedis.set("11", "123");
		String string = jedis.get("11");
		System.out.println(string);
	}
	@Test
	public void test2() {
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(500);
		cfg.setMaxIdle(20);
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.64.150", 7000));
		shards.add(new JedisShardInfo("192.168.64.150", 7001));
		shards.add(new JedisShardInfo("192.168.64.150", 7002));
		
		ShardedJedisPool pool = new ShardedJedisPool(cfg, shards);
		
		ShardedJedis j = pool.getResource();
		for (int i = 0; i < 100; i++) {
			j.set("key"+i, "value"+i);
		}
		
		pool.close();
	}

}
