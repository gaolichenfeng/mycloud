package cn.tedu.sp09.service;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
@Component
public class UserFeignClientFB implements UserFeignClient {

	@Override
	public JsonResult<User> getUser(Integer userId) {
		return JsonResult.err("获取用户失败");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		return JsonResult.err("增加用户积分失败");
	}

}
