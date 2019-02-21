package com.hytc.o2o.service.impl;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.DTO.ProductAndCategoeryDto;
import com.hytc.o2o.DTO.ProductExcution;
import com.hytc.o2o.dao.ProductDao;
import com.hytc.o2o.dao.ProductImgDao;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.ProductImg;
import com.hytc.o2o.entity.ProductSellDaily;
import com.hytc.o2o.enums.ProductStateEnum;
import com.hytc.o2o.exceptions.ProductRuntimeException;
import com.hytc.o2o.service.ProductService;
import com.hytc.o2o.util.ImageUtil;
import com.hytc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 详情图片存储的Dao方法
     */
    @Autowired
    private ProductImgDao productImgDao;

    /**
     * 逻辑商品信息
     * 逻辑删除
     *
     * @param productId
     */
    @Override
    public void delProduct(String productId, String status) {
        if ("1".equals(status)) {
            status = "0";
        } else if ("0".equals(status)) {
            status = "1";
        }
        productDao.delProductByProductId(productId, status);
    }

    @Override
    public List<Product> getProductByShopId(Long shopId, int index, int pageSize) {
        return productDao.getProductListByShopId(shopId, index, pageSize);
    }

    @Override
    public List<Product> getProductList(Product product, int index, int pageSize) {
        return productDao.getProductList(product, index, pageSize);
    }

    @Override
    public int createProductSellInfo(ProductSellDaily productSellDaily) {

        int count = productDao.saveProductSellDailyInfo(productSellDaily);

        if (count == 0) {
            return 0;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
        String insertTime = simpleDateFormat.format(productSellDaily.getCreateTime());
        if (StringUtils.isEmpty(insertTime)) {
            return 0;
        }
        if (Integer.valueOf(insertTime.substring(18, 19)) >= 5) {
            String lastTime = String.valueOf(Integer.valueOf(insertTime.substring(16, 17)) + 1);
            if (insertTime.length() == 21) {
                insertTime = insertTime.substring(0, 4) + insertTime.substring(5, 16) + lastTime;
            }
        }
        if (insertTime.length() == 21) {
            insertTime = insertTime.substring(0, 4) + insertTime.substring(5, 17);
        }

        productSellDaily.getCreateTime().getTime();
        Integer sellId = productDao.getProductSellId(insertTime);

        return sellId == null ? 0 : sellId;
    }

    @Override
    public List<ProductCategory> getProductCategoeryList() {

        return productDao.getProductCategoryList(null);
    }

    /**
     * 查询初期化信息
     *
     * @param productId
     * @return
     */
    @Override
    public ProductAndCategoeryDto getProduct(Long productId) {

        ProductAndCategoeryDto output = new ProductAndCategoeryDto();
        if (productId != null) {
            Product product = productDao.getProductInfo(productId);
            output.setProduct(product);
        }
        List<ProductCategory> productCategoryList = productDao.getProductCategoryList(null);

        output.setProductCategoryList(productCategoryList);

        return output;
    }

    /**
     * 添加或者修正一条商品信息
     *
     * @param product        商品信息
     * @param thumnail       缩略图
     * @param productImgList 详情图
     * @return
     * @throws ProductRuntimeException
     */
    @Override
    @Transactional
    public ProductExcution addProduct(Product product, ImageHolder thumnail, List<ImageHolder> productImgList) throws ProductRuntimeException {
        //1.处理缩略图，获取缩略图相对路径并赋值给product
        if (!ObjectUtils.isEmpty(product) && !ObjectUtils.isEmpty(product.getShop())
                && !StringUtils.isEmpty(product.getShop().getShopId())) {
            product.setCreateTime(LocalDateTime.now());
            product.setLastEditTime(LocalDateTime.now());

            //设置商品属性为上架商品
            product.setEnableStatus(0);

            //判断是否存在缩略图，如果存在的话处理文件流
            if (thumnail != null) {
                addThumbnail(product, thumnail);
            }

            //如果传入的productId为空，则是需要添加的信息，如果不为空则是应该修改的信息
            if (!StringUtils.isEmpty(product.getProductId())) {
                try {
                    int effectedNum = productDao.updateProduct(product);
                    if (effectedNum < 0) {
                        throw new ProductRuntimeException("商品更新失败");
                    }

                    String dest = PathUtil.getShopImagePath(product.getShop().getShopId());

                    boolean flag = Boolean.FALSE;
                    //遍历图片，并添加到productImgs
                    for (ImageHolder imageHolder : productImgList) {

                        String fileName = imageHolder.getImageName();


                        //查询是否有
                        List<ProductImg> productImgSearchList = productImgDao.searchProductImg(product.getProductId());

                        if (CollectionUtils.isEmpty(productImgSearchList)) {
                            addProductImgList(product, productImgList);
                            break;
                        } else {

                            if (productImgSearchList.size() == productImgList.size()) {

                                //非压缩的方式生成图片
                                String relativeAddr = ImageUtil.generateNormalThumbnail(imageHolder.getImage(), dest, fileName);


                                for (ProductImg img : productImgSearchList) {

                                    String file = null;

                                    file = PathUtil.getImgBasePth() + img.getImgAddr();

                                    //删除的路径
                                    ImageUtil.delImgFileOrPath(file);


                                    img.setImgAddr(relativeAddr);
                                    img.setProductId(product.getProductId());
                                    img.setCreateTime(LocalDateTime.now());
                                    productImgDao.updateProductCategoery(img);
                                }
                            } else {

                                for (ProductImg img : productImgSearchList) {
                                    String file = null;
                                    file = PathUtil.getImgBasePth() + img.getImgAddr();
                                    //删除的路径
                                    ImageUtil.delImgFileOrPath(file);
                                }

                                //删除先前的数据
                                productImgDao.deleteProductImgByProductId(product.getProductId());
                                List<ImageHolder> imageHolders = new ArrayList<>();
                                imageHolders.add(imageHolder);
                                addProductImgList(product, imageHolders);
                                break;
                            }


                        }

                    }
                } catch (Exception ex) {
                    throw new ProductRuntimeException("商品更新失败");
                }

            } else {
                try {
                    int effectedNum = productDao.insertPrdouctIntoDb(product);
                    if (effectedNum < 0) {
                        throw new ProductRuntimeException("创建商品失败");
                    }
                } catch (Exception e) {
                    throw new ProductRuntimeException("创建商品失败" + e.toString());
                }

                //2.向tb_product写入商品信息（商品信息+缩略图地址）
                if (!ObjectUtils.isEmpty(productImgList)) {
                    addProductImgList(product, productImgList);
                }

                //3.结合product_id 批量插入商品详情图片
                return new ProductExcution(ProductStateEnum.SUCCESS, product);
            }
        } else {
            //失败的时候
            return new ProductExcution(ProductStateEnum.EMPTY);
        }

        //失败的时候
        return new ProductExcution(ProductStateEnum.SUCCESS, product);
    }

    /**
     * 处理详情图文件流
     *
     * @param product
     * @param productImgList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgs = new ArrayList<>();

        //遍历图片，并添加到productImgs
        for (ImageHolder imageHolder : productImgList) {

            String fileName = imageHolder.getImageName();
            //非压缩的方式生成图片
            String imgAddr = ImageUtil.generateNormalThumbnail(imageHolder.getImage(), dest, fileName);
            ProductImg img = new ProductImg();
            img.setImgAddr(imgAddr);
            img.setProductId(product.getProductId());
            img.setCreateTime(LocalDateTime.now());
            productImgs.add(img);
        }
        if (!CollectionUtils.isEmpty(productImgs)) {
            try {

                int effectedNum = productImgDao.batchInsertProductImg(productImgs);
                if (effectedNum < 0) {
                    throw new ProductRuntimeException("添加失败");
                }
            } catch (Exception ex) {
                throw new ProductRuntimeException("创建图片失败" + ex.getMessage());
            }
        }
    }

    /**
     * 处理缩略图文件流
     *
     * @param product
     * @param thumnail
     */
    private void addThumbnail(Product product, ImageHolder thumnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());

        String fileName = thumnail.getImageName();
        String thumnnailAddr = ImageUtil.generateThumbnail(thumnail.getImage(), dest, fileName);
        product.setImgAddr(thumnnailAddr);
    }
}
