package com.fanchaojian.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * 上传图片的工厂
 * @author fanchaojian
 * @date 2020-9-27 - 16:25
 */
@Controller
@RestController
@RequestMapping("fileUpload")
public class FileUploadFactory {

    /**
     *
     * @param request
     * @param upload
     * @param base  上传的类型。blog:博客文章图片，icon:头像，图标等图片
     * @return
     */
    @PostMapping("{base}")
    public String uploadFile(HttpServletRequest request, MultipartFile upload, @PathVariable String base){

        try {
            //创建上传照片的地址，默认基路径为 tomcat../webapps/
            String path = request.getSession().getServletContext().getRealPath("/")+"../"+"/images/"+base;

            //判断文件路径是否存在
            File file = new File(path);
            if(!file.exists()){
                //创建多级目录
                file.mkdirs() ;
            }
            //重新指定上传的文件的名称
            String fileRealName = upload.getOriginalFilename() ;
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0,6);
            String fileName = uuid+"_"+fileRealName ;

            //完成文件上传
            upload.transferTo(new File(path,fileName));

            //获取服务器地址
            String serviceRoot=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

            System.out.println("服务器地址："+serviceRoot) ;
            //在这之前需要先设置Ttomcat的虚拟路径 tomcat.../webapps,命名为fanblog_upload
            String resultPath =  serviceRoot+"fanblog_upload/images/"+base+"/"+fileName;
            System.out.println(resultPath) ;
            /*return new String(resultPath.getBytes("ISO8859-1"),"UTF-8");*/
            return resultPath ;
        } catch (Exception e) {
            System.out.println("上传图片失败") ;
        	e.printStackTrace();
        	return "error" ;
        }

    }

}
