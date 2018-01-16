package com.boc.common.biz;/**
 * Created by ranqibing on 2017/1/11.
 */

/**
 * 获取id工具
 * IdWorkerUtil
 *
 * @author ranqibing
 * @date 2017/1/11
 */
public class IdWorkerUtil {

    protected final static IdWorker idWorker;
    static {
        idWorker = new IdWorker(1,1);
    }

    public static Long getId(){
        return idWorker.nextId();
    }
}
