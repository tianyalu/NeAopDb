# NeAopDb 面向切面架构设计之动态代理方式：操作数据库之前进行备份操作
## 一、前言
### 1.1 AOP
AOP：`Aspect Oriented Programming`，面向切面编程，通过预编译方式和运行期动态代理实现程序功能统一维护的一种技术。  
AOP是OOP（`Object Oriented Programming`，面向对象）的延续。
### 1.2 AOP两种实现 
![image](https://github.com/tianyalu/NeAopDb/raw/master/show/aop_implementations.png)  
### 1.3 怎样理解AOP
本质是在一系列纵向的业务流程中，把那些有相同业务的子流程抽取成一个横向的面。  

![image](https://github.com/tianyalu/NeAopDb/raw/master/show/aop_understand.png)  

## 二、实操
通过动态代理实现切面的需求：每次操作数据库之前都保存数据。    

![image](https://github.com/tianyalu/NeAopDb/raw/master/show/dynamic_aop_db.png)  

### 2.1 `DBOperation`接口类
```java
public interface DBOperation {
    void insert();
    void delete();
    void update();
    //数据备份
    void save();
}
```

### 2.2 `MainActivity`实现
```java
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

        //代理
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
```