package com.hytc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * 获取地址
 * @author hytc
 */
public class ImageUtil {

    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();

    /**
     * 将用户上传的图片进行加水印处理,并且返回相对值路径
     * @param imageFile   CommonsMultipartFile是Spring默认用来传输文件的组建
     * @param targetAddr String 目标0路径
     * @return relativeAddr 相对路径  /Users/gcl/Documents/MyPic.jpg
     */
    public static String generateThumbnail(InputStream imageFile, String targetAddr,String fileName){

        String  relativeAddr = makeDirPath(targetAddr);
        fileName = UUID.randomUUID()+fileName;
        relativeAddr += (fileName);
        File dest = new File(PathUtil.getImgBasePth()+relativeAddr);
        try{
            Thumbnails.of(imageFile)
                    .size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/water.jpg")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        }catch (IOException iex){
            iex.getStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 改变了size和压缩率
     * 将用户上传的图片进行加水印处理,并且返回相对值路径
     * @param imageFile   CommonsMultipartFile是Spring默认用来传输文件的组建
     * @param targetAddr String 目标0路径
     * @return relativeAddr 相对路径  /Users/gcl/Documents/MyPic.jpg
     */
    public static String generateNormalThumbnail(InputStream imageFile, String targetAddr,String fileName){

        String  relativeAddr = makeDirPath(targetAddr);
        fileName = UUID.randomUUID()+fileName;
        relativeAddr += (fileName);
        File dest = new File(PathUtil.getImgBasePth()+relativeAddr);
        try{
            Thumbnails.of(imageFile)
                    .size(337,640).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/water.jpg")),0.25f)
                    .outputQuality(0.9f).toFile(dest);
        }catch (IOException iex){
            iex.getStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 判断文件是否存在不存在的话就创建文件
     * @param targetAddr
     */
    private static String makeDirPath(String targetAddr) {
        String realFileName = PathUtil.getImgBasePth()+targetAddr;


        int year = LocalDateTime.now().getYear();

        int month = LocalDateTime.now().getMonthValue();

        realFileName +=year+month;

        File file =new File(realFileName);

        //迭代创建文件夹
        if (!file.exists()){
            file.mkdirs();
        }

        return targetAddr;
    }

    /**
     * 获取文件扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtrend(String fileName) {

        //从最后一个.号开始截取
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     *  获取唯一的文件名
     * @return
     */

    private static String getUnicomRandomFileName() {

        //获取UUID（唯一标识符）
        UUID uuid = UUID.randomUUID();
        Random random = new Random(5);
        Integer num  =random.nextInt(5);

        //转换数据类型
        String fileName = (""+uuid+num).trim();
        return fileName;

    }

   public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("/Users/gcl/Documents/MyPic.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/water.jpg")),0.25f)
                .outputQuality(0.8f).toFile("/Users/gcl/Documents/MyPic2.jpg");
    }

    /**
     * 删除图片信息
     * @param imgAddr
     */
    public static void delImgFileOrPath(String imgAddr) {
        File file = new File(imgAddr);
        deleteDir(file);
    }

    /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
