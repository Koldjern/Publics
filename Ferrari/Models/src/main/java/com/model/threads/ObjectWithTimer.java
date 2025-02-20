package com.model.threads;
//anders
//knows how to get stuff and, when to drop it
public class ObjectWithTimer {
    private int seconds;
    private boolean extra;
    private Thread timer;
    private Function function;
    private Object value;
    private Action afterTime;
    public ObjectWithTimer(Function function, int seconds) {
        this.seconds = seconds;
        this.function = function;
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                value = function.function();
    }});
        timer.start();
    }

    public ObjectWithTimer(Function function, int seconds, Action afterTime) {
        this(function, seconds);
        this.afterTime = afterTime;
    }
    public Object getValue() {
        if(timer == null)
            timer();
        else
            extra = true;
        return value == null ? value = function.function() : value;
    }
    private void timer() {
        extra = true;
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (extra) {
                        extra = false;
                        Thread.sleep(seconds * 1000);
                    }
                    if (afterTime != null)
                        afterTime.action();
                    value = null;
                    timer = null;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timer.start();
    }
}
