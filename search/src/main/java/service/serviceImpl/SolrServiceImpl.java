package service.serviceImpl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.ItemCustemer;
import pojo.Tb_item;
import providerInterface.ItemCatInterface;
import providerInterface.ItemDescInterface;
import providerInterface.ItemInterface;
import service.SolrService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/21.
 */
@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    private CloudSolrServer cloudSolrServer;
    @Autowired
    private ItemInterface itemInterface;
    @Autowired
    private ItemCatInterface itemCatInterface;
    @Autowired
    private ItemDescInterface descInterface;
    @Override
    public void impotData() throws IOException, SolrServerException {
        /*cloudSolrServer.deleteByQuery("*:*");
        cloudSolrServer.commit();*/
        List<Tb_item> itemList=itemInterface.importData();
        List<SolrInputDocument> solrInputDocumentList= new ArrayList<>();
        for (Tb_item tb_item:itemList){
            SolrInputDocument solrInputDocument=new SolrInputDocument();
            solrInputDocument.addField("id",tb_item.getId());
            solrInputDocument.addField("item_title",tb_item.getTitle());
            solrInputDocument.addField("item_sell_point",tb_item.getSellPoint());
            solrInputDocument.addField("item_price",tb_item.getPrice());
            solrInputDocument.addField("item_image",tb_item.getImage());
            solrInputDocument.addField("item_category_name",itemCatInterface.findItemCatById(tb_item.getCid()).getName());
            solrInputDocument.addField("item_desc",descInterface.findItemDescById(tb_item.getId()).getItemDesc());

            solrInputDocumentList.add(solrInputDocument);
        }

       UpdateResponse response= cloudSolrServer.add(solrInputDocumentList);
        int status= response.getStatus();
        System.out.println(status);
        cloudSolrServer.commit();
    }
    public int addItemToSolr(Tb_item tb_item,String desc){
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("id",tb_item.getId());
        solrInputDocument.addField("item_title",tb_item.getTitle());
        solrInputDocument.addField("item_sell_point",tb_item.getSellPoint());
        solrInputDocument.addField("item_price",tb_item.getPrice());
        solrInputDocument.addField("item_image",tb_item.getImage());
        solrInputDocument.addField("item_category_name",itemCatInterface.findItemCatById(tb_item.getCid()).getName());
        solrInputDocument.addField("item_desc", desc);

        try {
            cloudSolrServer.add(solrInputDocument);
            cloudSolrServer.commit();
            return 1;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String,Object> searchItem(int page,int rows,String q) throws SolrServerException {
        SolrQuery query=new SolrQuery();
        query.setQuery(q);
        query.set("df","item_title");
        query.setStart(rows*(page-1));
        query.setRows(rows);
        //设置高亮
        query.setHighlight(true);
        query.addHighlightField("item_title");
        //设置高亮格式
        query.setHighlightSimplePre("<em style='color:orange;'>");
        query.setHighlightSimplePost("</em>");
       QueryResponse response= cloudSolrServer.query(query);
       SolrDocumentList documentList= response.getResults();
          /*
        "536563": {
              "item_title": [
                "new2 - <em>阿尔卡特</em> (OT-927) 炭黑 联通3G手机 双卡双待"
              ]
            }
         */
        //获取高亮的内容
        Map<String, Map<String, List<String>>> map = response.getHighlighting();
        List<ItemCustemer> returnList=new ArrayList<>();
        for (SolrDocument document:documentList){
            ItemCustemer itemCustemer=new ItemCustemer();
            itemCustemer.setId(Long.parseLong(document.getFirstValue("id").toString()));
            itemCustemer.setImages(document.getFieldValue("item_image")==null?new String [1]:document.getFieldValue("item_image").toString().split(","));
            itemCustemer.setPrice(Long.parseLong(document.getFieldValue("item_price").toString()));
            itemCustemer.setSellPoint(document.getFieldValue("item_sell_point").toString());
            List<String> list=map.get(itemCustemer.getId().toString()).get("item_title");
            if (list!=null&&list.size()!=0){
                itemCustemer.setTitle(list.get(0));
            }else {
                itemCustemer.setTitle(document.getFieldValue("item_title").toString());
            }
            returnList.add(itemCustemer);
        }
        Map<String,Object> resMap=new HashMap<>();
        resMap.put("returnList",returnList);
        resMap.put("page",page);
        resMap.put("totalPages",documentList.getNumFound()%rows==0?documentList.getNumFound()/rows:documentList.getNumFound()/rows+1);
        return resMap;
    }
}
