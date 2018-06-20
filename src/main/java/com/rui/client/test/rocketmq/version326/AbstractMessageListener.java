package com.rui.client.test.rocketmq.version326;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public abstract class AbstractMessageListener implements MessageListenerConcurrently {

    public abstract boolean uniqueCheck(String id);

    public abstract String execute(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        //1.解析消息 返回唯一id
        String id = execute(messages,consumeConcurrentlyContext);
        //2.检查消息唯一，幂等 校验
        uniqueCheck(id);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
