package com.action.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Article;
import com.entity.Classify;
import com.entity.Manager;
import com.sql.SqlHelper;

public class ActionBean {
	
	public String queryManagerAll(){
		SqlHelper sqlHelper=new SqlHelper();
		List<Manager> list=sqlHelper.queryManagerAll();
		String tablehtml="";
		int i=1;
		for(Manager manager:list){
			tablehtml+="<tr><td><input type=\"radio\" name=\"rd\" autocomplete=\"off\"  value=\""+manager.getManager_id()+"\"></td><td class=\"text-center\">"+(i++)+"</td><td class=\"text-center\">"+manager.getManager_id()+"</td><td class=\"text-center\">"+manager.getManager_name()+"</td></tr>";
		}
		sqlHelper.destory();
		return tablehtml;
	}
	
	public Manager queryManagerById(String manager_id){
		SqlHelper sqlHelper=new SqlHelper();
		Manager manager=sqlHelper.queryManagerById(manager_id);
		
		sqlHelper.destory();
		return manager;
	}
	
	public String queryClassifyAll(){
		SqlHelper sqlHelper=new SqlHelper();
		List<Classify> list=sqlHelper.queryClassifyAll();
		String tablehtml="";
		int i=1;
		for(Classify classify:list){
			tablehtml+="<tr><td><input type=\"radio\" name=\"rd\" autocomplete=\"off\"  value=\""+classify.getClassify_id()+"\"></td><td class=\"text-center\">"+(i++)+"</td><td class=\"text-center\">"+classify.getClassify_name()+"</td><td class=\"text-center\">"+classify.getClassify_feight()+"</td></tr>";
		}
		sqlHelper.destory();
		return tablehtml;
	}
	
    public Classify queryClassifyById(String classify_id){
    	SqlHelper sqlHelper=new SqlHelper();
    	Classify classify=sqlHelper.queryClassifyById(classify_id);
		sqlHelper.destory();
		return classify;
	}
    /**
     * 查询出所有的新闻类别
     */
    public List queryClassifyList(){
		SqlHelper sqlHelper=new SqlHelper();
		List<Classify> list=sqlHelper.queryClassifyAll();
		
		sqlHelper.destory();
		return list;
	}
    /**
     * 查询所有的新闻
     * 
     */
    public List<Map> queryArticleMap(){
    	SqlHelper sqlHelper=new SqlHelper();
    	List list=sqlHelper.queryArticleMap();
    	sqlHelper.destory();
    	return list;
    }
    /**
     * 通过分页查询新闻
     * @return list
     */
    public List<Map> queryArticleMapByPage(int p){
    	SqlHelper sqlHelper=new SqlHelper();
    	List list=sqlHelper.queryArticleMapByPage(p);
    	sqlHelper.destory();
    	return list;
    }
    /**
     * 通过分页查询新闻，map中存放多少条记录
     * @param p 代表当前页数
     * @return map
     */
    
    public Map queryArticleByPage(int p){
    	Map map=new HashMap();
    	SqlHelper sqlHelper=new SqlHelper();
    	map.put("list", sqlHelper.queryArticleMapByPage(p));
    	map.put("rows", sqlHelper.queryArticleCount());
    	map.put("classifyList", sqlHelper.queryClassifyAll());
    	sqlHelper.destory();
    	return map;
    }
    public Map queryArticleByPage(int p,String classify_id,String article_title){
    	Map map=new HashMap();
    	SqlHelper sqlHelper=new SqlHelper();
    	map.put("list", sqlHelper.queryArticleMapByPage(p,classify_id,article_title));
    	map.put("rows", sqlHelper.queryArticleCount(classify_id,article_title));
    	map.put("classifyList", sqlHelper.queryClassifyAll());
    	sqlHelper.destory();
    	return map;
    }
    /**
     * 查询文化文章并将结果放到map中
     * @param article_id
     * @return map
     */
    public Map queryArticleMapEdit(String article_id){
    	Map map=new HashMap();
    	SqlHelper sqlHelper=new SqlHelper();
    	map.put("classifyList", sqlHelper.queryClassifyAll()); //查出所有的分类
    	map.put("article", sqlHelper.queryArticleById(article_id));//根据文章ID查询该文章
    	sqlHelper.destory();
    	return map;
    }
}
	
