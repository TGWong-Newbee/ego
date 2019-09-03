package consumerService;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import consumerInterface.ConsumerContentInterface;
import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.Tb_content;
import providerInterface.ContentInterface;
import utils.JsonUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/19.
 */
@Service
public class ConsumerContentService implements ConsumerContentInterface {
    @Autowired
    private ContentInterface contentInterface;
    @Autowired
    private JedisDao jedisDao;
    @Value("big_ad")
    private  String key;
    @Override
    public EasyUIDataGrid findContenByCatId(long categoryId, int page, int rows) {
        return contentInterface.findContentByContentCatId(categoryId, page, rows);
    }

    @Override
    public EasyuiStatus addContent(Tb_content tb_content) {
        new Thread(){
            @Override
            public void run() {
                if(jedisDao.exist(key)) {
                    String value = jedisDao.get(key);
                    List<Map> listMap = JsonUtils.jsonToList(value, Map.class);
                    if (listMap.size()==6){
                        listMap.remove(5);
                    }
                    Map map=new HashMap<>();
                    map.put("scrB", tb_content.getPic2());
                    map.put("height", 240);
                    map.put("alt", "图片无法加载");
                    map.put("width", 670);
                    map.put("src", tb_content.getPic());
                    map.put("widthB", 550);
                    map.put("href", tb_content.getUrl());
                    map.put("heightB", 240);
                    listMap.add(0,map);
                    String json=  JsonUtils.objectToJson(listMap);
                    jedisDao.set(key,json);
                }
            }
        }.start();
        Date date=new Date();
        tb_content.setCreated(date);
        tb_content.setUpdated(date);
        int res=contentInterface.addContent(tb_content);
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        if (res>0){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus updateContent(Tb_content tb_content) {
        Date date=new Date();
        tb_content.setUpdated(date);
        tb_content.setCreated(date);
        int res=contentInterface.updateContent(tb_content);
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        if (res>0){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus deleteContent(String ids) {
        int res=0;
       String strarr[]= ids.split(",");
        for (String str:strarr){
            long id=Long.parseLong(str);
            res+=contentInterface.deleteContent(id);
        }
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        if (res==strarr.length){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }
}
