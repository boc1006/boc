package com.boc.concurrent06;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁分为读锁和写锁，多个读锁之间是不需要互斥的(读操作不会改变数据，如果上了锁，反而会影响效率)，写锁和写锁之间需要互斥，
 * 也就是说，如果只是读数据，就可以多个线程同时读，但是如果你要写数据，就必须互斥，使得同一时刻只有一个线程在操作。
 * <p>@Title: T3_1.java 
 * <p>@Package com.boc.concurrent06 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2018年1月15日 上午10:00:02 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T3_1 {
	/* 共享数据，只能一个线程写数据，可以多个线程读数据 */  
    private Object data = null;  
    /* 创建一个读写锁 */  
    ReadWriteLock rwlock = new ReentrantReadWriteLock();  
  
    /** 
     * 读数据，可以多个线程同时读， 所以上读锁即可 
     */  
    public void get() {  
        /* 上读锁 */  
        rwlock.readLock().lock();  
  
        try {  
            System.out.println(Thread.currentThread().getName() + " 准备读数据!");  
            /* 休眠 */  
            Thread.sleep((long) (Math.random() * 1000));  
            System.out.println(Thread.currentThread().getName() + "读出的数据为 :" + data);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } finally {  
            rwlock.readLock().unlock();  
        }  
  
    }  
  
    /** 
     * 写数据，多个线程不能同时 写 所以必须上写锁 
     *  
     * @param data 
     */  
    public void put(Object data) {  
  
        /* 上写锁 */  
        rwlock.writeLock().lock();  
  
        try {  
            System.out.println(Thread.currentThread().getName() + " 准备写数据!");  
            /* 休眠 */  
            Thread.sleep((long) (Math.random() * 1000));  
            this.data = data;  
            System.out.println(Thread.currentThread().getName() + " 写入的数据: " + data);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            rwlock.writeLock().unlock();  
        }  
    }  
    
    public static void main(String[] args) {  
    	  
        /* 创建ReadWrite对象 */  
        final T3_1 readWrite = new T3_1();  
  
        /* 创建并启动3个读线程 */  
        for (int i = 0; i < 3; i++) {  
            new Thread(new Runnable() {  
  
                @Override  
                public void run() {  
                    readWrite.get();  
  
                }  
            }).start();  
              
            /*创建3个写线程*/  
            new Thread(new Runnable() {  
                  
                @Override  
                public void run() {  
                    /*随机写入一个数*/  
                    readWrite.put(new Random().nextInt(8));  
                      
                }  
            }).start();  
        }  
          
    }  
}
