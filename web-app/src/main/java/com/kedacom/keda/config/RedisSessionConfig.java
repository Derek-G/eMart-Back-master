package com.kedacom.keda.config;

import jdk.nashorn.internal.runtime.GlobalConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启spring session支持
 */
@Configuration
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class RedisSessionConfig {
    //Spring Session替换了默认的tomcat httpSession
}
