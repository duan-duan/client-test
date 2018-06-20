package com.rui.client.test.rocketmq.version326;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class DemoMessageListener extends AbstractMessageListener {


    @Override
    public <T> void execute(T t) {

    }

    @Override
    public MessageListener uniqueCheck(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + messages + "%n");
        MessageListener obj = new MessageListener();
        obj.setFlag(true);
        obj.setT(new Object());
        return obj;
    }
}
