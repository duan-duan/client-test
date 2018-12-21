package com.rui.client.test.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JeidsTest {


    public static void main(String[] args) {

//        Jedis jedis = new Jedis("10.60.58.194",6379);
////        Jedis jedis = new Jedis("10.60.54.229",6379);//8grvTlOPImGRdgPp
//        jedis.auth("ldalkfwie2981dls");
//        jedis.set("foo", "bar");
//        String value = jedis.get("foo");
//        System.out.println(value);

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically

        jedisClusterNodes.add(new HostAndPort("10.75.229.81", 7000));
//        jedisClusterNodes.add(new HostAndPort("10.75.217.44", 7000));
//        jedisClusterNodes.add(new HostAndPort("10.75.217.45", 7000));
//        jedisClusterNodes.add(new HostAndPort("10.75.217.47", 7000));
//        jedisClusterNodes.add(new HostAndPort("10.75.217.48", 7000));
//        jedisClusterNodes.add(new HostAndPort("10.75.217.51", 7000));
//        jedisClusterNodes.add(new HostAndPort("10.75.217.52", 7000));

//        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,2000,3,"ldalkfwie2981dls",new GenericObjectPoolConfig());
//        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,2000,3,"8grvTlOPImGRdgPp",new GenericObjectPoolConfig());
//        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,2000,3,"eSeUZz2vlEJo6uQ6",new GenericObjectPoolConfig());
        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,2000,3,"letv123456",new GenericObjectPoolConfig());


//        jc.get("common:trade_user_userinfo_product10411743");
        jc.set("foo1", "bar1");
        jc.incr("kak");
        String value1 = jc.get("foo1");


        //限速器
        long value = jc.incr("kaka");
        if(value == 1){
            jc.expire("kaka",1000);
        }if(value>11){
            System.out.println("too many requests per second");
        }


        long kak = jc.pttl("foo1");
       // System.out.println(value1);
        for (int i = 0; i < 10000; i++) {
            long t= System.currentTimeMillis();
            System.out.println(jc.get("common:trade_user_userinfo_product10411743"));
            System.out.println(System.currentTimeMillis()-t+"----");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
