package com.daniel.aysnctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

}
