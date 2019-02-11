package com.hytc.o2o.service.impl;

import com.hytc.o2o.DTO.AwardExcution;
import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.dao.AwardDao;
import com.hytc.o2o.entity.Award;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.enums.AwardStateEnum;
import com.hytc.o2o.enums.ShopStateEnum;
import com.hytc.o2o.exceptions.AwardException;
import com.hytc.o2o.service.AwardService;
import com.hytc.o2o.util.ImageUtil;
import com.hytc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {


    @Autowired
    private AwardDao awardDao;

    @Override
    public List<Award> finaAll() {
        return awardDao.findAll();
    }

    @Override
    public Award findAwardByAwardId(String awardId) {
        if (StringUtils.isEmpty(awardId)){
            throw  new RuntimeException("Id 不存在，是新建award");
        }
        return  awardDao.findAwardById(new Long(awardId));
    }

    @Override
    public Boolean addAward(Award award, ImageHolder holder) {
        if (award == null) {
            throw new AwardException(AwardStateEnum.NULL_SHOPID.getMsgl());
        }
        if (!ObjectUtils.isEmpty(holder)) {
            addFile2Proctectand2Award(award, holder.getImage(), holder.getImageName());
        }
        try {
            if (StringUtils.isEmpty(award.getAwardId())){
                awardDao.sava(award);
            } else {
                awardDao.updateAward(award);
            }
            return Boolean.TRUE;
        } catch (Exception ex){
            return Boolean.FALSE;
        }



    }

    /**
     * 将上传的文件存储到项目之中，同时把相对对路径存储的Award对象之中，在更新到数据库
     *
     */
    private void addFile2Proctectand2Award(Award award, InputStream imgInputStream, String fileName) {
        //获取存储的相对路径
        String reativePath = PathUtil.getShopImagePath(award.getAwardId());

        String showImageAddr = ImageUtil.generateThumbnail(imgInputStream, reativePath, fileName);

        award.setAwardImg(showImageAddr);
    }
}
