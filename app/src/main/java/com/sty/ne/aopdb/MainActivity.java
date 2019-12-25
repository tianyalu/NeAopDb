package com.sty.ne.aopdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过面向切面动态代理的方式实现数据库操作前对数据进行保存的操作
 */
public class MainActivity extends AppCompatActivity implements DBOperation{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnOperate;
    private DBOperation db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = (DBOperation) Proxy.newProxyInstance(DBOperation.class.getClassLoader(),
                new Class[]{DBOperation.class}, new DBHandler(this));

        btnOperate = findViewById(R.id.btn_operate);
        btnOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnOperateClicked();
            }
        });
    }

    private void onBtnOperateClicked() {
//        startActivity(new Intent(MainActivity.this, OrderActivity.class));
        db.delete();
    }

    class DBHandler implements InvocationHandler{
        private DBOperation db;

        DBHandler(DBOperation db) {
            this.db = db;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //切面
            if(db != null) {
                Log.i(TAG, "操作数据库之前，开始备份...");
                save(); //查询数据库后备份，详细过程省略
                Log.i(TAG, "数据备份完成，等待操作...");
                return method.invoke(db, args);
            }
            return null;
        }
    }

    @Override
    public void insert() {
        Log.i(TAG, "新增数据");
    }

    @Override
    public void delete() {
        Log.i(TAG, "删除数据");
    }

    @Override
    public void update() {
        Log.i(TAG, "更新数据");
    }

    @Override
    public void save() {
        Log.i(TAG, "保存数据");
    }
}
