package pers.jess.quartz;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFolder {

    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "/" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        if (readfile.getName().contains(".png")){
                            BufferedImage srcFile = ImageIO.read(readfile);
                           // int w = srcFile.getHeight();

                            zipWidthHeightImageFile(readfile,new File("/Users/mac/desktop/packimg/"+readfile.getName()),srcFile.getWidth(),srcFile.getHeight(),0.4f);
                            //Boolean isUpLoad =  ImgSourceRegion(readfile,"/Users/mac/desktop/packimg/index/"+readfile.getName(),false);
                        }


                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }



    public static Boolean ImgSourceRegion(File source, String output, boolean keepAspectRatio) {
        try {
            Thumbnails.of(source).scale(1f).outputQuality(0.01f).toFile(output);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 按设置的宽度高度压缩图片文件<br> 先保存原文件，再压缩、上传
     * @param oldFile  要进行压缩的文件全路径
     * @param newFile  新文件
     * @param width  宽度
     * @param height 高度
     * @param quality 质量
     * @return 返回压缩后的文件的全路径
     */
    public static String zipWidthHeightImageFile(File oldFile,File newFile, int width, int height,float quality) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);

            String srcImgPath = newFile.getAbsoluteFile().toString();
            System.out.println(srcImgPath);
            String subfix = "jpg";
            subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());

            BufferedImage buffImg = null;
            if(subfix.equals("png")){
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            }else{
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }

            Graphics2D graphics = buffImg.createGraphics();
            graphics.setBackground(new Color(255,255,255));
            graphics.setColor(new Color(255,255,255));
            graphics.fillRect(0, 0, width, height);
            graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            ImageIO.write(buffImg, subfix, new File(srcImgPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }

    public static void main(String[] args) {
        try {
            readfile("/Users/mac/desktop/image/");
           // File f = new File("/Users/mac/desktop/image/index/blue.png");
           // BufferedImage im = ImageIO.read(f);
            //System.out.println(im.getWidth());
            // deletefile("D:/file");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        System.out.println("ok");
    }



}
