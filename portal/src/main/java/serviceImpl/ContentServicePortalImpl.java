package serviceImpl;

import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.Tb_content;
import providerInterface.ContentInterface;
import service.ContentServicePortal;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 王俊 on 2019/8/20.
 */
@Service
public class ContentServicePortalImpl implements ContentServicePortal {
    @Autowired
    private ContentInterface contentService;
    @Autowired
    private JedisDao jedisDao;
    @Value("big_ad")
    private  String key;
    @Override
    public String showBigPic() {
        if(jedisDao.exist(key)){
            return  jedisDao.get(key);
        }

        List<Tb_content> list= contentService.showBigPic(6, true);
        List<Map<String,Object>> returnList=new ArrayList<>();
        for (Tb_content content:list){
            Map map=new HashMap<>();
            map.put("scrB",content.getPic2());
            map.put("height",240);
            map.put("alt","图片无法加载");
            map.put("width",670);
            map.put("src",content.getPic());
            map.put("widthB",550);
            map.put("href",content.getUrl());
            map.put("heightB",240);
            returnList.add(map);
        }
        String json= JsonUtils.objectToJson(returnList);
        //System.out.println(json);
        jedisDao.set(key,json);
        return json;
    }
}
