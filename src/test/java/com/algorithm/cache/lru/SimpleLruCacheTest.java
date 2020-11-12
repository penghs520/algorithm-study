package com.algorithm.cache.lru;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLruCacheTest {


    @Test
    public void test1(){
        SimpleLruCache<String, Object> cache = new SimpleLruCache<>(2);
        cache.put("name","小明");
        cache.put("sex","男");

        cache.put("age",12);
        Assertions.assertNull(cache.get("name"));
        Assertions.assertNotNull(cache.get("sex"));
        Assertions.assertNotNull(cache.get("age"));
    }

}