package consumerService;

import consumerInterface.PicUploadInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.FtpUtil;
import utils.IDUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/15.
 */
@Service
public class PicUploadService implements PicUploadInterface {
    @Value("${ftp_host}")
    private String ftp_host;
    @Value("${ftp_port}")
    private Integer ftp_port;
    @Value("${ftp_user}")
    private String ftp_user;
    @Value("${ftp_pass}")
    private String ftp_pass;
    @Value("${ftp_basepath}")
    private String ftp_basepath;

    @Override
    public Map<String, Object> uploadPic(MultipartFile file)  {
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("/yyyy/MM/dd/");
        String filePath= simpleDateFormat.format(new Date());
        String filename=IDUtils.genImageName()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            boolean result=FtpUtil.uploadFile(ftp_host,ftp_port,ftp_user,ftp_pass,ftp_basepath,filePath,filename,file.getInputStream());
            if (result) {
                map.put("error", 0);
                map.put("url", "http://" + ftp_host + filePath + filename);
            }else {
                map.put("error",1);
                map.put("message","上传图片失败");
            }
        } catch (IOException e) {
            map.put("error",1);
            map.put("message","上传图片失败");
            e.printStackTrace();
        }

        return map;
    }
}
