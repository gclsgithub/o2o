package com.hytc.o2o.util;

public class PathUtil {
    private static String seperator =System.getProperty("file.separator");

    public static String getImgBasePth(){
        String os = System.getProperty("os.name");
        String basePath="";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/project/image/";
        }else{
            basePath = "/home/gcl/image/";
        }
        basePath = basePath.replace("/",seperator);

        return basePath;
    }

    /**
     * 项目中的存储路径
     * @return
     */
    public static  String getShopImagePath(Long shopId){
        //给每一个商家设置一个文件夹保存图片
        String ImagePath="/upload/item/shop/"+shopId+"/";
        return ImagePath.replace("/",seperator);
    }
}
