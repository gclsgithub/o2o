package com.hytc.o2o.controller.shop;


import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.exceptions.ShopRuntimeException;
import com.hytc.o2o.service.AreaService;
import com.hytc.o2o.service.ShopCategoeryService;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = {"/shop"})
public class ShopMapperController {



    @Autowired
    private ShopCategoeryService shopCategoeryService;

    @Autowired
    private AreaService areaService;

    @GetMapping("/shoplist")
    public String jumpToShopList(){

        return "/shop/shoplist";
    }

    /**
     * 跳转
     * @return
     */
    @GetMapping("/mappershop")
    public String mapperShop(){
        return "/shop/shopoperation";
    }

    /**
     * 跳转 managershopfunction
     * @return
     */
    @GetMapping("/mappershopfunction")
    public String mapperShopFuncion(){
        //String shopId = HttpRequestUtil.getString(request,"shopId");
        //向Session中存放ShopId
        //request.getSession().setAttribute("shopId",shopId);
        return "/shop/shopmanage";
    }
    @GetMapping("/productmanage")
    public String jumpProductmanage(){
        return "/shop/productmanage";
    }
    /**
     * 初始化
     * @return
     */
    @GetMapping("/shopinit")
    @ResponseBody
    public Map<String,Object> initShopOperationHtml(){
        Map<String,Object> modelMap = new HashMap<>();

        List<ShopCategoery> shopCategoeryList = new ArrayList<>();
        List<Area> areaList = areaService.findAlls();

        //初始化检索一个父类的ShopCategoery类并将其父类Id设置为空
        ShopCategoery shopCategoery = new ShopCategoery();
        shopCategoery.setParentId(-1L);

        try {
            //初始化ShopCateGoery,初始化的时候只查询其父亲类别Id为null的类
             shopCategoeryList = shopCategoeryService.findAlls(shopCategoery);
            //初始化地区ID
            modelMap.put("shopCategoeryList",shopCategoeryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);

        }catch (ShopRuntimeException shopRuntimeException){
            modelMap.put("shopCategoeryList",shopCategoeryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",false);
        }

        return modelMap;
    }
}
