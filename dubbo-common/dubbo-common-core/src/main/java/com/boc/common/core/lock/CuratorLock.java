package com.boc.common.core.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@Title: Zookeeper Curator分布式锁 
 * <p>@Package com.boc.common.core.lock 
 * <p>@Description: 在分布式环境下，存在资源竞争的情况下使用.
 * 一般情况下，在写操作的业务场境中，如果需要使用synchronized的操作，可使用CuratorLock替代.
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月9日 上午11:11:12 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CuratorLock {
	int id();
    int keepMills() default 1000;
    /** 
     * 当获取锁失败，是继续等待还是放弃<br/> 
     * 默认为继续等待 
     */  
    boolean toWait() default true;  
}
