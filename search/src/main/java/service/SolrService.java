package service;

import org.apache.solr.client.solrj.SolrServerException;
import pojo.Tb_item;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/21.
 */
public interface SolrService {
    void impotData() throws IOException, SolrServerException;
    Map searchItem(int page,int rows,String q) throws SolrServerException;
    public int addItemToSolr(Tb_item tb_item,String desc);
}
