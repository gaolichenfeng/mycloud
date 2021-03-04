package cn.tedu.sp11.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import cn.tedu.web.util.JsonResult;
@Component //加上注解,给spring容器,就可以自动使用降级类了
public class UserFallback implements FallbackProvider {

	@Override
	public String getRoute() {
		// 应用的具体服务:item-service,"*",null
		return "user-service";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				// 设置请求头
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				return httpHeaders;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				// 返回Jsonresult的json穿
				String json = JsonResult.err().msg("用户后台服务器错误...").toString();
				ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes("UTF-8"));
				return stream;
			}
			
			@Override
			public String getStatusText() throws IOException {
				// 返回状态信息
				return HttpStatus.OK.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				// 返回状态码和信息
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// 返回状态码
				return HttpStatus.OK.value();
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
