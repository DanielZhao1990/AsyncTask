package com.daniel.aysnctask;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by daniel on 2015/5/12.
 */
public class Executor {
    private static Executor ourInstance = new Executor();

    public static Executor getInstance() {
        return ourInstance;
    }
    public static final int CAPACITY = 30;
    public static final int DO_ADD_TASK = -99999999;
    ArrayBlockingQueue<ThreadTask> tasks = new ArrayBlockingQueue<ThreadTask>(CAPACITY);
    private boolean mQuit = false;
    /**
     * 异步任务的执行者
     */
    ThreadExecutor threadExecutor;
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DO_ADD_TASK:
                    addTask((ThreadTask) msg.obj);
                    break;
            }

        }
    };
    private Executor() {
        threadExecutor = new ThreadExecutor();
        threadExecutor.start();
    }

    public void addTask(ThreadTask threadTask) {
        tasks.add(threadTask);
    }

    /**
     * delay秒后将Task添加到队列中.
     * @param threadTask
     * @param delay
     */
    public void addTaskDelayed(ThreadTask threadTask, int delay) {
        Message msg = handler.obtainMessage(DO_ADD_TASK);
        msg.obj = threadTask;
        handler.sendMessageDelayed(msg, delay);
    }

    public void quit() {
        mQuit = true;
        threadExecutor.quit();
    }

    public class ThreadExecutor extends Thread {
        public void quit() {
            interrupt();
        }
        @Override
        public void run() {
            super.run();
            try {//防止线程崩溃
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                while (true) {
                    ThreadTask task;
                    try {
                        task = tasks.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if (mQuit) {
                            return;
                        }
                        continue;
                    }
                    task.doInWorkThread();
                    handler.postDelayed(task, task.getDelay());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
