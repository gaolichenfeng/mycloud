package cn.tedu.sp01.service;

import java.util.List;

import cn.tedu.sp01.pojo.Item;

public interface ItemService {
	//根据订单id,获取订单中的商品列表
	List<Item> getItems(String orderId);
	
	//减少商品库存
	void decreaseNumbers(List<Item> items);
}
