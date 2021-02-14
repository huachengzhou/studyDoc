---
title : '线程中断问题'
date : '2021-02-14'
draft : false
tags : ["多线程"]
categories : ["java","index"]
author : 'zch'
description : '测试博客'
lastmod : '2021-02-14'
---



#### **结束线程的方式**

+ ~~1:Thread.stop()~~ 不推荐使用了
```
thread.stop()
```

+ 2:thread.setDaemon() 设置为守护线程,JVM退出自动终止
```
 Thread thread = new Thread(() -> {

        },"T-1") ;
        thread.setDaemon(true);
        thread.start();
```

+ 3:共享变量 通过一个推出表示开关
```
public class MyTask implements Runnable {

    private boolean shouldExit = false;

    public void setShouldExit(boolean newExit) {
        this.shouldExit = newExit;
    }

    @Override
    public void run() {
        while (!shouldExit){
            //执行线程操作
        }
    }
}
```

+ 4:利用中断机制thread.interrupt()

```
A:void interrupt() 相某个线程发出中断信号,将会设置该线程的中断状态位，即设置为true，中断的结果线程是死亡、还是等待新的任务或是继续运行至下一步，就取决于这个程序本身。线程会不时地检测这个中断标示位，以判断线程是否应该被中断（中断标示值是否为true）。它并不像stop方法那样会中断一个正在运行的线程。
B:boolean isInterrupted() 查询某个线程是否有获得中断信号
C:static boolean interrupted() 查询当前线程是否获得中断信号,查询后会重置中断的信号的状态 也就是说如果你连续两次调用这个方法,那么第一次是true,第二次是false
```

![interrupt()方法被调用](interrupt()方法被调用.svg)

+ 5:较优雅的退出方式
```
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            //线程要处理的任务
            //如果耗时较长的任务,可在在任务中间要退出的部分增加isInterrupted()的判断
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //退出循环
                break;
            }
        }
        System.out.println("thread exits.");
        //关闭打开的资源,退出线程
    }
}, "T-1") ;
thread.start();
thread.interrupt();
```
































































### [回到Java多线程首页](index.md)
