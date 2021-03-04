package cn.tedu.sp06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RibbonController {
	@Autowired
	private RestTemplate rt;

	@HystrixCommand(fallbackMethod = "getItemsFB")
	@GetMapping("/item-service/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
		// http://localhost:8001/{orderId}
		return rt.getForObject("http://item-service/{1}", JsonResult.class, orderId);
	}

	@HystrixCommand(fallbackMethod = "decreaseNumberFB")
	@PostMapping("/item-service/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		// http://localhost:8001/decreaseNumber
		return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
	}

	/////////////////////////////////////////

	@HystrixCommand(fallbackMethod = "getUserFB")
	@GetMapping("/user-service/{userId}")
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
	}

	@HystrixCommand(fallbackMethod = "addScoreFB")
	@GetMapping("/user-service/{userId}/score") 
	public JsonResult addScore(
			@PathVariable Integer userId, Integer score) {
		return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
	}

	/////////////////////////////////////////

	@HystrixCommand(fallbackMethod = "getOrderFB")
	@GetMapping("/order-service/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
	}

	@HystrixCommand(fallbackMethod = "addOrderFB")
	@GetMapping("/order-service")
	public JsonResult addOrder() {
		return rt.getForObject("http://order-service/", JsonResult.class);
	}

	////////////////////////////////////////////////////////////////////////////
	public JsonResult<List<Item>> getItemsFB(String orderId) {
		return JsonResult.err("获取订单的商品列表失败");
	}
	public JsonResult decreaseNumberFB(List<Item> items) {
		return JsonResult.err("减少商品库存失败");
	}
	public JsonResult<User> getUserFB(Integer userId) {
		return JsonResult.err("获取用户失败");
	}
	public JsonResult addScoreFB(
			Integer userId, Integer score) {
		return JsonResult.err("增加用户集分失败");
	}
	public JsonResult<Order> getOrderFB(String orderId) {
		return JsonResult.err("获取订单失败");
	}
	public JsonResult addOrderFB() {
		return JsonResult.err("保存订单失败");
	}


}
