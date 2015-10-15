package com.daniel.aysnctask;

/**
 * Created by daniel on 2015/5/12.
 */
public  class ThreadTask implements Runnable {
    int delay;

    public ThreadTask() {
        this.delay = 0;
    }

    public ThreadTask(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void doInWorkThread(){
    }

    public void doInMainThread(){

    }
    @Override
    public void run(){
        doInMainThread();
    }
}
