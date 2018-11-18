package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.Award;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.UserAndAwardMap;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserAwardMapDaoTest extends BaseTest {

    @Autowired
    private UserAwardMapDao userAwardMapDao;

    @Test
    public void findUserAwardMapList() {
        UserAndAwardMap userAndAwardMap = new UserAndAwardMap();
        Award award = new Award();
        award.setAwardId(3L);
        award.setShopId(2L);
        PersonInfo operator = new PersonInfo();
        operator.setUserId(2L);
        PersonInfo userInfo = new PersonInfo();
        userInfo.setUserId(3L);
        userAndAwardMap.setAward(award);
        userAndAwardMap.setCreateTime(new Date());
        userAndAwardMap.setOperator(operator);
        userAndAwardMap.setPoint(100);
        userAndAwardMap.setUser(userInfo);
        userAndAwardMap.setUsedStatus(0);
        List<UserAndAwardMap> lists = userAwardMapDao.findUserAwardMapList(userAndAwardMap,0,6);
        Assert.assertEquals(lists.size(),1);
    }

    @Test
    public void findUserAwardCount() {
        UserAndAwardMap userAndAwardMap = new UserAndAwardMap();
        Award award = new Award();
        award.setAwardId(3L);
        award.setShopId(2L);
        PersonInfo operator = new PersonInfo();
        operator.setUserId(2L);
        PersonInfo userInfo = new PersonInfo();
        userInfo.setUserId(3L);
        userAndAwardMap.setAward(award);
        userAndAwardMap.setCreateTime(new Date());
        userAndAwardMap.setOperator(operator);
        userAndAwardMap.setPoint(100);
        userAndAwardMap.setUser(userInfo);
        userAndAwardMap.setUsedStatus(0);
        Integer count = userAwardMapDao.findUserAwardCount(userAndAwardMap);
        Assert.assertEquals(count,new Integer(1));
    }

    @Test
    public void findUserAndAwardById() {
        UserAndAwardMap userAndAwardMap = new UserAndAwardMap();
        Award award = new Award();
        award.setAwardId(3L);
        award.setShopId(2L);
        PersonInfo operator = new PersonInfo();
        operator.setUserId(2L);
        PersonInfo userInfo = new PersonInfo();
        userInfo.setUserId(3L);
        userAndAwardMap.setAward(award);
        userAndAwardMap.setCreateTime(new Date());
        userAndAwardMap.setOperator(operator);
        userAndAwardMap.setPoint(100);
        userAndAwardMap.setUser(userInfo);
        userAndAwardMap.setUsedStatus(0);
        UserAndAwardMap out = userAwardMapDao.findUserAndAwardById("1");
        System.out.println(out);
    }

    @Test
    public void sava() {
        UserAndAwardMap userAndAwardMap = new UserAndAwardMap();
        Award award = new Award();
        award.setAwardId(3L);
        award.setShopId(2L);
        PersonInfo operator = new PersonInfo();
        operator.setUserId(2L);
        PersonInfo userInfo = new PersonInfo();
        userInfo.setUserId(3L);
        userAndAwardMap.setAward(award);
        userAndAwardMap.setCreateTime(new Date());
        userAndAwardMap.setOperator(operator);
        userAndAwardMap.setPoint(100);
        userAndAwardMap.setUser(userInfo);
        userAndAwardMap.setUsedStatus(0);
        userAwardMapDao.sava(userAndAwardMap);
    }

    @Test
    public void updateUserAndAward() {
        UserAndAwardMap userAndAwardMap = new UserAndAwardMap();
        Award award = new Award();
        award.setAwardId(3L);
        award.setShopId(2L);
        PersonInfo operator = new PersonInfo();
        operator.setUserId(2L);
        PersonInfo userInfo = new PersonInfo();
        userInfo.setUserId(3L);
        userAndAwardMap.setAward(award);
        userAndAwardMap.setCreateTime(new Date());
        userAndAwardMap.setOperator(operator);
        userAndAwardMap.setPoint(100);
        userAndAwardMap.setUser(userInfo);
        userAndAwardMap.setUsedStatus(0);
        Integer count = userAwardMapDao.updateUserAndAward(userAndAwardMap);
        Assert.assertEquals(count,new Integer(1));
    }

    @Test
    public void delUserAndAward() {
        Integer count = userAwardMapDao.delUserAndAward("1");
        Assert.assertEquals(count,new Integer(1));
    }
}