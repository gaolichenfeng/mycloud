package cn.tedu.sp02.item.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;

@RestController //@Controller + @ResponseBody
@Slf4j
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@Value("${server.port}")
	private int port;
	
	@GetMapping("/{orderId}") //@RequestMapping(path="/{orderId}", method=GET)
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws Exception {
		log.info("port="+port+", orderId="+orderId);
		
		if (Math.random()<0.6) { //60%概率执行延迟
			int t = new Random().nextInt(5000); //[0,5000) 随机延迟时长
			log.info("延迟: "+t);
			Thread.sleep(t);
		}
		
		List<Item> list = itemService.getItems(orderId);
		JsonResult<List<Item>> r = JsonResult.ok(list).msg("port="+port);
		return r;
	}
	
	
	@PostMapping("/decreaseNumber") //@RequestMapping(path="/{decreaseNumber}", method=POST)
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		log.info("执行减少商品库存");
		itemService.decreaseNumbers(items);
		return JsonResult.ok();
	}
}








