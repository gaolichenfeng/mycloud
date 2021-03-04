package cn.tedu.sp04.order.feignclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;

@Component
public class ItemFeignClientFB implements ItemFeignClient {

	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		//模拟缓存数据
		if (Math.random()<0.5) {
			return JsonResult.ok(Arrays.asList(new Item[] {
					new Item(1, "缓存商品1", 2),
					new Item(2, "缓存商品2", 1),
					new Item(3, "缓存商品3", 5),
					new Item(4, "缓存商品4", 3),
					new Item(5, "缓存商品5", 2),
			}));
		}
		
		//如果没有缓存数据,就直接返回错误提示
		return JsonResult.err("获取订单的商品列表失败");
	}

	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		return JsonResult.err("减少商品库存失败");
	}

}
