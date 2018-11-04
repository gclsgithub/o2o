package com.hytc.o2o.service.impl;

import com.hytc.o2o.cache.JedisUtil;
import com.hytc.o2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        StringBuilder sb = new StringBuilder();
        String searchKey = sb.append(keyPrefix).append("*").toString();
        Set<String> keySet = jedisKeys.keys(searchKey);
        for (String key:keySet){
            jedisKeys.del(key);
        }
    }
}
