package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.Award;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AwardDaoTest extends BaseTest {

    @Autowired
    private AwardDao awardDao;

    @Test
    public void fiandAll(){
        List<Award> awards = awardDao.findAll();
        awards.stream().forEach(System.out::println);
    }

    @Test
    public void findAwardsBySomeAwardCondition() {
        Award award = new Award();
        award.setAwardDesc("DESC");
        award.setAwardImg("IMAGE");
        award.setAwardName("NAME");
        award.setCreateTime(new Date());
        award.setLastEditTime(new Date());
        award.setNeedPoint(100);
        award.setPriority(10);
        award.setShopId(2L);
        award.setEnableStatus("0");
        List<Award> awards = awardDao.findAwardsBySomeAwardCondition(award,0,1);
        System.out.println(awards.size());
    }

    @Test
    public void findAwardsCount() {
        Award award = new Award();
        award.setAwardDesc("DESC");
        award.setAwardImg("IMAGE");
        award.setAwardName("NAME");
        award.setCreateTime(new Date());
        award.setLastEditTime(new Date());
        award.setNeedPoint(100);
        award.setPriority(10);
        award.setShopId(2L);
        award.setEnableStatus("0");
        Integer count = awardDao.findAwardsCount(award);
        Assert.assertEquals(count,new Integer(1));
    }

    @Test
    public void findAwardById() {
        Award award = new Award();
        award.setAwardDesc("DESC");
        award.setAwardImg("IMAGE");
        award.setAwardName("NAME");
        award.setCreateTime(new Date());
        award.setLastEditTime(new Date());
        award.setNeedPoint(100);
        award.setPriority(10);
        award.setShopId(2L);
        award.setEnableStatus("0");
        Award award1 = awardDao.findAwardById(2);
        System.out.println(award1.getAwardName());
    }

    @Test
    public void sava() {

        Award award = new Award();
        award.setAwardDesc("DESC");
        award.setAwardImg("IMAGE");
        award.setAwardName("NAME");
        award.setCreateTime(new Date());
        award.setLastEditTime(new Date());
        award.setNeedPoint(100);
        award.setPriority(10);
        award.setShopId(2L);
        award.setEnableStatus("0");
        awardDao.sava(award);
    }

    @Test
    public void updateAward() {

        Award award = new Award();
        award.setAwardDesc("DESC");
        award.setAwardImg("IMAGE");
        award.setAwardName("NAME");
        award.setCreateTime(new Date());
        award.setLastEditTime(new Date());
        award.setNeedPoint(100);
        award.setPriority(10);
        award.setShopId(2L);
        award.setEnableStatus("0");
        Integer count = awardDao.updateAward(award);
        Assert.assertEquals(count,new Integer(1));
    }

    @Test
    public void delAward() {
        awardDao.delAward(2L,2L);
    }
}