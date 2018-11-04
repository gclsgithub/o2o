package com.hytc.o2o.service;

public interface CacheService {

    /**
     * 根据传入的前缀删除cache中的数据
     * @param key
     */
    void removeFromCache(String keyPrefix);
}
