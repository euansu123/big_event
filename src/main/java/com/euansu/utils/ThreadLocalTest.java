package com.euansu.utils;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        // 提供一个ThreadLocal对象
        ThreadLocal tl = new ThreadLocal();

        // 开启两个现场
        new Thread(()->{
            tl.set("萧炎");
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
        },"蓝色").start();

        new Thread(()->{
            tl.set("药尘");
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
            System.out.println(Thread.currentThread().getName() + "：" + tl.get());
        },"红色").start();
    }
}
