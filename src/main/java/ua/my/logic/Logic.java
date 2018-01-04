package ua.my.logic;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Logic {
   private static File getRootPath(){
        final  String findKey ="SNAPSHOT";
        int prefixLength = "file:".length();
        String classPath = Logic.class.getResource("").toString();
        String rootPath = classPath.substring(prefixLength,classPath.indexOf(findKey)+findKey.length());
        return new File(rootPath);
    }

    private static String getStaticRootPath(){
       return getRootPath()+"/static/";
    }


    public static String savePhoto(MultipartFile photo){

        try {
//            if(photo==null)return "logo.jpg";
            String photoName = System.currentTimeMillis()+".jpg";

            File outputFile = new File(getStaticRootPath() + photoName);
            outputFile.createNewFile();

//            BufferedInputStream bis = new BufferedInputStream(photo.getInputStream());
//            FileOutputStream fos = new FileOutputStream(outputFile);
            BufferedImage img = ImageIO.read(photo.getInputStream());
            ImageIO.write(img, "jpg", outputFile);
            return photoName;
        } catch (IOException e) {
            e.printStackTrace();
            return "ChekList.jpg";
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return "logo.jpg";
        }

    }

    public static void deletePhoto(String imageName){
        if (imageName.equals("logo.jpg")) return;
        String photoPath = getStaticRootPath()+imageName;
        File file = new File(photoPath);
        file.delete();
    }
}
