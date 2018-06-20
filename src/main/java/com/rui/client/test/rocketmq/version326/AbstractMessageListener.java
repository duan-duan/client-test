package com.rui.client.test.rocketmq.version326;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public abstract class AbstractMessageListener implements MessageListenerConcurrently {

    public abstract <T> void execute(T t);

    public abstract MessageListener uniqueCheck (List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        //1.解析消息 检查消息唯一，幂等 校验
        MessageListener obj = uniqueCheck(messages,consumeConcurrentlyContext);
        //2.处理业务国际
        if(obj.isFlag()){
            execute(obj.getT());
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
