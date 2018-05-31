package com.rui.client.test.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Jreids {


    public static void main(String[] args) {

        Jedis jedis = new Jedis("10.60.58.194",6379);
//        Jedis jedis = new Jedis("10.60.54.229",6379);//8grvTlOPImGRdgPp
        jedis.auth("ldalkfwie2981dls");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("10.60.58.194", 7000));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,2000,3,"ldalkfwie2981dls",new GenericObjectPoolConfig());
        jc.set("foo1", "bar1");
        String value1 = jc.get("foo1");
        System.out.println(value1);

    }
}
