package com.rui.client.test.rocketmq.version409;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Description:
 * @author: wangruirui
 * @date: 2018/11/05
 */
public class ConsumerThread {
    public static void main(String[] args) {

        for (int j = 0; j < 10; j++) {

            new Thread(()->{

                //设置消费者组名
                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
                //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
                //指定nameServer的地址
                consumer.setNamesrvAddr("10.75.236.141:9876;10.75.236.140:9876");

                consumer.setInstanceName(UUID.randomUUID().toString().replaceAll("-", ""));
                //指定订阅的topic及tag表达式
                try {
                    consumer.subscribe("TopicTest", "*");

                    consumer.registerMessageListener(new MessageListenerConcurrently() {
                        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                        ConsumeConcurrentlyContext context) {
                            MessageExt messageExt = msgs.get(0);
                            System.out.println(String.format("Custome message [%s],tagName[%s]",
                                    new String(messageExt.getBody()),
                                    messageExt.getTags())
                                    +Thread.currentThread().getName()
                                    +"magId--"+(msgs.get(0).getMsgId())
                                    +"quId"+(msgs.get(0).getQueueId()+""));
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    });
                    //启动消费者实例
                    consumer.start();
                    System.out.println("Consumer Started.");
                } catch (MQClientException e) {
                    e.printStackTrace();
                }

            },j+"---thread").start();

        }
    }
}

