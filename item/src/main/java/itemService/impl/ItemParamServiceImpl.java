package itemService.impl;

import itemService.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.ItemParam;
import pojo.Param;
import pojo.Tb_item_param_item;
import providerInterface.ItemParamItemInterface;
import utils.JsonUtils;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private ItemParamItemInterface itemParamItemInterface;
    @Override
    public String showItemParam(long id) {
        Tb_item_param_item tb_item_param_item= itemParamItemInterface.findItemParamItemByI(id);
        System.out.println(tb_item_param_item);
        String json= tb_item_param_item.getParamData();
            System.out.println(json);
       List<ItemParam> itemParamList= JsonUtils.jsonToList(json, ItemParam.class);
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<itemParamList.size();i++){
            ItemParam itemParam=itemParamList.get(i);
            String group=itemParam.getGroup();
            List<Param> paramList=itemParam.getParams();
            stringBuilder.append("<table width='500' style='color:gray'>");
            for (int j=0;j<paramList.size();j++){
                stringBuilder.append("<tr>");
                if (j==0){
                    stringBuilder.append("<td align = 'right'>"+group+"</td>");
                }else {
                    stringBuilder.append("<td></td>");
                }

                System.out.println(j);
                System.out.println(paramList.get(j).getK());
                stringBuilder.append("<td align = 'right'>"+paramList.get(j).getK()+"</td>");
                stringBuilder.append("<td align = 'right'>"+paramList.get(j).getV()+"</td>");
                stringBuilder.append("</tr>");
            }
            stringBuilder.append("</table>");
        }
        return stringBuilder.toString();
    }
}
