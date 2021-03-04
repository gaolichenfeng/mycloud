package cn.tedu.sp11.fliter;


import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.tedu.web.util.JsonResult;
@Component
public class AccessFilter extends ZuulFilter {
	
	@Override
	public boolean shouldFilter() {
		// 针对单个的服务id进行过滤,判断当前请求的id,是否为指定的服务id,则放行
		RequestContext ctx = RequestContext.getCurrentContext();
		String pid = (String)ctx.get(FilterConstants.SERVICE_ID_KEY);
		return pid.equals("item-service");
	}

	@Override
	public Object run() throws ZuulException {
		// 过滤代码,这里写权限判断代码,但是object没有返回值
		RequestContext ctx = RequestContext.getCurrentContext(); //提出来使用一个会出现问题
		HttpServletRequest request = ctx.getRequest();
		String token = request.getParameter("token");
		if(token==null||token.trim().length()==0) {
			//没有token,拒绝访问
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(JsonResult.err().code(JsonResult.NOT_LOGIN).toString());
		}
		//没有被使用到
		return null;
	}

	@Override
	public String filterType() {
		// 设置过滤器类型,为前置过滤器
		return FilterConstants.PRE_TYPE;//前置过滤器类型pre
	}

	@Override
	public int filterOrder() {
		// 过滤器顺序的位置,要放在5之后,这样此过滤器才能被访问到
		return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
	}

}
