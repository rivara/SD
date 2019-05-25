package es.urjc.code.daw;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.urjc.code.daw.Application;



@SpringBootApplication
public class Application {

	private static final Log LOG = LogFactory.getLog(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
						.allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.allowCredentials(true).maxAge(3600);
			}
		};
	}
}
	/*
	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
		return config;
	}
	
	@Bean
    public CacheManager cacheManager() {	
    	LOG.info("ACTIVANDO CACHE!!!");	
    	return new ConcurrentMapCacheManager("web"); 
    }*/


