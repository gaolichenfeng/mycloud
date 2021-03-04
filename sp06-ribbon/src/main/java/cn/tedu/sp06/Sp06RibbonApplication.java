package cn.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Sp06RibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRt() {
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setConnectTimeout(1000);
		f.setReadTimeout(1000);
		
		return new RestTemplate(f);
		
		//return new RestTemplate();
	}
}





