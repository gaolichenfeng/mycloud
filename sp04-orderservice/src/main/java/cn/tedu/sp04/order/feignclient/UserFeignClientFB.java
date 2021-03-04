package cn.tedu.sp04.order.feignclient;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;

@Component
public class UserFeignClientFB implements UserFeignClient {

	@Override
	public JsonResult<User> getUser(Integer userId) {
		//模拟缓存数据
		if (Math.random()<0.5) {
			return JsonResult.ok(new User(7,"缓存用户7","缓存用户密码"));
		}
		
		//如果没有缓存数据,就直接返回错误提示
		return JsonResult.err("获取用户失败");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		return JsonResult.err("增加用户积分失败");
	}

}
