package com.etjava.controller;

import com.etjava.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传处理Controller
 */
@RestController
@RequestMapping("/")
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    // 注入配置文件中的图片文件路径
    @Value("${imageFilePath}")
    private String imageFilePath;

    @RequestMapping("/uploadImage")
    public Map<String,Object> upLoadImage(MultipartFile file) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(!file.isEmpty()){
            // 获取源文件名称
            String filename = file.getOriginalFilename();
            // 获取后缀
            String suffix = filename.substring(filename.lastIndexOf("."));
            // 重命名图片
            String newFileName = DateUtil.getCurrentDateStr() + suffix;
            // 输出到磁盘
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+newFileName));

            map.put("code",0);
            map.put("msg","上传成功");

            Map<String,Object> map2 = new HashMap<>();
            map2.put("title",newFileName);
            map2.put("src","/image/"+newFileName);
            map.put("data",map2);
            logger.info("上传头像 "+map);
        }


        return map;
    }
}
