# AsyncTask
A helper to do async task

> 用来执行异步任务的工具类.

- 用法
```
  Executor.getInstance().addTask(new ThreadTask()
        {
            @Override
            public void doInWorkThread() {
                super.doInWorkThread();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void doInMainThread() {
                super.doInMainThread();
                Toast.makeText(getApplicationContext(),"任务完成啦",Toast.LENGTH_LONG).show();

            }
        });
```

- 执行顺序
doInWorkThread  - 工作线程执行
      |
doInMainThread  -  主线程执行
