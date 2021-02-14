---
title : '线程状态'
date : '2021-02-14'
draft : false
tags : ["多线程"]
categories : ["java","index"]
author : 'zch'
description : '测试博客'
lastmod : '2021-02-14'
---

 
 
### **线程状态**
 
![线程核心](线程状态.svg)
 
```
public enum State {
    /**
    尚未启动的线程的线程状态 ,这个时候还没有在堆栈中分配内存等数据
     */
    NEW,

    /**
    线程可能等待也可能阻塞
     */
    RUNNABLE,

    /**
    阻塞 等待锁的状态
     */
    BLOCKED,

    /**
     线程等待状态
     */
    WAITING,

    /**
    和WAITING类似但是有时间的等待
     */
    TIMED_WAITING,

    /**
     线程终止
     */
    TERMINATED;
}
```

### [回到Java多线程首页](index.md)
