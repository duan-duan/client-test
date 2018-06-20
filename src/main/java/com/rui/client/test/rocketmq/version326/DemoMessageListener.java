package com.rui.client.test.rocketmq.version326;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class DemoMessageListener extends AbstractMessageListener {

    @Override
    public boolean uniqueCheck(String id) {
        return false;
    }

    @Override
    public String execute(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + messages + "%n");
        return null;
    }
}
