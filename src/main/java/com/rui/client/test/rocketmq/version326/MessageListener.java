package com.rui.client.test.rocketmq.version326;

public class MessageListener<T> {

    private boolean flag;

    private T t;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
