package com.boc.concurrent02;

import java.util.concurrent.Semaphore;

/**
 * Semaphore
 * Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可
 * public Semaphore(int permits) //参数permits表示许可数目，即同时可以允许多少线程进行访问
 * public Semaphore(int permits, boolean fair) //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
 * 下面说一下Semaphore类中比较重要的几个方法，首先是acquire()、release()方法：
 * public void acquire() throws InterruptedException {  }     //获取一个许可
 * public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
 * public void release() { }          //释放一个许可
 * public void release(int permits) { }    //释放permits个许可
 * acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
 * release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
 * 这4个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法：
 * public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
 * public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
 * public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
 * public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个
 * <p>@Title: T16.java 
 * <p>@Package com.boc.concurrent02 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午4:32:02 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T16 {
	public static void main(String[] args) {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<N;i++)
            new Worker(i,semaphore).start();
    }
     
    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
         
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();           
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
