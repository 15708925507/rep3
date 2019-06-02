package com.sql;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DB;
import com.entity.Article;
import com.entity.Classify;
import com.entity.Manager;

public class SqlHelper {
	private Connection conn=null; //声明一个连接对象conn
	private int perPage=10;
	//创建数据库连接
	public SqlHelper(){
		conn=DB.getConnection();
	}
	public void destory(){
		if(conn!=null)
			try {
				conn.close();
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 通过账号查询管理员
	 * @param manager_id
	 * @return manager
	 */
	public Manager queryManagerById(String manager_id){
		Manager manager=null;
		String sql="select * from manager where manager_id=?";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,manager_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				manager=new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_pwd(rs.getString("manager_pwd"));
			  }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	/**
	 * 查询管理员账号和密码
	 * @param m
	 * @return manager
	 */
	public Manager queryManagerByIdAndPwd(Manager m){
		Manager manager=null;
		String sql="select * from manager where manager_id=? and manager_pwd=?";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,m.getManager_id());
			ps.setString(2,m.getManager_pwd());
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				manager=new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_pwd(rs.getString("manager_pwd"));
			  }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	 /**
	  * 添加管理员
	  * @param manager
	  * @return b
	  */
	public boolean insertManager(Manager manager){
		String sql="insert into manager(manager_id,manager_name,manager_pwd)values(?,?,?)";
		boolean b=false;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,manager.getManager_id() );
			ps.setString(2, manager.getManager_name());
			ps.setString(3, manager.getManager_pwd());
			ps.executeUpdate();
			b=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 删除管理员
	 * @param manager
	 * @return
	 */
	public boolean deleteManager(Manager manager){
		String sql="delete from manager where manager_id=?";
		boolean b=false;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,manager.getManager_id() );
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	/*更改管理员密码*/
	public boolean updateManagerPwd(Manager manager){
		String sql="update manager set manager_pwd=? where manager_id=?";
		boolean b=false;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,manager.getManager_pwd());
			ps.setString(2,manager.getManager_id());
			ps.executeUpdate();
			b=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 修改管理员名字
	 * @param manager
	 * @return b
	 */
	public boolean updateManagerName(Manager manager){
		String sql="update manager set manager_name=? where manager_id=?";
		boolean b=false;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,manager.getManager_name());
			ps.setString(2,manager.getManager_id());
			ps.executeUpdate();
			b=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 更新管理员姓名和密码
	 */
	
	public boolean updateManagerNameAndPwd(Manager manager){
		String sql="update manager set manager_name=?,manager_pwd=? where manager_id=?";
		boolean b=false;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,manager.getManager_name());
			ps.setString(2,manager.getManager_pwd());
			ps.setString(3, manager.getManager_id());
			ps.executeUpdate();
			b=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
	
	}
	/**
	 * 查询所有的管理员
	 * @return list
	 */
	public List<Manager> queryManagerAll(){
		List<Manager> list=new ArrayList<Manager>();
		String sql="select manager_id, manager_name from manager";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Manager manager=new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				list.add(manager);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	 /**
	  * 添加分类的方法
	  * @param classify
	  * @return b
	  */
		public boolean insertClassify(Classify classify){
			String sql="insert into classify(classify_id,classify_name,classify_feight)values(?,?,?)";
			boolean b=false;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,classify.getClassify_id() );
				ps.setString(2, classify.getClassify_name());
				ps.setInt(3, classify.getClassify_feight());
				ps.executeUpdate();
				b=true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return b;
		}
		/**
		 * 查询分类信息
		 * @return list
		 */
		public List<Classify> queryClassifyAll(){
			List<Classify> list=new ArrayList<Classify>();
			String sql="select * from classify order by classify_feight asc";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Classify classify=new Classify();
				    classify.setClassify_id(rs.getString("classify_id"));
				    classify.setClassify_name(rs.getString("classify_name"));
				    classify.setClassify_feight(rs.getInt("classify_feight"));
					
					list.add(classify);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		public Classify queryClassifyById(String classify_id){
			String sql="select * from classify where classify_id=? order by article_date desc";
			Classify classify=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,classify_id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					classify=new Classify();
					classify.setClassify_id(rs.getString("classify_id"));
				    classify.setClassify_name(rs.getString("classify_name"));
					classify.setClassify_feight(rs.getInt("classify_feight"));
				  }
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return classify;
			
		}
		/**
		 * 修改分类信息
		 * @param classify
		 * @return
		 */
		public boolean updateClassify(Classify classify) {
			String sql="update classify set classify_name=?,classify_feight=? where classify_id=?";
			boolean b=false;
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,classify.getClassify_name());
				ps.setInt(2,classify.getClassify_feight());
				ps.setString(3, classify.getClassify_id());
				ps.executeUpdate();
				b=true;
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return b;
		}
		/**
		 * 删除分类的方法
		 * @param classify
		 */
		public void deleteClassify(Classify classify) {
			//判断在新闻表中是否有该类别
			String sql="delete from classify where classify_id=?";
			
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,classify.getClassify_id() );
				ps.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	    /**
	     * 根据新闻类别查询新闻信息
	     */
		public List<Article> queryArticleByClassify_id(String classify_id){
			String sql="select article_id,article_title,article_content,article_date,article_content,classify_id from article where classify_id=? order by article_date desc ";
			List<Article> list=new ArrayList();

			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,classify_id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Article article=new Article();
					article.setArticle_id(rs.getString("article_id"));
					article.setArticle_title(rs.getString("article_title"));
					article.setArticle_content(rs.getString("article_content"));
					article.setArticle_date(rs.getString("article_date"));
					article.setClassify_id(rs.getString("classify_id"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return list;
		}
		/**
		 * 前台根据新闻类别查询新闻文章
		 * @param classify_id
		 * @return
		 */
		public List<Article> queryArticleByClassify_idForIndex(String classify_id){
			String sql="select article_id,article_title,article_date,classify_id from article where classify_id=? order by article_date desc limit 0,10 ";
			List<Article> list=new ArrayList();

			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,classify_id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Article article=new Article();
					article.setArticle_id(rs.getString("article_id"));
					article.setArticle_title(rs.getString("article_title"));
					
					article.setArticle_date(rs.getString("article_date"));
					article.setClassify_id(rs.getString("classify_id"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return list;
		}
		/**
		 * 增加新闻
		 * @param article
		 * @return
		 */
		public boolean insertArticle(Article article) {
			String sql="insert into article(article_id,article_title,article_date,article_content,classify_id)values(?,?,?,?,?)";
			boolean b=false;
			try{
				 PreparedStatement ps = conn.prepareStatement(sql);
				 ps.setString(1, article.getArticle_id());
				 ps.setString(2, article.getArticle_title());
				 ps.setString(3, article.getArticle_date());
				 ps.setString(4, article.getArticle_content());
				 ps.setString(5, article.getClassify_id());
				 ps.executeUpdate();
				 b=true;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return b;
		}
		public List<Article> queryArticle() {
			String sql="select article_id,article_title,article_date,classify_name"+"from article,classify"+"where article.classify_id=classify.classify_id order by desc";
			List list=new ArrayList();
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
		public List<Map> queryArticleMap() {
			String sql="select article_id,article_title,classify_name,article_date"
		                +" from article,classify"
					    +" where article.classify_id=classify.classify_id "
					    + "order by article_date desc ";
			List<Map> list=new ArrayList();
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Map map=new HashMap();
					map.put("article_id", rs.getString("article_id"));
					map.put("article_title", rs.getString("article_title"));
					map.put("article_date", rs.getString("article_date"));
					map.put("classify_name", rs.getString("classify_name"));
					list.add(map);
					

					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
		
	
		/**
		 * 通过分页查找文章
		 * @param p 代表当前页面
		 * @return list
		 */
		public List<Map> queryArticleMapByPage(int p) {
			int start=p*perPage;
			String sql="select article_id,article_title,classify_name,article_date"
		                +" from article,classify"
					    +" where article.classify_id=classify.classify_id "
					    + "order by article_date desc "
					    +"limit "+start+","+perPage;
			List<Map> list=new ArrayList();
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Map map=new HashMap();
					map.put("article_id", rs.getString("article_id"));
					map.put("article_title", rs.getString("article_title"));
					map.put("article_date", rs.getString("article_date"));
					map.put("classify_name", rs.getString("classify_name"));
					list.add(map);
					

					
				}
			}catch(Exception e){
				 e.printStackTrace();
			}
			return list;
		}
	
	public  int queryArticleCount(){
		String sql="select Count(*) from article";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Map> queryArticleMapByPage(int p,String classify_id,String article_title) {
		int start=p*perPage;
		int i=start+1;
		String sql="select article_id,article_title,classify_name,article_date"
	                +" from article,classify"
				    +" where article.classify_id=classify.classify_id ";
		if(classify_id !=null && !"".equals(classify_id)){
			sql += " and classify.classify_id=?";
		}
		if(article_title !=null && !"".equals(article_title)){
			sql += " and article_title like ?";
		}
			sql += "order by article_date desc "
				  +"limit "+start+","+perPage;
		List<Map> list=new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			int pos=1;
			if(classify_id !=null && !"".equals(classify_id)){
				ps.setString(1, classify_id);
				pos=2;
			}
			if(article_title !=null && !"".equals(article_title)){
				ps.setString(pos,  "%"+article_title+"%");
				
			   
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Map map=new HashMap();
				map.put("order_number", i++);
				map.put("article_id", rs.getString("article_id"));
				map.put("article_title", rs.getString("article_title"));
				map.put("article_date", rs.getString("article_date"));
				map.put("classify_name", rs.getString("classify_name"));
				list.add(map);
				

				
			}
		}catch(Exception e){
			 e.printStackTrace();
		}
		return list;
	}
	
	
	public  int queryArticleCount(String classify_id,String article_title){
		String sql="select Count(*) from article ";
		int flag=0;
		boolean b1=classify_id !=null && !"".equals(classify_id);
		if(b1){
			sql="select Count(*) from article,classify "
					+"where article.classify_id=classify.classify_id and classify.classify_id=? ";
			flag=1;
			
		}
		boolean b2=article_title !=null && !"".equals(article_title);
		if(b2){
			sql +=(flag>0?"and":"where") +" article_title like ?";
			flag +=1;
		}
	
		System.out.println(classify_id);
		System.out.println(article_title);
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			if(b1){
				ps.setString(1, classify_id);
			}
			if(b2){
				ps.setString(flag,"%"+article_title+"%");
			}
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
		
		
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public Article queryArticleById(String article_id) {
		
		String sql="select article_id,article_title,article_date,article_content,classify_id from article where article_id=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			  Article  article=new Article();
				article.setArticle_title(rs.getString("article_title"));
				article.setArticle_date(rs.getString("article_date"));
				article.setClassify_id(rs.getString("classify_id"));
				article.setArticle_content(rs.getString("article_content"));
				article.setArticle_id(rs.getString("article_id"));
				return article;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改文章
	 * @param article
	 * @return b
	 */
	public boolean editArticle(Article article) {
		boolean b=false;
		String sql="update article "
				+"set article_title=?,article_date=?,article_content=?,classify_id=? "
				+"where article_id=? ";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			 ps.setString(1, article.getArticle_title());
			 ps.setString(2, article.getArticle_date());
			 ps.setString(3, article.getArticle_content());
			 ps.setString(4, article.getClassify_id());
			 ps.setString(5, article.getArticle_id());
			 ps.executeUpdate();
			b=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return b;
	}
	public boolean deleteNewsById(String article_id) {
		boolean b=false;
		String sql="delete from article where article_id=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article_id);
			ps.executeUpdate();
			b=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	public List<Article> queryLastArticles() {
		String sql="select article_id,article_title from article order by article_date desc limit 0,10";
		List list=new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Article a=new Article();
				a.setArticle_id(rs.getString("article_id"));
				a.setArticle_title(rs.getString("article_title"));
				list.add(a);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
