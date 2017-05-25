package cn.leizhang.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.leizhang.dao.GoodsDao;
import cn.leizhang.domain.FeedBack;
import cn.leizhang.domain.Goods;
import cn.leizhang.utils.DataRourceUtils;

public class GoodsService {
	public static ArrayList<Goods> getSpecialGoods() throws SQLException
	{
		
		return GoodsDao.getSpecialGoods();
		
	}
	public static ArrayList<FeedBack> getFeedBacks() throws SQLException
	{
		
		return GoodsDao.getFeedBacks();
		
	}
	public static ArrayList<Goods> getGoods() throws SQLException
	{
		
		return GoodsDao.getGoods();
		
	}
	public static Goods getGoodsById(String goodsID) throws SQLException
	{
		
		return GoodsDao.getGoodsById(goodsID);
		
	}
	public static ArrayList<Goods> getRandomGoodsBeans(int a) throws SQLException
	{
		
		return GoodsDao.getRandomGoodsBeans(a);
		
	}
	public static ArrayList<Goods> getNewGoodsBean() throws SQLException
	{
		
		return GoodsDao.getNewGoodsBean();
		
	}
	public static ArrayList<Goods> getBetweenGoods(int a,int b) throws SQLException
	{
		
		return GoodsDao.getBetweenGoods(a,b);
		
		
	}
	public void saveFeedBack(FeedBack fb) throws SQLException {
		
		 GoodsDao.saveFeedBack(fb);
		
	}
	
}
