package cn.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
@FeignClient(name = "item-service", fallback = ItemFeignClientFB.class)
public interface ItemFeignClient {
	//调用后台商品服务,获取商品列表
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);
	
	//调用后台商品服务,减少商品库存
	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
