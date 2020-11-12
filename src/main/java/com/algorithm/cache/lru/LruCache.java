package com.algorithm.cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;

//通过LinkedHashMap实现
public class LruCache extends LinkedHashMap {

    private int maxSize;

    public LruCache(int maxSize) {
        super(maxSize + 1, 1.0F, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return this.size() > this.maxSize;
    }
}
