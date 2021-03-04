package cn.tedu.sp09.service;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
@Component
public class ItemFeignClientFB implements ItemFeignClient {

	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		return JsonResult.err("获取订单的商品列表失败");
	}

	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		return JsonResult.err("减少商品库存失败");
	}

}
