package com.boc.common.utils;

import java.util.Collection;

/**
 * <p>@Title ListUtils</p>
 * <p>@Description </p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author SONG YU TAO</p>
 * <p>@date 2016年11月28日</p>
 * <p>songyutao@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class ListUtils {

    public interface Expression<T> {
        boolean some(T obj);
    }

    public interface Consumer<T, R> {
        R consume(T obj);
    }

    /**
     * 只要匹配一个值就返回true
     */
    public static <T> boolean some(Collection<T> values, T value) {
        if (values == null || value == null) {
            return false;
        }

        for (T item : values) {
            if (item.equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 只要匹配一个值就返回true
     */
    public static <T> boolean some(Collection<T> values, Expression<T> expression) {
        if (values == null || expression == null) {
            return false;
        }

        for (T item : values) {
            boolean exist = expression.some(item);

            if (exist) {
                return true;
            }
        }

        return false;
    }

    public static <T, R> String forEachToken(Collection<T> list, Consumer<T, R> consumer) {
        return forEachToken(list, consumer, ",");
    }

    public static <T, R> String forEachToken(Collection<T> list, Consumer<T, R> consumer, String token) {
        if (list == null || list.size() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        if (token == null) {
            token = ",";
        }

        for (T item : list) {
            R value = consumer.consume(item);

            sb.append(value).append(token);
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
