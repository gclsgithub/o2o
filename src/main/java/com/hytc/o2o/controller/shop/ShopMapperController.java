package com.hytc.o2o.controller.shop;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.Award;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.exceptions.ShopRuntimeException;
import com.hytc.o2o.service.AreaService;
import com.hytc.o2o.service.AwardService;
import com.hytc.o2o.service.ShopCategoeryService;
import com.hytc.o2o.util.CodeUtil;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

    @Autowired
    private AwardService awardService;

    //@Value("${wechat.url}")
    public String url;

    //@Value("${wechat.authUrl}")
    public String authUrl;

    //@Value("${wechat.urlMiddle}")
    public String urlMiddle;

    // @Value("${wechat.urlSuffix}")
    public String urlSuffix;

    @GetMapping("/gennerateqrcode4shopauth")
    public void gennerateqrcode4shopauth(HttpServletRequest request, HttpServletResponse response) {
        String shopId = (String) request.getSession().getAttribute("shopId");
        if (ObjectUtils.isEmpty(shopId)) {
            long timeStap = System.currentTimeMillis();
            String content = "{aaashopIdaaa:" + shopId + ",aaacreateTimeaaa:" + timeStap + "}";
            try {

                String longUrl = url + authUrl + urlMiddle + URLEncoder.encode(content, "UTF-8") + urlSuffix;

                BitMatrix qrCodeImg = CodeUtil.generateQrCodeStream(longUrl, response);

                //将二维码以图片的形式输出到前段
                MatrixToImageWriter.writeToStream(qrCodeImg, ".png", response.getOutputStream());


            } catch (Exception ex) {

            }
        }
    }

    @GetMapping("/shoplist")
    public String jumpToShopList() {

        return "/shop/shoplist";
    }

    /**
     * 跳转
     *
     * @return
     */
    @GetMapping("/mappershop")
    public String mapperShop() {
        return "/shop/shopoperation";
    }

    /**
     * 跳转 managershopfunction  login
     *
     * @return
     */
    @GetMapping("/mappershopfunction")
    public String mapperShopFuncion(HttpServletRequest request) {
        String shopId = HttpRequestUtil.getString(request, "shopId");
        //向Session中存放ShopId
        request.getSession().setAttribute("shopId", shopId);
        return "/shop/shopmanage";
    }

    @GetMapping("/shopauthmanage")
    public String jumpShopAuthManage() {
        return "/shop/shopauthmanage";
    }

    @GetMapping("/awardmanage")
    public String jumpProductmanage() {
        return "/shop/awardmanage";
    }

    @GetMapping("/shopauthedit")
    public String jumpShopAuthEdit() {
        return "/shop/shopauthedit";
    }


    /**
     * 初始化
     *
     * @return
     */
    @GetMapping("/shopinit")
    @ResponseBody
    public Map<String, Object> initShopOperationHtml() {
        Map<String, Object> modelMap = new HashMap<>();

        List<ShopCategoery> shopCategoeryList = new ArrayList<>();
        List<Area> areaList = areaService.findAlls();

        //初始化检索一个父类的ShopCategoery类并将其父类Id设置为空
        ShopCategoery shopCategoery = new ShopCategoery();
        shopCategoery.setParentId(-1L);

        try {
            //初始化ShopCateGoery,初始化的时候只查询其父亲类别Id为null的类
            shopCategoeryList = shopCategoeryService.findAlls(shopCategoery);
            //初始化地区ID
            modelMap.put("shopCategoeryList", shopCategoeryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);

        } catch (ShopRuntimeException shopRuntimeException) {
            modelMap.put("shopCategoeryList", shopCategoeryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", false);
        }

        return modelMap;
    }

    @GetMapping("listawardsbyshop")
    @ResponseBody
    public Map<String, Object> initAwardList() {
        Map<String, Object> modelMap = new HashMap<>();

        List<Award> awardList = awardService.finaAll();

        if (CollectionUtils.isEmpty(awardList)){
            modelMap.put("success", false);
            modelMap.put("message","未查到信息");
        }else {
            modelMap.put("success", true);
            modelMap.put("awardList",awardList);
        }
        return modelMap;
    }

    @GetMapping("awardedit")
    public ModelAndView jumpToNewAward(@RequestParam(value = "shopId")String shopId){
        ModelAndView modelAndView = new ModelAndView("/shop/awardedit");

        modelAndView.addObject("shopId",shopId);

        return modelAndView;
    }

    @GetMapping("getawardbyid")
    @ResponseBody
    public  Map<String, Object> getAwardInfo(@RequestParam(value = "awardId",required = false)String awardId){
        Map<String, Object> modelMap = new HashMap<>();

        Award award = awardService.findAwardByAwardId(awardId);

        if (ObjectUtils.isEmpty(award)){
            modelMap.put("success", false);
            modelMap.put("message","未查到信息");
        } else {
            modelMap.put("success", true);
            modelMap.put("award",award);
        }
        return modelMap;
    }

    @PostMapping("modifyaward")
    @ResponseBody
    public Map<String, Object> modifyAward(HttpServletRequest request, String awardStr){
        Map<String, Object> modelMap = new HashMap<>();

        boolean statusChange = CodeUtil.cheackVerfityCode(request);

        //判断验证码是否正确
        if (!statusChange && !CodeUtil.cheackVerfityCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "验证码信息错误");
            return modelMap;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Award award = null;
        try {
             award = objectMapper.readValue(awardStr,Award.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommonsMultipartFile awardImage = null;

        //从Servlet上下文中获取文件流
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            awardImage = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
        }

        if (award != null && awardImage != null) {
            try {
                ImageHolder holder = new ImageHolder(awardImage.getOriginalFilename(),awardImage.getInputStream());
                Boolean isAdd = awardService.addAward(award,holder);

                if (isAdd){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return modelMap;
    }
}
