package controller;

import consumerInterface.PicUploadInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by 王俊 on 2019/8/15.
 */
@Controller
public class PicUploadController {
    @Autowired
    private PicUploadInterface picUploadService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map picUpload(MultipartFile uploadFile){
       return picUploadService.uploadPic(uploadFile);
    }
}
