package com.kedacom.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Zuul过滤器,在实现了自定义过滤器之后，它并不会直接生效，我们还需要为其创建具体的Bean才能启动该过滤器(应用主类中创建)
 * 可定义一些与业务无关的通用逻辑实现对请求的过滤和拦截，比如：签名校验、权限校验、请求限流等功能。
 *
 */
public class AccessFilter extends ZuulFilter{

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    private static final String[] IGNORE_URI = {"/login","/css/","/js/","/img/"};

    //过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
    @Override public String filterType() {
        //pre，代表会在请求被路由之前执行
        return "pre";
    }

    //过滤器的执行顺序
    @Override public int filterOrder() {
        return 0;
    }

    //判断该过滤器是否需要被执行
    @Override public boolean shouldFilter() {
        return true;
    }

    @Override public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        /** 默认用户没有登录 */
        boolean flag = false;
        /** 获得请求的ServletPath */
        String servletPath = request.getServletPath();
        /**  判断请求是否需要拦截 */
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }

        if(!flag){
            Object accessToken = request.getParameter("accessToken");
            // TODO accessToken 的获取及验证
            accessToken = "test";

            if(accessToken == null) {
                logger.warn("access token is empty");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                return null;
            }

            logger.info("accessToken ok");
            //路由转发
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            return null;
        }else {
            return null;
        }
    }
}
