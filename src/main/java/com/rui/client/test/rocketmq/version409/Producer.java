package com.rui.client.test.rocketmq.version409;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.Semaphore;

public class Producer {

    private static int count = 10;

    public static void main(String[] args) throws Exception {
        // 设置生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        // 指定nameServer的地址
        producer.setNamesrvAddr("10.75.236.141:9876;10.75.236.140:9876");

        // 启动实例
        producer.start();

        final Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < count; i++) {
            Thread.sleep(1000);
            Message message = new Message("TopicTest",
                    "test_tag",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.println(String.format("message [%s] send success!", new String(message.getBody())));
                    semaphore.release();
                }

                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });

        }
        semaphore.acquire(count);
        //关闭生产者，释放资源
        producer.shutdown();
    }
}
