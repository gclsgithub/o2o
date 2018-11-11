package com.hytc.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.o2o.cache.JedisUtil;
import com.hytc.o2o.dao.AreaDao;
import com.hytc.o2o.entity.Area;
import com.hytc.o2o.exceptions.AreaOptionException;
import com.hytc.o2o.service.AreaService;
import com.hytc.o2o.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private CacheService cacheService;

    private static final String AREA_LIST_KEY = "arealist";

    @Override
    @Transactional
    public List<Area> findAlls() {
        String key  = AREA_LIST_KEY;

        List<Area> areaList = null;

        ObjectMapper mapper = new ObjectMapper();

        if (!jedisKeys.exists(key)){
            areaList = areaDao.findAlls();
            String  areaString = null;
            try {
                 areaString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.warn(e.toString());
                throw new AreaOptionException("JSON转换错误");
            }
            jedisStrings.set(key,areaString);
        }else{
            String areaString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructType(ArrayList.class,Area.class);
            try {
                areaList = mapper.readValue(areaString,javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.warn(e.toString());
                throw new AreaOptionException("JSON转换错误");
            }
        }

        return areaList;
    }
}
