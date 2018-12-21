package com.rui.client.test.rocketmq.version409;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @author: wangruirui
 * @date: 2018/11/05
 */
public class ProducterThread {

    public static void main(String[] args) {
        for (int j=0; j<=5; j++) {
            new Thread(()->{
                int count = 1000;
                // 设置生产者组名
                DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
                // 指定nameServer的地址
                producer.setNamesrvAddr("10.75.236.141:9876;10.75.236.140:9876");

                producer.setInstanceName( UUID.randomUUID().toString().replaceAll("-", ""));
                // 启动实例
                try {
                    producer.start();

                    final Semaphore semaphore = new Semaphore(0);

                    for (int i = 0; i < count; i++) {
                        Message message = null;
                        message = new Message("TopicTest",
                                "test_tag",
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                        producer.send(message);
                    }
                    semaphore.acquire(count);
                    //关闭生产者，释放资源
                    producer.shutdown();
                    System.out.println("发送完毕 关闭链接");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },j+"---thread").start();

        }

    }
}
