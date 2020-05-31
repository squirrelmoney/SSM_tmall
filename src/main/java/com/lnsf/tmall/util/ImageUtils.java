package com.lnsf.tmall.util;


import com.sun.jersey.api.client.Client;
        import com.sun.jersey.api.client.WebResource;
        import org.apache.commons.io.FilenameUtils;
        import org.springframework.web.multipart.MultipartFile;

        import javax.servlet.http.HttpServletRequest;
        import java.io.File;
        import java.io.IOException;
        import java.util.*;

/*图片工具类*/
public class ImageUtils {



    /**
     * 上传图片
     * *@param product
     * 	 @return
     */
    public static String newname(HttpServletRequest request, MultipartFile pictureFile) throws IOException {

        String imgname= null;//装配后的图片地址

        //上传图片
        if(pictureFile!=null&&!pictureFile.isEmpty()){

            // 使用UUID给图片重命名，并去掉四个“-”
            String name = UUID.randomUUID().toString().replaceAll("-", "");
            // 获取文件的扩展名(img\png\gif)-----FilenameUtils工具类
            String ext = FilenameUtils.getExtension(pictureFile
                    .getOriginalFilename());
            // 装配图片地址
            imgname =name + "." + ext;

        }

        return imgname;

    }



      public static void jeseyService(String newFilename,byte[] fileBytes) {
          // 创建jesey服务器，进行跨服务器上传
          Client client = Client.create();
          // 将文件关联到远程服务器
          // 这里的Commons.pic="http://192.168.3.9:8089/Filesevice"，也就是图片服务器
          WebResource resource = client.resource(Commons.pic + "/upload/" + newFilename);
          // 上传到服务器
          resource.put(String.class, fileBytes);
      }

}
