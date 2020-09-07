package com.songhailong.demo1.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {

    // 前置过滤器
    @Override
    public String filterType() {
        return "pre";
    }

    // 过滤器级别
    @Override
    public int filterOrder() {
        return 0;
    }

    // 过滤器是否生效
    @Override
    public boolean shouldFilter() {
        //zool 使用时，上下文对象RequestContext，是共享的。 所以通过RequestContext 获取值
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //设置需要拦截的路径
//        if ("/membercenter/hello".equalsIgnoreCase(request.getRequestURI())) {
//            //拦住了
//            System.out.println("已拦截：/membercenter/hello");
//            return true;
//        }
        //放行
        return false;
    }

    // 过滤逻辑
    @Override
    public Object run() throws ZuulException {
        //zool 使用时，上下文对象RequestContext，是共享的。 所以通过RequestContext 获取值
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");
        String cookie = request.getHeader("Cookie");
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
            cookie = request.getParameter("Cookie");
        }
        //如果token 等于null   返回状态码和没有权限的信息给前台
        if (StringUtils.isBlank(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        //（JWT）解析token  获取权限列表 鉴权。
        return null;
    }
}
