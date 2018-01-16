package com.boc.concurrent02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了
 * CyclicBarrier类位于java.util.concurrent包下，CyclicBarrier提供2个构造器：
 * CyclicBarrier(int parties, Runnable barrierAction)
 * CyclicBarrier(int parties)
 * 参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
 * 然后CyclicBarrier中最重要的方法就是await方法，它有2个重载版本
 * public int await() throws InterruptedException, BrokenBarrierException
 * public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException
 * <p>@Title: T14.java 
 * <p>@Package com.boc.concurrent02 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午4:24:46 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T14 {
	public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++)
            new Writer(barrier).start();
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
 
        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
