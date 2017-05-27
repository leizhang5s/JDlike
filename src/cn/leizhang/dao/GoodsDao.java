package cn.leizhang.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.leizhang.domain.FeedBack;
import cn.leizhang.domain.Goods;
import cn.leizhang.utils.DataRourceUtils;

public class GoodsDao {
	
	
	public static ArrayList<Goods> getSpecialGoods() throws SQLException {
		String sql="select * from goods where goodsid in (select goodsid from specialgoods)";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		ArrayList<Goods> arr=runner.query(sql,new BeanListHandler(Goods.class));
		return arr;
	}
	
	
	public static ArrayList<Goods> getGoods() throws SQLException {
		String sql="select * from goods ";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		ArrayList<Goods> arr=runner.query(sql,new BeanListHandler(Goods.class));
		//System.out.println(arr.get(0).getGoodsname());
		return arr;
	}
	
	
	public static ArrayList<FeedBack> getFeedBacks() throws SQLException {
		String sql="select * from feedback";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		ArrayList<FeedBack> arr=runner.query(sql,new BeanListHandler(FeedBack.class));
		return arr;
	}
	
	public static Goods getGoodsById(String goodsID) throws SQLException {
		String sql="select * from goods where goodsid=?";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		Goods goods=runner.query(sql,new BeanHandler(Goods.class),goodsID);
		return goods;
	}


	public static ArrayList<Goods> getRandomGoodsBeans(int a) throws SQLException {
		String sql="select count(*) from goods";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		Object obj=runner.query(sql, new ScalarHandler(1));
		int num=((Long)obj).intValue();
		Random ran=new Random();
		ArrayList<Goods> arr=new ArrayList<Goods>();
		Set<Integer> set=new HashSet<Integer>();
		
		while(set.size()<a)
		{	
			int id=ran.nextInt(num)+1;
			set.add(id);
			//System.out.println(id);
		}
		for(int i:set)
		{
			Goods good=getGoodsById(String.valueOf(i));
			arr.add(good);
		}
		
		return arr;
	}


	public static ArrayList<Goods> getNewGoodsBean() throws SQLException {
		String sql="select * from goods where goodsid in (select * from newgoods)";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		ArrayList<Goods> arr=runner.query(sql,new BeanListHandler(Goods.class));
		//System.out.println(arr.get(0).getGoodsname());
		return arr;
		
	}


	public static ArrayList<Goods> getBetweenGoods(int a, int b) throws SQLException {
		ArrayList<Goods> arr=new ArrayList<Goods>();
		for(int i=a;i<=b;i++)
		{
			Goods goods=getGoodsById(String.valueOf(i));
			arr.add(goods);
			
			
		}
		
		return arr;
	}
	
	public static void saveFeedBack(FeedBack fb) throws SQLException {
		String sql="insert into feedback values(?,?,?,?,?,?)";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		runner.update(sql, null,fb.getName(),fb.getSubject(),fb.getMsg(),fb.getEmail(),fb.getIp());
		
	}
	
	
}
