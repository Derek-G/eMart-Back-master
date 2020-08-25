package com.kedacom.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
	//启动项目后访问：http://localhost:1201/category-service/default/master可查看到远程仓库配置文件信息
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
