package consumerInterface;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/15.
 */
public interface PicUploadInterface {
    public Map<String, Object> uploadPic(MultipartFile file);
}
