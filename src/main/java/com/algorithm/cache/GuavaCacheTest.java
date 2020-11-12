package com.algorithm.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Guava Cache与ConcurrentMap的区别：具有过期策略、LoadingCache虽然不会过期，但是可以自动加载缓存
 * Guava Cache与Redis的区别：Guava Cache是在内存中缓存数据，相比较于数据库或redis存储，访问内存中的数据会更加高效
 * 下面的这几种情况可以考虑使用Guava Cache:
 * 1. 愿意消耗一些内存空间来提升速度
 * 2. 预料到某些键会被多次查询
 * 3. 缓存中存放的数据总量不会超出内存容量
 */
public class GuavaCacheTest {

    static Cache<Object, Object> cache = null;

    @BeforeAll
    public static void init() {
        //返回一个手工的LocalCache.LocalManualCache
        cache = CacheBuilder.newBuilder().build();
        cache.put("name", "小明");
        cache.put("age", 18);
    }

    @Test
    public void testGetIfPresent() {
        Object name = cache.getIfPresent("name");
        Assertions.assertEquals("小明", name);
        System.out.println(name);
    }

    @Test
    public void testGetAllPresent() {
        List keys = new ArrayList();
        keys.add("name");
        keys.add("age");
        //表示不变的map
        ImmutableMap<Object, Object> all =
                cache.getAllPresent(keys);
        Assertions.assertEquals(2, all.size());
        Assertions.assertNotNull(all.get("name"));
    }

    @Test
    public void testGetAndCallable() throws Exception {
        Callable<String> callable = () -> {
            System.out.println("成功拿到了");
            return "dsds";
        };
        Object name = cache.get("name",callable );
        Assertions.assertEquals("小明",name);
        //如果。。。，则执行回调
        callable.call();
    }

    @Test
    public void testMaxSize(){
        Cache<Object, Object> cache = CacheBuilder.newBuilder().initialCapacity(2).maximumSize(3).build();
        cache.put("name","sss");
        cache.put("1","sss");
        cache.put("2","sss");
        cache.put("3","sss");
        Assertions.assertNotNull(cache.getIfPresent("1"));

    }

}
