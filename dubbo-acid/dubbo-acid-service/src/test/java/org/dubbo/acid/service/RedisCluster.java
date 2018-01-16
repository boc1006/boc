package org.dubbo.acid.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCluster {
	@org.junit.Test
	public void testRedisCluster() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		HostAndPort hostAndPort = new HostAndPort("172.16.2.151", 6379);
		HostAndPort hostAndPort1 = new HostAndPort("172.16.2.151", 6380);
		HostAndPort hostAndPort2 = new HostAndPort("172.16.2.151", 6381);
		HostAndPort hostAndPort3 = new HostAndPort("172.16.2.151", 26379);
		HostAndPort hostAndPort4 = new HostAndPort("172.16.2.151", 26380);
		HostAndPort hostAndPort5 = new HostAndPort("172.16.2.151", 26381);
		nodes.add(hostAndPort);
		nodes.add(hostAndPort1);
		nodes.add(hostAndPort2);
		nodes.add(hostAndPort3);
		nodes.add(hostAndPort4);
		nodes.add(hostAndPort5);
		JedisCluster jedisCluster = null;
		try {
			jedisCluster = new JedisCluster(nodes, poolConfig);
			String string = jedisCluster.get("key1");
			System.out.println(string);
			String s = jedisCluster.set("java", "v8.0");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedisCluster != null) {
				try {
					jedisCluster.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
