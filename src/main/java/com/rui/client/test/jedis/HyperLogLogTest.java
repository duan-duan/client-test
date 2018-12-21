package com.rui.client.test.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: redis 统计 test
 * @author: wangruirui
 * @date: 2018/09/14
 */
public class HyperLogLogTest {

    public static void main(String[] args){
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("10.112.29.150",7000));
        jedisClusterNodes.add(new HostAndPort("10.112.29.151",7000));



        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,2000,3,"8grvTlOPImGRdgPp",new GenericObjectPoolConfig());
        jc.set("foo1", "bar1");
        String value1 = jc.get("foo1");
        System.out.println(value1);


        for (int i = 0; i < 1000; i++) {
            jc.pfadd("codehole", "user" + i);
         }
        long total = jc.pfcount("codehole");
         System.out.printf("%d %d\n", total, 1000);

         jc.del("codehole");

    }



}
