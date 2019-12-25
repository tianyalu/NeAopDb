# NeAopDb 面向切面架构设计之动态代理方式：操作数据库之前进行备份操作
## 一、前言
### 1.1 AOP
AOP：Aspect Oriented Programming，面向切面编程，通过预编译方式和运行期动态代理实现程序功能统一维护的一种技术。  
AOP是OOP（Object Oriented Programming，面向对象）的延续。
### 1.2 AOP两种实现 
![image](https://github.com/tianyalu/NeAopDb/raw/master/show/aop_implementations.png)  
### 1.3 怎样理解AOP
本质是在一系列纵向的业务流程中，把那些有相业务的子流程抽取成一个横向的面。  

![image](https://github.com/tianyalu/NeAopDb/raw/master/show/aop_understand.png)  

## 二、实操
通过动态代理实现切面的需求：每次操作数据库之前都保存数据。    

![image](https://github.com/tianyalu/NeAopDb/raw/master/show/dynamic_aop_db.png)  

